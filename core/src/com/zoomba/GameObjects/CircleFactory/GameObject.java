package com.zoomba.GameObjects.CircleFactory;

import java.util.Random;

/**
 * Created by ed on 09/08/16.
 */
public abstract class GameObject {
    float x, y, radius;
    CircleOrientation orientation;

    public GameObject(float x, float y, float radius, CircleOrientation orientation) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.orientation = orientation;
    }

    public void setX(float x) {
        this.x = x;
    }
    public void setY(float y) {
        this.y = y;
    }
    public void setOrientation(CircleOrientation orientation) {
        this.orientation = orientation;
    }

    public float getX() {
        return x;
    }
    public float getY() {
        return y;
    }
    public float getRadius() {
        return radius;
    }

    public CircleOrientation getOrientation() {
        return orientation;
    }
    public static CircleOrientation getRandomOrientation() {
        int random = new Random().nextInt(2);
        if (random == 0)
            return CircleOrientation.Leftwards;
        else return CircleOrientation.Rightwards;
    }
}
