package com.zoomba.GameObjects.CircleFactory;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Created by ed on 20/08/16.
 */
public interface Circle {
    void onSpawn();
    void onDraw(SpriteBatch batch, ShapeRenderer renderer, float scale);
    void onMove();
    void onCollision();
    void onCapture();
    void onDestroy();
}
