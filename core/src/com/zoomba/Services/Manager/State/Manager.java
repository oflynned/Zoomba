package com.zoomba.Services.Manager.State;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Timer;
import com.zoomba.GameObjects.Circles.Fast;
import com.zoomba.GameObjects.Circles.Slow;
import com.zoomba.GameObjects.ObjectFactory.Factories.HazardFactory;
import com.zoomba.GameObjects.ObjectFactory.Factories.PowerupFactory;
import com.zoomba.GameObjects.ObjectFactory.Objects.Circle;
import com.zoomba.GameObjects.ObjectFactory.Objects.Factory;
import com.zoomba.GameObjects.ObjectFactory.Objects.Hazard;
import com.zoomba.GameObjects.ObjectFactory.Objects.Powerup;
import com.zoomba.GameObjects.ObjectFactory.Types.HazardTypes;
import com.zoomba.GameObjects.ObjectFactory.Types.PowerupTypes;
import com.zoomba.Services.Constants;
import com.zoomba.Services.Manager.Types.DebugState;
import com.zoomba.Services.Manager.Types.GameState;

import java.util.Random;

/**
 * Created by ed on 09/08/16.
 */
public class Manager {
    private int currentEpoch;
    private GameState state;
    private int points;
    private int difficulty;
    private DebugState debugState = DebugState.Normal;

    //static singleton -- do not instantiate, private constructor prevents this
    private Manager() {}

    private static Manager manager = null;

    public static Manager getInstance() {
        manager = manager == null ? new Manager() : manager;
        return manager;
    }

    public float generateCoords(float limit) {
        return new Random().nextInt((int)limit);
    }

    public void startTimer() {
        setCurrentEpoch(Constants.GAME_LENGTH);
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                currentEpoch--;
            }
        }, 1f);
    }

    public void checkState(int entityCount) {
        if(getCurrentEpoch() > Constants.ONE_SECOND) {
            currentEpoch--;
            state = entityCount > 0 ? GameState.Ongoing : GameState.Win;
        } else {
            state = entityCount > 0 ? GameState.Loss : GameState.Win;
        }
    }

    public void incrementScore(Circle circle) {
        if (circle.getClass().equals(Slow.class)) {
            points += Constants.SLOW_POINTS;
        } else if(circle.getClass().equals(Fast.class)) {
            points += Constants.FAST_POINTS;
        }
    }

    public Powerup generatePowerup(PowerupTypes powerupType) {
        Gdx.app.debug("Powerup", "Generating powerup: " + powerupType.name());
        return new PowerupFactory().generatePowerup(powerupType);
    }

    public Hazard generateHazard(HazardTypes hazardType) {
        Gdx.app.debug("Hazard", "Generating hazard: " + hazardType.name());
        return new HazardFactory().generateHazard(hazardType);
    }

    public void generatePickup() {
        int random = new Random().nextInt(2);
        if (random == 1) {
            int randomHazard = HazardTypes.values().length;
            HazardTypes[] hazardTypes = HazardTypes.values();
            generateHazard(hazardTypes[randomHazard]);
        } else {
            int randomPowerup = PowerupTypes.values().length;
            PowerupTypes[] powerupTypes = PowerupTypes.values();
            generatePowerup(powerupTypes[randomPowerup]);
        }
    }

    public void setDebugState(DebugState debugState) {
        this.debugState = debugState;
    }

    public DebugState getDebugState() {
        return debugState;
    }

    public int getCurrentEpoch() {
        return currentEpoch;
    }

    public void setCurrentEpoch(int currentEpoch) {
        this.currentEpoch = currentEpoch;
    }

    public void setState(GameState state) {
        this.state = state;
    }

    public GameState getState() {
        return state;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }
}
