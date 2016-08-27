package com.zoomba.GameObjects.ObjectFactory.Objects;

import com.zoomba.GameObjects.ObjectFactory.Factories.CircleFactory;
import com.zoomba.GameObjects.ObjectFactory.Factories.HazardFactory;
import com.zoomba.GameObjects.ObjectFactory.Factories.PowerupFactory;
import com.zoomba.GameObjects.ObjectFactory.Types.FactoryTypes;

/**
 * Created by ed on 20/08/16.
 */
public class Producer {
    public static Factory getFactory(FactoryTypes factoryType) {
        if(factoryType.equals(FactoryTypes.Circle)) return new CircleFactory();
        else if(factoryType.equals(FactoryTypes.Powerups)) return new PowerupFactory();
        else if(factoryType.equals(FactoryTypes.Hazards)) return new HazardFactory();
        else return null;
    }
}
