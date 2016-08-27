package com.zoomba.Services.Manager.State;

import com.zoomba.GameObjects.Circles.Slow;
import com.zoomba.GameObjects.ObjectFactory.Circle;
import com.zoomba.Services.Constants;

/**
 * Created by ed on 09/08/16.
 */
public class Manager {
    private int currentEpoch;
    private GameState state;
    private int points;
    private int difficulty;

    private static Manager manager = null;

    public static Manager getInstance() {
        manager = manager == null ? new Manager() : manager;
        return manager;
    }

    public void countdown(int entityCount) {
        if(getCurrentEpoch() > Constants.ONE_SECOND) {
            currentEpoch--;
            state = GameState.Ongoing;
        } else {
            // loss : win
            state = entityCount > 0 ? GameState.Win : GameState.Win;
        }
    }

    public void incrementScore(Circle circle) {
        if (circle.getClass().equals(Slow.class)) {
            points += Constants.SLOW_POINTS;
        }
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
