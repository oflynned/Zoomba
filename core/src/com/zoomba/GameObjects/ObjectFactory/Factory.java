package com.zoomba.GameObjects.ObjectFactory;

import com.badlogic.gdx.graphics.Color;

/**
 * Created by ed on 20/08/16.
 */
public abstract class Factory {
    public abstract Circle generateCircle(ObjectTypes objectType, float radius, float orientation,
                                          Color color, float velocity);
}
