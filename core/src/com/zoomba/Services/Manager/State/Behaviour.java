package com.zoomba.Services.Manager.State;

import com.badlogic.gdx.Gdx;

import java.util.Random;

/**
 * Created by colinpuri on 22/08/2016.
 */
public class Behaviour {
    private static float width = Gdx.graphics.getWidth();
    private static float height = Gdx.graphics.getHeight();

    public static PhysicsState generateStateTransition() {
        switch(new Random().nextInt(2)) {
            case 0:
                return PhysicsState.Bounce;
            case 1:
                return PhysicsState.Teleport;
            default:
                return null;
        }
    }

    public static float collide(float x, float y, float orientation) {
        return 0;
    }
}
