package com.zoomba.GameObjects.ObjectFactory.Factories;

import com.zoomba.GameObjects.Hazards.DecreaseCircleSize;
import com.zoomba.GameObjects.Hazards.DecreaseScrollSpeed;
import com.zoomba.GameObjects.Hazards.DecreaseZoomSpeed;
import com.zoomba.GameObjects.Hazards.IncreaseCircleSpeed;
import com.zoomba.GameObjects.Hazards.Invertibility;
import com.zoomba.GameObjects.Hazards.Invisibility;
import com.zoomba.GameObjects.ObjectFactory.Objects.Circle;
import com.zoomba.GameObjects.ObjectFactory.Objects.GameObject;
import com.zoomba.GameObjects.ObjectFactory.Objects.Hazard;
import com.zoomba.GameObjects.ObjectFactory.Objects.Powerup;
import com.zoomba.GameObjects.ObjectFactory.Types.CircleTypes;
import com.zoomba.GameObjects.ObjectFactory.Objects.Factory;
import com.zoomba.GameObjects.ObjectFactory.Types.HazardTypes;
import com.zoomba.GameObjects.ObjectFactory.Types.PowerupTypes;
import com.zoomba.Services.Constants;
import com.zoomba.Services.Manager.State.Manager;
import com.zoomba.UI.Modes.HighScore;

/**
 * Created by ed on 27/08/16.
 */
public class HazardFactory extends Factory {
    @Override
    public Circle generateCircle(CircleTypes objectType) {
        return null;
    }

    @Override
    public Powerup generatePowerup(PowerupTypes powerupTypes) {
        return null;
    }

    @Override
    public Hazard generateHazard(HazardTypes hazardTypes) {
        float x = GameObject.getRandomX();
        float y = GameObject.getRandomY();
        float orientation = GameObject.getRandomOrientation();
        float radius = Constants.CIRCLE_RADIUS;
        float velocity = Constants.SLOW_VELOCITY;
        int lifetime = Constants.PICKUP_EFFECT;
        int existence = Constants.PICKUP_EFFECT;

        if (hazardTypes.equals(HazardTypes.DecreaseCircleSize)) {
            return new DecreaseCircleSize(x, y, radius, orientation, velocity, lifetime, existence);
        } else if (hazardTypes.equals(HazardTypes.DecreaseScrollSpeed)) {
            return new DecreaseScrollSpeed(x, y, radius, orientation, velocity, lifetime, existence);
        } else if (hazardTypes.equals(HazardTypes.DecreaseZoomSpeed)) {
            return new DecreaseZoomSpeed(x, y, radius, orientation, velocity, lifetime, existence);
        } else if (hazardTypes.equals(HazardTypes.Invertibility)) {
            return new Invertibility(x, y, radius, orientation, velocity, lifetime, existence);
        } else if (hazardTypes.equals(HazardTypes.Invisibility)) {
            return new Invisibility(x, y, radius, orientation, velocity, lifetime, existence);
        } else if (hazardTypes.equals(HazardTypes.IncreaseCircleSpeed)) {
            return new IncreaseCircleSpeed(x, y, radius, orientation, velocity, lifetime, existence);
        }
        return null;
    }
}
