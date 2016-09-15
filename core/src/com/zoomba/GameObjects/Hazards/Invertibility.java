package com.zoomba.GameObjects.Hazards;

import com.zoomba.GameObjects.ObjectFactory.Objects.GameObject;
import com.zoomba.GameObjects.ObjectFactory.Objects.Hazard;

import java.util.ArrayList;

/**
 * Created by ed on 29/08/2016.
 */
public class Invertibility extends Hazard {
    public Invertibility(float x, float y, float radius, float orientation, float velocity,
                         int lifetime, int existence) {
        super(x, y, radius, orientation, velocity, lifetime, existence);
    }

    @Override
    public void onPickup(ArrayList<GameObject> gameObjects) {

    }

    @Override
    public void onDestroy(ArrayList<GameObject> gameObjects) {

    }
}
