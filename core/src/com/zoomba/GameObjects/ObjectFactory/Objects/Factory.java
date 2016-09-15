package com.zoomba.GameObjects.ObjectFactory.Objects;


import com.zoomba.GameObjects.ObjectFactory.Types.CircleTypes;
import com.zoomba.GameObjects.ObjectFactory.Types.HazardTypes;
import com.zoomba.GameObjects.ObjectFactory.Types.PowerupTypes;

/**
 * Created by ed on 20/08/16.
 */
public abstract class Factory {
    public abstract Circle generateCircle(CircleTypes objectType);
    public abstract Powerup generatePowerup(PowerupTypes powerupTypes);
    public abstract Hazard generateHazard(HazardTypes hazardTypes);
}
