package com.zoomba.GameObjects.ObjectFactory;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Created by ed on 20/08/16.
 */
public abstract class Circle extends GameObject {
    public Circle(float x, float y, float radius, float orientation, Color color, float velocity, int id) {
        super(x, y, radius, orientation, color, velocity, id);
    }

    public abstract void onSpawn();
    public abstract void onDraw(SpriteBatch batch, ShapeRenderer renderer);
    public abstract void onMove();
    public abstract void onCollision();
    public abstract void onCapture();
    public abstract void onDestroy();
}
