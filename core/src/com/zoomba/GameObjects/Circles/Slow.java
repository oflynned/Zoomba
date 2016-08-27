package com.zoomba.GameObjects.Circles;

import com.zoomba.GameObjects.ObjectFactory.Circle;
import com.zoomba.Services.Constants;

/**
 * Created by ed on 20/08/16.
 */
public class Slow extends Circle {
    public Slow(float x, float y, float radius, float orientation, int id) {
        super(x, y, radius, orientation, Constants.RED_500, Constants.SLOW_VELOCITY, id);
        super.onSpawn();
    }
}
