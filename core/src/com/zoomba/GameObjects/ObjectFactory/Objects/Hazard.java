package com.zoomba.GameObjects.ObjectFactory.Objects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by ed on 29/08/2016.
 */
public abstract class Hazard extends GameObject {
    private long lifetime;
    private long effectLifetime;
    private boolean isEffect;

    public Hazard(float x, float y, float radius, float orientation, Color color, float velocity) {
        super(new Vector2(x, y), radius, orientation, color, velocity);
    }

    public Hazard(float x, float y, float radius, float orientation, Color color, float velocity,
                  long lifetime, long effectLifetime) {
        super(new Vector2(x, y), radius, orientation, color, velocity);
        this.lifetime = lifetime;
        this.effectLifetime = effectLifetime;
        this.isEffect = false;
    }

    public void exist() {
        //countdown existence if invoked or not
    }

    public long getLifetime() {
        return lifetime;
    }

    public long getEffectLifetime() {
        return effectLifetime;
    }

    public boolean isEffect() {
        return isEffect;
    }
}
