package com.zoomba.GameObjects.Circles;

import com.badlogic.gdx.graphics.Color;
import com.zoomba.GameObjects.ObjectFactory.Circle;

/**
 * Created by ed on 20/08/16.
 */
public class Slow extends Circle {
    public Slow(float x, float y, float radius, float orientation, Color color, float velocity, int id) {
        super(x, y, radius, orientation, color, velocity, id);
        super.onSpawn();
    }
}
