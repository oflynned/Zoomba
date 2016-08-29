package com.zoomba.Services.Manager.State;

import com.badlogic.gdx.utils.Timer;
import com.zoomba.GameObjects.Circles.Fast;
import com.zoomba.GameObjects.Circles.Slow;
import com.zoomba.GameObjects.ObjectFactory.Objects.Circle;
import com.zoomba.Services.Constants;
import com.zoomba.Services.Manager.Types.DebugState;
import com.zoomba.Services.Manager.Types.GameState;

/**
 * Created by ed on 09/08/16.
 */
public class Manager {
    private int currentEpoch;
    private GameState state;
    private int points;
    private int difficulty;
    private DebugState debugState = DebugState.Normal;

    private static Manager manager = null;

    public static Manager getInstance() {
        manager = manager == null ? new Manager() : manager;
        return manager;
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
            state = GameState.Ongoing;
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
