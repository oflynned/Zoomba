package com.zoomba.GameObjects.Circles;

import com.zoomba.GameObjects.ObjectFactory.Circle;
import com.zoomba.Services.Constants;

/**
 * Created by ed on 27/08/16.
 */
public class Fast extends Circle {
    public Fast(float x, float y, float radius, float orientation, int id) {
        super(x, y, radius, orientation, Constants.YELLOW_500, Constants.FAST_VELOCITY, id);
        super.onSpawn();
    }
}
