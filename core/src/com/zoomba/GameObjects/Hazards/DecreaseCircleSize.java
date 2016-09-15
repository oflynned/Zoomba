package com.zoomba.GameObjects.Hazards;

import com.zoomba.GameObjects.ObjectFactory.Objects.GameObject;
import com.zoomba.GameObjects.ObjectFactory.Objects.Hazard;
import com.zoomba.Services.Constants;

import java.util.ArrayList;

/**
 * Created by ed on 29/08/2016.
 */
public class DecreaseCircleSize extends Hazard {
    public DecreaseCircleSize(float x, float y, float radius, float orientation,
                              float velocity, int lifetime, int existence) {
        super(x, y, radius, orientation, velocity, lifetime, existence);
    }

    @Override
    public void onPickup(ArrayList<GameObject> gameObjects) {
        for (GameObject gameObject : gameObjects) {
            gameObject.setRadius(Constants.CIRCLE_REDUCED_RADIUS);
        }
        super.startTimer();
    }

    @Override
    public void onDestroy(ArrayList<GameObject> gameObjects) {
        for (GameObject gameObject : gameObjects) {
            gameObject.setRadius(Constants.CIRCLE_RADIUS);
        }
    }
}
