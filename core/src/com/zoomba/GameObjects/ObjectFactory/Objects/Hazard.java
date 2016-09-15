package com.zoomba.GameObjects.ObjectFactory.Objects;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;
import com.zoomba.Services.Interfaces.Pickup;

/**
 * Created by ed on 29/08/2016.
 */
public abstract class Hazard extends GameObject implements Pickup {
    private int existence, lifetime;

    public Hazard(float x, float y, float radius, float orientation, float velocity, int lifetime, int existence) {
        super(new Vector2(x, y), radius, orientation, velocity);
        this.lifetime = lifetime;
        this.existence = existence;
    }

    public void startTimer() {
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                existence--;
            }
        }, 1f);
    }

    public int getExistence() {
        return existence;
    }

    public int getLifetime() {
        return lifetime;
    }
}
