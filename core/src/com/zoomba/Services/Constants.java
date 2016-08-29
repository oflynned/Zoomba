package com.zoomba.Services;

import com.badlogic.gdx.graphics.Color;

/**
 * Created by ed on 09/08/16.
 */
public class Constants {
    //circle attributes
    public static final float CIRCLE_RADIUS = 50;
    public static final int CIRCLE_INITIAL_AMOUNT = 2;

    public static final float SLOW_VELOCITY = 2;
    public static final int SLOW_POINTS = (int) (SLOW_VELOCITY * 10);
    public static final float FAST_VELOCITY = 6;
    public static final int FAST_POINTS = (int) (FAST_VELOCITY * 10);

    //difficulty tiers
    public static final int TIER_1 = 3;
    public static final int TIER_2 = 5;
    public static final int TIER_3 = 7;

    //game duration lengths
    public static final int ONE_SECOND = 100;
    public static final int GAME_LENGTH = 30 * ONE_SECOND;

    //text scaling
    public static final float TITLE_SCALE = 5;
    public static final float REG_SCALE = 3;

    //multisampling
    public static final int ANTIALIASING = 8;

    //debug labels
    public static final String PHYSICS_DEBUG = "PhysicsDebug";
    public static final String PHYSICS_ATTRIBUTE_DEBUG = "PhysicsAttribute";
    public static final String GAME_SCREEN_DEBUG = "HighScore";
    public static final String GESTURE_CONTROLLER_DEBUG = "GestureController";
    public static final String OBJECT_DEBUG = "GameObject";

    //styles
    public static final Color BLUE_500 = new Color(0x2196f3ff);
    public static final Color RED_500  = new Color(0xf44336ff);
    public static final Color YELLOW_500 = new Color(0xffeb3bff);
}
