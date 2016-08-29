package com.zoomba.GameObjects.Hazards;

import com.badlogic.gdx.graphics.Color;
import com.zoomba.GameObjects.ObjectFactory.Objects.Hazard;
import com.zoomba.Services.Constants;
import com.zoomba.Services.Interfaces.Pickup;

/**
 * Created by ed on 29/08/2016.
 */
public class Invertibility extends Hazard implements Pickup {
    private static final long EFFECT_TIME = Constants.ONE_SECOND * 5;

    public Invertibility(float x, float y, float radius, float orientation, Color color, float velocity,
                  long lifetime, long effectLifetime) {
        super(x, y, radius, orientation, color, velocity, lifetime, effectLifetime);
    }

    @Override
    public void effect() {
        // scrolling left inverts to scrolling right
        // scrolling up inverts to scrolling down
        // ...
    }
}
