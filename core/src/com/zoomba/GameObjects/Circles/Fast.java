package com.zoomba.GameObjects.Circles;

import com.badlogic.gdx.math.Vector2;
import com.zoomba.GameObjects.ObjectFactory.Objects.Circle;
import com.zoomba.Services.Constants;

/**
 * Created by ed on 27/08/16.
 */
public class Fast extends Circle {
    public Fast(float x, float y) {
        super(new Vector2(x, y), Constants.FAST_VELOCITY);
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
