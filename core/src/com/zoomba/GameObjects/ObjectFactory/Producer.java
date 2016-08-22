package com.zoomba.GameObjects.ObjectFactory;

/**
 * Created by ed on 20/08/16.
 */
public class Producer {
    public static Factory getFactory(FactoryTypes factoryType) {
        if(factoryType.equals(FactoryTypes.Circle))
            return new CircleFactory();
        else return null;
    }
}
