package com.zoomba.GameObjects.ObjectFactory.Factories;

import com.zoomba.GameObjects.Circles.Fast;
import com.zoomba.GameObjects.Circles.Slow;
import com.zoomba.GameObjects.ObjectFactory.Objects.Circle;
import com.zoomba.GameObjects.ObjectFactory.Objects.Factory;
import com.zoomba.GameObjects.ObjectFactory.Objects.GameObject;
import com.zoomba.GameObjects.ObjectFactory.Objects.Hazard;
import com.zoomba.GameObjects.ObjectFactory.Objects.Powerup;
import com.zoomba.GameObjects.ObjectFactory.Types.CircleTypes;
import com.zoomba.GameObjects.ObjectFactory.Types.HazardTypes;
import com.zoomba.GameObjects.ObjectFactory.Types.PowerupTypes;
import com.zoomba.Services.Manager.State.Manager;
import com.zoomba.UI.Modes.HighScore;

import java.util.Random;

/**
 * Created by ed on 20/08/16.
 */
public class CircleFactory extends Factory {

    @Override
    public Circle generateCircle(CircleTypes objectType) {
        if (objectType.equals(CircleTypes.Slow))
            return new Slow(GameObject.getRandomX(), GameObject.getRandomY());
        else if (objectType.equals(CircleTypes.Fast))
            return new Fast(GameObject.getRandomX(), GameObject.getRandomY());
        else return null;
    }

    @Override
    public Powerup generatePowerup(PowerupTypes powerupTypes) {
        return null;
    }

    @Override
    public Hazard generateHazard(HazardTypes hazardTypes) {
        return null;
    }

}
