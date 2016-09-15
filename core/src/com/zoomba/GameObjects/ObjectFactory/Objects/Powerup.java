package com.zoomba.GameObjects.ObjectFactory.Objects;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;
import com.zoomba.GameObjects.ObjectFactory.Types.PowerupTypes;
import com.zoomba.Services.Interfaces.Pickup;
import com.zoomba.Services.Interfaces.PlayerFocus;

/**
 * Created by ed on 29/08/2016.
 */
public abstract class Powerup extends GameObject implements Pickup, PlayerFocus {
    private int existence, lifetime;
    private PowerupTypes powerupType;

    public Powerup(float x, float y, float radius, float orientation, float velocity,
                   int lifetime, int existence, PowerupTypes powerupType) {
        super(new Vector2(x, y), radius, orientation, velocity);
        this.lifetime = lifetime;
        this.existence = existence;
        this.powerupType = powerupType;

        onLifetimeTimer();
        onPickupTimer();
    }

    public void onLifetimeTimer() {
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                lifetime--;
            }
        }, 1f);
    }

    public void onPickupTimer() {
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

    public PowerupTypes getPowerupType() {
        return powerupType;
    }
}
