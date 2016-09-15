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

    public Circle(Vector2 position, float velocity) {
        super(position, Constants.CIRCLE_RADIUS, GameObject.getRandomOrientation(), velocity);
        onSpawn();
    }

    public void onSpawn() {
        setX(GameObject.getRandomX());
        setY(GameObject.getRandomY());
        setOrientation(GameObject.getRandomOrientation());
        Gdx.app.log(Constants.OBJECT_DEBUG, "onSpawn() @ (" + getX() + ", " + getY() + ") with vels " +
                getXVel() + " " + getYVel() + " (" + getClass() + ")");
    }
}
