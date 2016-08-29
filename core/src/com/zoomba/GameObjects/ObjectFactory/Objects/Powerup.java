package com.zoomba.GameObjects.ObjectFactory.Objects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by ed on 29/08/2016.
 */
public abstract class Powerup extends GameObject {
    public Powerup(float x, float y, float radius, float orientation, Color color, float velocity) {
        super(new Vector2(x, y), radius, orientation, color, velocity);
    }
}
