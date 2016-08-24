package com.zoomba.GameObjects.ObjectFactory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;

import java.util.Random;

import sun.misc.FloatingDecimal;

/**
 * Created by ed on 09/08/16.
 */
public abstract class GameObject {
    float width = Gdx.graphics.getWidth();
    float height = Gdx.graphics.getHeight();
    float x, y, radius;
    Color color;
    float orientation;
    double velocity;
    int id;

    public GameObject(float x, float y, float radius, float orientation, Color color, float velocity, int id) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.orientation = orientation;
        this.color = color;
        this.velocity = velocity;
        this.id = id;
    }

    public void setX(float x) {
        this.x = x;
    }
    public void setY(float y) {
        this.y = y;
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

    public void setVelocity(double velocity) {
        this.velocity = velocity;
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

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public Color getColor() {
        return color;
    }

    public int getId() {
        return id;
    }
}
