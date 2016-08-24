package com.zoomba.GameObjects.ObjectFactory;

import com.badlogic.gdx.graphics.Color;
import com.zoomba.GameObjects.Circles.Slow;
import com.zoomba.UI.Screens.GameScreen;

import java.util.Random;

/**
 * Created by ed on 20/08/16.
 */
public class CircleFactory extends Factory {

    private int id = 0;

    @Override
    public Slow generateCircle(ObjectTypes objectType, float radius, float orientation, Color color, float velocity) {
        float x = generateCoords(GameScreen.width);
        float y = generateCoords(GameScreen.height);

        if (objectType.equals(ObjectTypes.Slow))
            return new Slow(x, y, radius, orientation, color, velocity, generateId());
        else return null;
    }

    private static float generateCoords(float limit) {
        return new Random().nextInt((int)limit);
    }

    private int generateId() {
        return ++id;
    }
}
