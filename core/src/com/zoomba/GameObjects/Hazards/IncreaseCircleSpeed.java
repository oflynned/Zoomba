package com.zoomba.GameObjects.Hazards;

import com.zoomba.GameObjects.ObjectFactory.Objects.Circle;
import com.zoomba.GameObjects.ObjectFactory.Objects.GameObject;
import com.zoomba.GameObjects.ObjectFactory.Objects.Hazard;
import com.zoomba.GameObjects.ObjectFactory.Types.HazardTypes;

import java.util.ArrayList;

/**
 * Created by ed on 29/08/2016.
 */
public class IncreaseCircleSpeed extends Hazard {
    public IncreaseCircleSpeed(float x, float y, float radius, float orientation, float velocity,
                               int lifetime, int existence) {
        super(x, y, radius, orientation, velocity, lifetime, existence, HazardTypes.IncreaseCircleSpeed);
    }

    @Override
    public void onUpdate() {

    }

    @Override
    public void onPickup(ArrayList<Circle> circles) {

    }

    @Override
    public void onDestroy(ArrayList<Circle> circles) {

    }

    @Override
    public void onFocus() {

    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void onFailed() {

    }
}
