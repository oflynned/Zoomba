package com.zoomba.Services;

import com.badlogic.gdx.graphics.Color;

/**
 * Created by ed on 09/08/16.
 */
public class Constants {
    public static final float CIRCLE_RADIUS = 50;
    public static final int CIRCLE_AMOUNT = 2;

    public static final float SLOW_VELOCITY = 2;
    public static final int SLOW_POINTS = 5;
    public static final float FAST_VELOCITY = 12;

    public static final int ONE_SECOND = 100;
    public static final int GAME_LENGTH = ONE_SECOND;

    //multisampling
    public static final int ANTIALIASING = 8;

    //debug labels
    public static final String PHYSICS_DEBUG = "PhysicsDebug";
    public static final String PHYSICS_ATTRIBUTE_DEBUG = "PhysicsAttribute";
    public static final String GAME_SCREEN_DEBUG = "GameScreen";
    public static final String GESTURE_CONTROLLER_DEBUG = "GestureController";
    public static final String OBJECT_DEBUG = "GameObject";

    //styles
    public static final Color BLUE_500 = new Color(0x2196f3ff);
    public static final Color RED_500  = new Color(0xf44336ff);
    public static final Color YELLOW_500 = new Color(0xffeb3bff);

    public static Color parseColor(String hex) {
        String s1 = hex.substring(0, 2);
        int v1 = Integer.parseInt(s1, 16);
        float f1 = (float) v1 / 255f;

        String s2 = hex.substring(2, 4);
        int v2 = Integer.parseInt(s2, 16);
        float f2 = (float) v2 / 255f;

        String s3 = hex.substring(4, 6);
        int v3 = Integer.parseInt(s3, 16);
        float f3 = (float) v3 / 255f;

        return new Color(f1, f2, f3, 1);
    }
}
