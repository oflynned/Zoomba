package com.zoomba.GameObjects.ObjectFactory.Objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.zoomba.Services.Constants;
import com.zoomba.Services.Manager.State.Behaviour;
import com.zoomba.Services.Manager.Types.Direction;
import com.zoomba.UI.Modes.HighScore;

import java.util.Random;

import sun.misc.FloatingDecimal;

/**
 * Created by ed on 09/08/16.
 */
public abstract class GameObject {
    Vector2 position;
    double velocity;
    float radius, orientation;

    public GameObject(Vector2 position, float radius, float orientation, float velocity) {
        this.position = position;
        this.radius = radius;
        this.orientation = orientation;
        this.velocity = velocity;
    }

    public abstract void onUpdate();

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

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public void setVelocity(double velocity) {
        this.velocity = velocity;
    }

    public void setRadius(float radius) {
        this.radius = radius;
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

    public void onDraw(Texture texture, Batch batch) {
        batch.draw(texture, (float) getX(), (float) (getY() - getRadius()), getRadius() * 2, getRadius() * 2);
    }

    public void onMove() {
        if(isCollision()) {
            onCollision();
        } else {
            setX(getX() + getXVel());
            setY(getY() + getYVel());
        }
    }

    public void onCollision() {
        Gdx.app.log(Constants.OBJECT_DEBUG, "onCollision() @ (" + getX() + "," + getY() +
                ") (" + getClass() + ")");
        if(isLeftCollision()) {
            Behaviour.generateStateTransition(Direction.Left, this);
        } else if (isTopCollision()) {
            Behaviour.generateStateTransition(Direction.Top, this);
        } else if (isRightCollision()) {
            Behaviour.generateStateTransition(Direction.Right, this);
        } else if (isBottomCollision()) {
            Behaviour.generateStateTransition(Direction.Bottom, this);
        }
    }

    public boolean isCollision() {
        return isLeftCollision() || isTopCollision() || isRightCollision() || isBottomCollision();
    }

    public boolean isLeftCollision() {
        return getX()<= 0 ;
    }

    public boolean isTopCollision() {
        return getY() + getRadius() >= HighScore.height;
    }

    public boolean isRightCollision() {
        return getX() + getRadius()*2 >= HighScore.width;
    }

    public boolean isBottomCollision() {
        return getY() - getRadius() <= 0;
    }
}
