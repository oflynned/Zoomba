package com.zoomba.GameObjects.ObjectFactory.Objects;


import com.zoomba.GameObjects.ObjectFactory.Types.ObjectTypes;

/**
 * Created by ed on 20/08/16.
 */
public abstract class Factory {
    public abstract Circle generateCircle(ObjectTypes objectType);
}
