package com.zoomba.GameObjects.ObjectFactory.Factories;

import com.zoomba.GameObjects.Circles.Fast;
import com.zoomba.GameObjects.Circles.Slow;
import com.zoomba.GameObjects.ObjectFactory.Objects.Circle;
import com.zoomba.GameObjects.ObjectFactory.Objects.Factory;
import com.zoomba.GameObjects.ObjectFactory.Types.ObjectTypes;
import com.zoomba.UI.Modes.HighScore;

import java.util.Random;

/**
 * Created by ed on 20/08/16.
 */
public class CircleFactory extends Factory {

    @Override
    public Circle generateCircle(ObjectTypes objectType) {
        float x = generateCoords(HighScore.width);
        float y = generateCoords(HighScore.height);

        if (objectType.equals(ObjectTypes.Slow))
            return new Slow(x, y);
        else if (objectType.equals(ObjectTypes.Fast))
            return new Fast(x, y);
        else return null;
    }

    private static float generateCoords(float limit) {
        return new Random().nextInt((int)limit);
    }

}
