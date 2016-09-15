package com.zoomba.GameObjects.ObjectFactory.Objects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;
import com.zoomba.Services.Interfaces.Pickup;

/**
 * Created by ed on 29/08/2016.
 */
public abstract class Powerup extends GameObject implements Pickup {
    private int existence;

    public Powerup(float x, float y, float radius, float orientation, Color color, float velocity) {
        super(new Vector2(x, y), radius, orientation, color, velocity);
    }

    public void startTimer() {
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                setExistence(getExistence() - 1);
            }
        }, 1f);
    }

    public int getExistence() {
        return existence;
    }

    public void setExistence(int existence) {
        this.existence = existence;
    }
}
