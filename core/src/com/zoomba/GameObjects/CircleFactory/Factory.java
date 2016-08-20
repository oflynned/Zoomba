package com.zoomba.GameObjects.CircleFactory;

import com.zoomba.GameObjects.Circles.Slow;

/**
 * Created by ed on 20/08/16.
 */
public abstract class Factory {
    public abstract Slow generateSlowCircle(ObjectTypes objectType, float radius, CircleOrientation orientation);
}
