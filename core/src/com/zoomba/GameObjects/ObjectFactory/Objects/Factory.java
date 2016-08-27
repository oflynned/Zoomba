package com.zoomba.GameObjects.ObjectFactory.Objects;

import com.zoomba.GameObjects.CircleFactory.ObjectTypes;

/**
 * Created by ed on 20/08/16.
 */
public abstract class Factory {
    public abstract GameObject generate(ObjectTypes objectType);
}
