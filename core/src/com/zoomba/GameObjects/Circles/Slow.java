package com.zoomba.GameObjects.Circles;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.zoomba.GameObjects.ObjectFactory.Circle;
import com.zoomba.GameObjects.ObjectFactory.GameObject;
import com.zoomba.Services.Constants;

/**
 * Created by ed on 20/08/16.
 */
public class Slow extends Circle {

    public Slow(float x, float y, float radius, float orientation, Color color, float velocity) {
        super(x, y, radius, orientation, color, velocity);
    }

    @Override
    public void onSpawn() {
        setX(GameObject.getRandomX());
        setY(GameObject.getRandomY());
        setOrientation(GameObject.getRandomOrientation());
        System.out.println("onSpawn() @ (" + getX() + "," + getY() + ") with vels " + getXVel() + " " + getYVel());
    }

    @Override
    public void onDraw(SpriteBatch batch, ShapeRenderer renderer) {
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.circle(getX() + getRadius(), getY(), getRadius());
        renderer.setColor(Constants.RED_500);
        renderer.end();
    }

    @Override
    public void onMove() {
        System.out.println("onMove() @ (" + getX() + "," + getY() + ") moving " + getOrientation());
        if(isCollision()) {
            onCollision();
        } else {
            setX(getX() + getXVel());
            setY(getY() + getYVel());
        }
    }

    @Override
    public void onCollision() {
        System.out.println("onCollision() @ (" + getX() + "," + getY() + ")");

        onSpawn();
        //super.setOrientation(Behaviour.collide(getX(), getY(), getOrientation()));
    }

    @Override
    public void onCapture() {

    }

    @Override
    public void onDestroy() {

    }

    public boolean isCollision() {
        return getX() <= 0 || getX() >= super.getWidth() || getY() <= 0 || getY() >= super.getHeight();
    }
}
