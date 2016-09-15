package com.zoomba.GameObjects.ObjectFactory.Objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;
import com.zoomba.GameObjects.ObjectFactory.Types.HazardTypes;
import com.zoomba.Services.Interfaces.Pickup;
import com.zoomba.Services.Interfaces.PlayerFocus;

/**
 * Created by ed on 29/08/2016.
 */
public abstract class Hazard extends GameObject implements Pickup, PlayerFocus {
    private int existence, lifetime;
    private HazardTypes hazardType;

    public Hazard(float x, float y, float radius, float orientation, float velocity, int lifetime,
                  int existence, HazardTypes hazardType) {
        super(new Vector2(x, y), radius, orientation, velocity);
        this.lifetime = lifetime;
        this.existence = existence;
        this.hazardType = hazardType;
        startLifetimeTimer();
    }

    public void startExistenceTimer() {
        Gdx.app.log("Timer", "Starting lifetime timer");
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                existence--;
            }
        }, 1f);
    }

    public void startLifetimeTimer() {
        Gdx.app.log("Timer", "Starting lifetime timer");
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                lifetime--;
            }
        }, 1f);
    }

    public int getExistence() {
        return existence;
    }

    public int getLifetime() {
        return lifetime;
    }

    public HazardTypes getHazardType() {
        return hazardType;
    }
}
