package com.zoomba.Services.Manager.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by ed on 09/08/16.
 */
public class Manager implements GestureDetector.GestureListener {
    private static Manager manager = null;
    private float scale = 1, velX, velY;
    boolean isFlinging;
    OrthographicCamera camera;
    GestureDetector gestureDetector;

    public static Manager getInstance() {
        if (manager == null)
            manager = new Manager();
        return manager;
    }

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        setFlinging(false);
        setScale(getCamera().zoom);
        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        System.out.println("Tap at: " + x + " " + y + " " + count);
        return false;
    }

    @Override
    public boolean longPress(float x, float y) {
        System.out.println("Long press at: " + x + " " + y);
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        setFlinging(true);
        setVelX(getCamera().zoom * velocityX * 0.5f);
        setVelY(getCamera().zoom * velocityY * 0.5f);

        System.out.println("Fling");
        return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        System.out.println("Pan");
        getCamera().position.add(-deltaX * getCamera().zoom, deltaY * getCamera().zoom, 0);
        return false;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        System.out.println("Pan stop");
        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        float ratio = initialDistance / distance;
        getCamera().zoom = getScale() * ratio;
        System.out.println("Zoom: " + getCamera().zoom);
        return false;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        return false;
    }

    @Override
    public void pinchStop() {

    }

    public void update (OrthographicCamera camera) {
        setCamera(camera);
        if (isFlinging()) {
            setVelX(getVelX() * 0.98f);
            setVelY(getVelY() * 0.98f);
            getCamera().position.add(-getVelX() * Gdx.graphics.getDeltaTime(),
                    getVelY() * Gdx.graphics.getDeltaTime(), 0);
            if (Math.abs(getVelX()) < 0.01f) setVelX(0);
            if (Math.abs(getVelY()) < 0.01f) setVelY(0);
        }
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public float getVelX() {
        return velX;
    }

    public void setVelX(float velX) {
        this.velX = velX;
    }

    public float getVelY() {
        return velY;
    }

    public void setVelY(float velY) {
        this.velY = velY;
    }

    public boolean isFlinging() {
        return isFlinging;
    }

    public void setFlinging(boolean flinging) {
        isFlinging = flinging;
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    public void setCamera(OrthographicCamera camera) {
        this.camera = camera;
    }

    public GestureDetector getGestureDetector() {
        return gestureDetector;
    }

    public void setGestureDetector(GestureDetector gestureDetector) {
        this.gestureDetector = gestureDetector;
    }
}
