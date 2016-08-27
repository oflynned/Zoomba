package com.zoomba.GameObjects.ObjectFactory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.zoomba.Services.Constants;
import com.zoomba.Services.Manager.State.Behaviour;
import com.zoomba.Services.Manager.State.Direction;
import com.zoomba.UI.Screens.GameScreen;

/**
 * Created by ed on 20/08/16.
 */
public abstract class Circle extends GameObject {
    public Circle(float x, float y, float radius, float orientation, Color color, float velocity, int id) {
        super(x, y, radius, orientation, color, velocity, id);
    }

    public void onSpawn() {
        setX(GameObject.getRandomX());
        setY(GameObject.getRandomY());
        setOrientation(GameObject.getRandomOrientation());
        Gdx.app.log(Constants.OBJECT_DEBUG, "onSpawn() @ (" + getX() + ", " + getY() + ") with vels " +
                getXVel() + " " + getYVel() + ", id: " + getId());
    }

    public void onDraw(ShapeRenderer renderer) {
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.circle((float) getX() + getRadius(), (float) getY(), getRadius());
        renderer.setColor(getColor());
        renderer.end();
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
        Gdx.app.log(Constants.OBJECT_DEBUG, "onCollision() @ (" + getX() + "," + getY() + ") with id " + getId());
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

    public void onCapture(){

    }

    public void onDestroy() {

    }

    public boolean isCollision() {
        return isLeftCollision() || isTopCollision() || isRightCollision() || isBottomCollision();
    }

    public boolean isLeftCollision() {
        return getX()<= 0 ;
    }

    public boolean isTopCollision() {
        return getY() + getRadius() >= GameScreen.height;
    }

    public boolean isRightCollision() {
        return getX() + getRadius()*2 >= GameScreen.width;
    }

    public boolean isBottomCollision() {
        return getY() - getRadius() <= 0;
    }
}
