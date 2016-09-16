package com.zoomba.GameObjects.ObjectFactory.Objects;

import com.badlogic.gdx.math.Vector2;
import com.zoomba.GameObjects.ObjectFactory.Types.PowerupTypes;
import com.zoomba.Services.Interfaces.Pickup;
import com.zoomba.Services.Interfaces.PlayerFocus;

/**
 * Created by ed on 29/08/2016.
 */
public abstract class Powerup extends GameObject implements Pickup, PlayerFocus {
    private int existence, lifetime;
    private PowerupTypes powerupType;
    private boolean isInvoked;

    public Powerup(float x, float y, float radius, float orientation, float velocity,
                   int lifetime, int existence, PowerupTypes powerupType) {
        super(new Vector2(x, y), radius, orientation, velocity);
        this.lifetime = lifetime;
        this.existence = existence;
        this.powerupType = powerupType;
        setInvoked(false);
    }

    @Override
    public void onUpdate() {
        if (isInvoked()) lifetime--; else existence--;
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

    public boolean isInvoked() {
        return isInvoked;
    }

    public void setInvoked(boolean invoked) {
        isInvoked = invoked;
    }
}
