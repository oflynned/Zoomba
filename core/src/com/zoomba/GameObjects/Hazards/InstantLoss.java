package com.zoomba.GameObjects.Hazards;

import com.zoomba.GameObjects.ObjectFactory.Objects.GameObject;
import com.zoomba.GameObjects.ObjectFactory.Objects.Hazard;

import java.util.ArrayList;

/**
 * Created by ed on 15/09/2016
 */
public class InstantLoss extends Hazard {
    public InstantLoss(float x, float y, float radius, float orientation, float velocity,
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
