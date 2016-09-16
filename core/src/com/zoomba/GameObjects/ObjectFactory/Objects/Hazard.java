package com.zoomba.GameObjects.ObjectFactory.Objects;

import com.badlogic.gdx.math.Vector2;
import com.zoomba.GameObjects.ObjectFactory.Types.HazardTypes;
import com.zoomba.Services.Interfaces.Pickup;
import com.zoomba.Services.Interfaces.PlayerFocus;

/**
 * Created by ed on 29/08/2016.
 */
public abstract class Hazard extends GameObject implements Pickup, PlayerFocus {
    private int existence, lifetime;
    private HazardTypes hazardType;
    private boolean isInvoked = false, isAlive = true, isPickupUp = false;

    public Hazard(float x, float y, float radius, float orientation, float velocity, int lifetime,
                  int existence, HazardTypes hazardType) {
        super(new Vector2(x, y), radius, orientation, velocity);
        this.lifetime = lifetime;
        this.existence = existence;
        this.hazardType = hazardType;
    }

    @Override
    public void onUpdate() {
        if (getLifetime() < 0 || getExistence() < 0) setAlive(false);
        if (isInvoked()) lifetime--; else existence--;
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

    public boolean isInvoked() {
        return isInvoked;
    }

    public void setInvoked(boolean invoked) {
        isInvoked = invoked;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public boolean isPickupUp() {
        return isPickupUp;
    }

    public void setPickupUp(boolean pickupUp) {
        isPickupUp = pickupUp;
    }
}
