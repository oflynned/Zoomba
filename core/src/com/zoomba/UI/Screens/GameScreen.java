package com.zoomba.UI.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.zoomba.GameObjects.ObjectFactory.Circle;
import com.zoomba.GameObjects.ObjectFactory.Factory;
import com.zoomba.GameObjects.ObjectFactory.FactoryTypes;
import com.zoomba.GameObjects.ObjectFactory.GameObject;
import com.zoomba.GameObjects.ObjectFactory.ObjectTypes;
import com.zoomba.GameObjects.ObjectFactory.Producer;
import com.zoomba.Services.Constants;
import com.zoomba.Zoomba;

import java.util.ArrayList;

/**
 * Created by ed on 09/08/16.
 */
public class GameScreen implements Screen, GestureDetector.GestureListener {
    private Zoomba zoomba;
    ArrayList<Circle> slowCircles;

    float zoomFactor = 0f;
    private OrthographicCamera camera;

    float width = Gdx.graphics.getWidth(), height = Gdx.graphics.getHeight();

    public GameScreen(Zoomba zoomba) {
        this.zoomba = zoomba;
        this.camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        Gdx.input.setInputProcessor(new GestureDetector(this));

        Factory circleFactory = Producer.getFactory(FactoryTypes.Circle);
        slowCircles = new ArrayList<Circle>();
        for (int i = 0; i < Constants.CIRCLE_AMOUNT; i++) {
            assert circleFactory != null;
            slowCircles.add(circleFactory.generateCircle(ObjectTypes.Slow, Constants.CIRCLE_RADIUS,
                    GameObject.getRandomOrientation(), Constants.RED_500, Constants.CIRCLE_VELOCITY));
        }
        for (Circle circle : slowCircles) {
            System.out.println(circle.getClass() + " " + circle.getX() + " " + circle.getY());
        }
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(33f / 255f, 150f / 255f, 243f / 255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        handleInput();
        getCamera().update();
        getZoomba().getSpriteBatch().setProjectionMatrix(getCamera().combined);

        for (Circle circle : getCircles()) {
            getZoomba().getSpriteBatch().begin();

            circle.onMove();
            circle.onDraw(getZoomba().getSpriteBatch(), getZoomba().getShapeRenderer());

            getZoomba().getSpriteBatch().end();
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    public Zoomba getZoomba() {
        return zoomba;
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    public ArrayList<Circle> getCircles() {
        return slowCircles;
    }

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        return false;
    }

    @Override
    public boolean longPress(float x, float y) {
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        camera.translate(-deltaX, deltaY);
        return false;
    }

    public void handleInput() {
        camera.zoom = MathUtils.clamp(camera.zoom, 1.5f, 1.8f);

        float effectiveViewportWidth = camera.viewportWidth * camera.zoom;
        float effectiveViewportHeight = camera.viewportHeight * camera.zoom;

        camera.position.x = MathUtils.clamp(camera.position.x, effectiveViewportWidth / 2f,
                width - effectiveViewportWidth / 2f);
        camera.position.y = MathUtils.clamp(camera.position.y, effectiveViewportHeight / 2f,
                height - effectiveViewportHeight / 2f);
    }


    @Override
    public boolean zoom(float initialDistance, float distance) {
        camera.zoom += initialDistance >= distance ? 0.2 : -0.2;
        return false;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2,
                         Vector2 pointer1, Vector2 pointer2) {
        return false;
    }

    @Override
    public void pinchStop() {

    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        return false;
    }

    public class GestureController implements GestureDetector.GestureListener {
        float velX, velY;
        boolean flinging = false;
        float initialScale = 1;

        public boolean touchDown(float x, float y, int pointer, int button) {
            flinging = false;
            initialScale = camera.zoom;
            return false;
        }

        @Override
        public boolean tap(float x, float y, int count, int button) {
            Gdx.app.log("GestureDetectorTest", "tap at " + x + ", " + y + ", count: " + count);
            return false;
        }

        @Override
        public boolean longPress(float x, float y) {
            Gdx.app.log("GestureDetectorTest", "long press at " + x + ", " + y);
            return false;
        }

        @Override
        public boolean fling(float velocityX, float velocityY, int button) {
            Gdx.app.log("GestureDetectorTest", "fling " + velocityX + ", " + velocityY);
            flinging = true;
            velX = camera.zoom * velocityX * 0.5f;
            velY = camera.zoom * velocityY * 0.5f;
            return false;
        }

        @Override
        public boolean pan(float x, float y, float deltaX, float deltaY) {
            // Gdx.app.log("GestureDetectorTest", "pan at " + x + ", " + y);
            camera.position.add(-deltaX * camera.zoom, deltaY * camera.zoom, 0);
            return false;
        }

        @Override
        public boolean panStop(float x, float y, int pointer, int button) {
            Gdx.app.log("GestureDetectorTest", "pan stop at " + x + ", " + y);
            return false;
        }

        @Override
        public boolean zoom(float originalDistance, float currentDistance) {
            float ratio = originalDistance / currentDistance;
            camera.zoom = initialScale * ratio;
            System.out.println(camera.zoom);
            return false;
        }

        @Override
        public boolean pinch(Vector2 initialFirstPointer, Vector2 initialSecondPointer, Vector2 firstPointer, Vector2 secondPointer) {
            return false;
        }

        public void update() {
            if (flinging) {
                velX *= 0.98f;
                velY *= 0.98f;
                camera.position.add(-velX * Gdx.graphics.getDeltaTime(), velY * Gdx.graphics.getDeltaTime(), 0);
                if (Math.abs(velX) < 0.01f) velX = 0;
                if (Math.abs(velY) < 0.01f) velY = 0;
            }
        }

        @Override
        public void pinchStop() {
        }
    }

}
