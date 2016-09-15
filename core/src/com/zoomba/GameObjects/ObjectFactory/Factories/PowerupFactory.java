package com.zoomba.GameObjects.ObjectFactory.Factories;

import com.badlogic.gdx.Gdx;
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
import com.zoomba.Powerups.DecreaseCircleSpeed;
import com.zoomba.Powerups.IncreaseCircleSize;
import com.zoomba.Powerups.IncreaseScrollSpeed;
import com.zoomba.Powerups.IncreaseZoomSpeed;
import com.zoomba.Services.Constants;

/**
 * Created by ed on 27/08/16.
 */
public class PowerupFactory extends Factory {
    @Override
    public Circle generateCircle(CircleTypes objectType) {
        return null;
    }

    @Override
    public Powerup generatePowerup(PowerupTypes powerupTypes) {
        Gdx.app.log("Hazard", "Generating hazard (" + powerupTypes.name() + ")");
        float x = GameObject.getRandomX();
        float y = GameObject.getRandomY();
        float orientation = GameObject.getRandomOrientation();
        float radius = Constants.CIRCLE_RADIUS;
        float velocity = Constants.SLOW_VELOCITY;
        int lifetime = Constants.PICKUP_EFFECT;
        int existence = Constants.PICKUP_EFFECT;

        if (powerupTypes.equals(PowerupTypes.DecreaseCircleSpeed)) {
            return new DecreaseCircleSpeed(x, y, radius, orientation, velocity, lifetime, existence);
        } else if (powerupTypes.equals(PowerupTypes.IncreaseCircleSize)) {
            return new IncreaseCircleSize(x, y, radius, orientation, velocity, lifetime, existence);
        } else if (powerupTypes.equals(PowerupTypes.IncreaseScrollSpeed)) {
            return new IncreaseScrollSpeed(x, y, radius, orientation, velocity, lifetime, existence);
        } else if (powerupTypes.equals(PowerupTypes.IncreaseZoomSpeed)) {
            return new IncreaseZoomSpeed(x, y, radius, orientation, velocity, lifetime, existence);
        }
        return null;
    }

    @Override
    public Hazard generateHazard(HazardTypes hazardTypes) {
        return null;
    }
}
