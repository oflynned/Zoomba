package com.zoomba.Services;

import com.badlogic.gdx.graphics.Color;

/**
 * Created by ed on 09/08/16.
 */
public class Constants {
    public static final float CIRCLE_RADIUS = 50;
    public static final float CIRCLE_VELOCITY = 10;
    public static final int CIRCLE_AMOUNT = 50;

    //multisampling
    public static final int ANTIALIASING = 4;

    //styles
    public static final Color BLUE_500 = parseColor("2196F3");
    public static final Color RED_500  = parseColor("F44336");

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
