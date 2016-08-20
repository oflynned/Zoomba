package com.zoomba.GameObjects.CircleFactory;

import com.badlogic.gdx.Gdx;
import com.zoomba.GameObjects.Circles.Slow;

import java.util.Random;

/**
 * Created by ed on 20/08/16.
 */
public class CircleFactory extends Factory {
    @Override
    public Slow generateSlowCircle(ObjectTypes objectType, float radius, CircleOrientation orientation) {
        float x = generateCoords(Gdx.graphics.getWidth());
        float y = generateCoords(Gdx.graphics.getHeight());

        if (objectType.equals(ObjectTypes.Slow))
            return new Slow(x, y, radius, orientation);
        else return null;
    }

    private static float generateCoords(int limit) {
        return new Random().nextInt(limit);
    }
}
