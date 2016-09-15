package com.zoomba.GameObjects.Hazards;

import com.badlogic.gdx.Gdx;
import com.zoomba.GameObjects.ObjectFactory.Objects.Circle;
import com.zoomba.GameObjects.ObjectFactory.Objects.Hazard;
import com.zoomba.GameObjects.ObjectFactory.Types.HazardTypes;
import com.zoomba.Services.Constants;

import java.util.ArrayList;

/**
 * Created by ed on 29/08/2016.
 */
public class DecreaseCircleSize extends Hazard {
    private boolean isAlive = true;

    public DecreaseCircleSize(float x, float y, float radius, float orientation,
                              float velocity, int lifetime, int existence) {
        super(x, y, radius, orientation, velocity, lifetime, existence, HazardTypes.DecreaseCircleSize);
        super.startLifetimeTimer();
        Gdx.app.log("Hazard", "Constructor");
    }

    @Override
    public void onUpdate() {
        if (super.getLifetime() < 0) isAlive = false;
        if (super.getExistence() < 0) isAlive = false;
    }

    @Override
    public void onPickup(ArrayList<Circle> circles) {
        for (Circle circle : circles) {
            circle.setRadius(Constants.CIRCLE_REDUCED_RADIUS);
        }
        super.startExistenceTimer();
    }

    @Override
    public void onDestroy(ArrayList<Circle> circles) {
        for (Circle circle : circles) {
            circle.setRadius(Constants.CIRCLE_RADIUS);
        }
    }

    @Override
    public void onFocus() {
        //on circle entering threshold of being focused on
    }

    @Override
    public void onSuccess() {
        //on circle successfully being captured
    }

    @Override
    public void onFailed() {
        //on circle breaking free
    }

    public boolean isAlive() {
        return isAlive;
    }
}
