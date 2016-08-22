package com.zoomba.GameObjects.ObjectFactory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.zoomba.GameObjects.Circles.Slow;
import com.zoomba.Services.Constants;

import java.util.Random;

/**
 * Created by ed on 20/08/16.
 */
public class CircleFactory extends Factory {
    @Override
    public Slow generateCircle(ObjectTypes objectType, float radius, float orientation, Color color, float velocity) {
        float x = Gdx.graphics.getWidth() / 2; //generateCoords(Gdx.graphics.getWidth());
        float y = Gdx.graphics.getHeight() / 2; //generateCoords(Gdx.graphics.getHeight());

        if (objectType.equals(ObjectTypes.Slow))
            return new Slow(x, y, radius, orientation, color, velocity);
        else return null;
    }

    private static float generateCoords(int limit) {
        return new Random().nextInt(limit);
    }
}
