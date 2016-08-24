package com.zoomba.UI.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
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
public class GameScreen implements Screen {
    private Zoomba zoomba;
    private ArrayList<Circle> slowCircles;

    private Stage stage;
    private OrthographicCamera worldCamera;
    private GestureController gestureController = new GestureController();

    public static float width = Gdx.graphics.getWidth(), height = Gdx.graphics.getHeight();
    private float viewportWidth = width, viewportHeight = height;
    private Vector2 cameraCentre = new Vector2(width, height);
    
    public GameScreen(Zoomba zoomba) {
        this.zoomba = zoomba;

        worldCamera = new OrthographicCamera();
        stage = new Stage(new FitViewport(viewportWidth, viewportHeight));

        worldCamera.position.set(cameraCentre.x / 2, cameraCentre.y / 2, 0);

        GestureDetector gestureDetector = new GestureDetector(20, 0.5f, 2, 0.15f, gestureController);
        Gdx.input.setInputProcessor(gestureDetector);

        Factory circleFactory = Producer.getFactory(FactoryTypes.Circle);
        slowCircles = new ArrayList<Circle>();
        for (int i = 0; i < Constants.CIRCLE_AMOUNT; i++) {
            assert circleFactory != null;
            slowCircles.add(circleFactory.generateCircle(ObjectTypes.Slow, Constants.CIRCLE_RADIUS,
                    GameObject.getRandomOrientation(), Constants.RED_500, Constants.CIRCLE_VELOCITY));
        }
        for (Circle circle : slowCircles) {
            Gdx.app.log(Constants.GAME_SCREEN_DEBUG, circle.getClass() + " " + circle.getX() + " " + circle.getY());
        }
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(33f / 255f, 150f / 255f, 243f / 255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.getViewport().apply();
        stage.draw();
        gestureController.update();
        getZoomba().getSpriteBatch().setProjectionMatrix(worldCamera.combined);

        for (Circle circle : getCircles()) {
            getZoomba().getSpriteBatch().begin();

            circle.onMove();
            circle.onDraw(getZoomba().getSpriteBatch(), getZoomba().getShapeRenderer());

            getZoomba().getSpriteBatch().end();
        }

        stage.getViewport().update((int) viewportWidth, (int) viewportHeight, false);
        worldCamera.position.set(cameraCentre.x / 2, cameraCentre.y / 2, 0);
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

    public ArrayList<Circle> getCircles() {
        return slowCircles;
    }

    class GestureController implements GestureDetector.GestureListener {
        float velX, velY;
        boolean flinging = false;
        float initialScale = 1;

        public boolean touchDown (float x, float y, int pointer, int button) {
            flinging = false;
            initialScale = worldCamera.zoom;
            return false;
        }

        @Override
        public boolean tap (float x, float y, int count, int button) {
            Gdx.app.log(Constants.GESTURE_CONTROLLER_DEBUG, "tap at " + x + ", " + y + ", count: " + count);
            return false;
        }

        @Override
        public boolean longPress (float x, float y) {
            Gdx.app.log(Constants.GESTURE_CONTROLLER_DEBUG, "long press at " + x + ", " + y);
            return false;
        }

        @Override
        public boolean fling (float velocityX, float velocityY, int button) {
            Gdx.app.log(Constants.GESTURE_CONTROLLER_DEBUG, "fling " + velocityX + ", " + velocityY);
            flinging = true;
            velX = worldCamera.zoom * velocityX * 0.5f;
            velY = worldCamera.zoom * velocityY * 0.5f;
            return false;
        }

        @Override
        public boolean pan (float x, float y, float deltaX, float deltaY) {
            Gdx.app.log(Constants.GESTURE_CONTROLLER_DEBUG, "pan at " + x + ", " + y);
            worldCamera.position.add(-deltaX * worldCamera.zoom, deltaY * worldCamera.zoom, 0);
            return false;
        }

        @Override
        public boolean panStop (float x, float y, int pointer, int button) {
            Gdx.app.log(Constants.GESTURE_CONTROLLER_DEBUG, "pan stop at " + x + ", " + y);
            return false;
        }

        @Override
        public boolean zoom (float originalDistance, float currentDistance) {
            float ratio = originalDistance / currentDistance;
            worldCamera.zoom = initialScale / ratio;
            Gdx.app.log(Constants.GESTURE_CONTROLLER_DEBUG, worldCamera.zoom + " zoom");

            viewportWidth = width * worldCamera.zoom;
            viewportHeight = height * worldCamera.zoom;

            if (viewportHeight < height || viewportWidth < width) {
                viewportWidth = width;
                viewportHeight = height;
            }

            Gdx.app.log(Constants.GESTURE_CONTROLLER_DEBUG, viewportWidth + " " + viewportHeight);
            return false;
        }

        @Override
        public boolean pinch (Vector2 initialFirstPointer, Vector2 initialSecondPointer, Vector2 firstPointer, Vector2 secondPointer) {
            cameraCentre.x = (firstPointer.x + secondPointer.x) / 2;
            cameraCentre.y = (firstPointer.y + secondPointer.y) / 2;
            return false;
        }

        public void update () {
            if (flinging) {
                velX *= 0.98f;
                velY *= 0.98f;
                worldCamera.position.add(-velX * Gdx.graphics.getDeltaTime(),
                        velY * Gdx.graphics.getDeltaTime(), 0);
                if (Math.abs(velX) < 0.01f) velX = 0;
                if (Math.abs(velY) < 0.01f) velY = 0;
            }
        }

        @Override
        public void pinchStop () {

        }
    }

}
