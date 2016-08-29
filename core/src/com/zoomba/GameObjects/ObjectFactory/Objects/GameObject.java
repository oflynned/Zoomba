package com.zoomba.GameObjects.ObjectFactory.Objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

import sun.misc.FloatingDecimal;

/**
 * Created by ed on 09/08/16.
 */
public abstract class GameObject {
    Vector2 position;
    double velocity;
    float radius, orientation;
    Color color;

    public GameObject(Vector2 position, float radius, float orientation, Color color, float velocity) {
        this.position = position;
        this.radius = radius;
        this.orientation = orientation;
        this.color = color;
        this.velocity = velocity;
    }

    public void setX(double x) {
        this.position.x = (float) x;
    }
    public void setY(double y) {
        this.position.y = (float) y;
    }
    public void setOrientation(float orientation) {
        this.orientation = orientation;
    }

    public float getXVel() {
        return (float) (Math.cos(Math.toRadians(getOrientation())) * getVelocity());
    }

    public float getYVel() {
        return (float) (Math.sin(Math.toRadians(getOrientation())) * getVelocity());
    }

    public double getVelocity() {
        return velocity;
    }

    public double getX() {
        return position.x;
    }

    public double getY() {
        return position.y;
    }

    public float getRadius() {
        return radius;
    }

    public float getOrientation() {
        return orientation;
    }

    public static int getRandomX() {
        return new Random().nextInt(Gdx.graphics.getWidth());
    }

    public static int getRandomY() {
        return new Random().nextInt(Gdx.graphics.getHeight());
    }

    public static float getRandomOrientation() {
        return new Random().nextInt(360);
    }

    public Color getColor() {
        return color;
    }
}
