package com.zoomba.GameObjects.ObjectFactory.Objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.zoomba.Services.Constants;
import com.zoomba.Services.Interfaces.PlayerFocus;
import com.zoomba.Services.Manager.State.Behaviour;
import com.zoomba.Services.Manager.State.Manager;
import com.zoomba.Services.Manager.Types.DebugState;
import com.zoomba.Services.Manager.Types.Direction;
import com.zoomba.UI.Modes.HighScore;

/**
 * Created by ed on 20/08/16.
 */
public abstract class Circle extends GameObject implements PlayerFocus {

    public Circle(Vector2 position, Color color, float velocity) {
        super(position, Constants.CIRCLE_RADIUS, GameObject.getRandomOrientation(), color, velocity);
        onSpawn();
    }

    public void onSpawn() {
        setX(GameObject.getRandomX());
        setY(GameObject.getRandomY());
        setOrientation(GameObject.getRandomOrientation());
        Gdx.app.log(Constants.OBJECT_DEBUG, "onSpawn() @ (" + getX() + ", " + getY() + ") with vels " +
                getXVel() + " " + getYVel() + " (" + getClass() + ")");
    }

    public void onDraw(Texture texture, Batch batch, ShapeRenderer renderer) {
        batch.draw(texture, (float) getX(), (float) (getY() - getRadius()),
                Constants.CIRCLE_RADIUS * 2, Constants.CIRCLE_RADIUS * 2);

        if (Manager.getInstance().getDebugState().equals(DebugState.Outline)) {
            renderer.begin(ShapeRenderer.ShapeType.Line);
            renderer.rect((float) getX(), (float) getY() - getRadius(), 2 * getRadius(), 2 * getRadius());
            renderer.setColor(getColor());
            renderer.end();
        }
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
