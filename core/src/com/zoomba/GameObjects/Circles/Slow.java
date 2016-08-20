package com.zoomba.GameObjects.Circles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.zoomba.GameObjects.CircleFactory.Circle;
import com.zoomba.GameObjects.CircleFactory.CircleOrientation;
import com.zoomba.GameObjects.CircleFactory.GameObject;
import com.zoomba.Services.Constants;

/**
 * Created by ed on 20/08/16.
 */
public class Slow extends GameObject implements Circle {
    float width = Gdx.graphics.getWidth();
    float height = Gdx.graphics.getHeight();

    public Slow(float x, float y, float radius, CircleOrientation orientation) {
        super(x, y, radius, orientation);
        onSpawn();
    }

    @Override
    public void onSpawn() {
        System.out.println("onSpawn()");
    }

    @Override
    public void onDraw(SpriteBatch batch, ShapeRenderer renderer, float scale) {
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.circle(getX() + getRadius(), getY(), getRadius() * scale);
        renderer.setColor(Color.BLACK);
        renderer.end();
    }

    @Override
    public void onMove() {
        System.out.println(getX() + " " + getY() + " " + getOrientation());
        if(getX() <= 0 || getX() >= width || getY() <= 0 || getY() >= height) {
            onCollision();
        } else {
            if(getOrientation().equals(CircleOrientation.Leftwards))
                setX(getX() - Constants.CIRCLE_VELOCITY);
            else if (getOrientation().equals(CircleOrientation.Rightwards))
                setX(getX() + Constants.CIRCLE_VELOCITY);
        }
    }

    @Override
    public void onCollision() {
        System.out.println("onCollision()");
        if(getX() == width && getOrientation().equals(CircleOrientation.Rightwards)) {
            setOrientation(CircleOrientation.Leftwards);
            setX(getX() - 20);
        } else if (getX() == 0 && getOrientation().equals(CircleOrientation.Leftwards)) {
            setOrientation(CircleOrientation.Rightwards);
            setX(getX() + 20);
        }
    }

    @Override
    public void onCapture() {

    }

    @Override
    public void onDestroy() {

    }
}
