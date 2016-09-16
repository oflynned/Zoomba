package com.zoomba.GameObjects.Hazards;

import com.zoomba.GameObjects.ObjectFactory.Objects.Circle;
import com.zoomba.GameObjects.ObjectFactory.Objects.Hazard;
import com.zoomba.GameObjects.ObjectFactory.Types.HazardTypes;

import java.util.ArrayList;

/**
 * Created by ed on 29/08/2016.
 */
public class DecreaseCircleSize extends Hazard {

    public DecreaseCircleSize(float x, float y, float radius, float orientation,
                              float velocity, int lifetime, int existence) {
        super(x, y, radius, orientation, velocity, lifetime, existence, HazardTypes.DecreaseCircleSize);
    }

    @Override
    public void onPickup(ArrayList<Circle> circles) {
        setInvoked(true);
        for (Circle circle : circles) circle.setRadius(circle.getRadius() / 2);
    }

    @Override
    public void onDestroy(ArrayList<Circle> circles) {
        for (Circle circle : circles) circle.setRadius(circle.getRadius() * 2);
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
}
