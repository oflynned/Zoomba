package com.zoomba.GameObjects.Circles;

import com.badlogic.gdx.math.Vector2;
import com.zoomba.GameObjects.ObjectFactory.Objects.Circle;
import com.zoomba.Services.Constants;

/**
 * Created by ed on 20/08/16.
 */
public class Slow extends Circle {
    public Slow(float x, float y) {
        super(new Vector2(x, y), Constants.SLOW_VELOCITY);
    }

    @Override
    public void onUpdate() {

    }

    @Override
    public void onFocus() {

    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void onFailed() {

    }
}
