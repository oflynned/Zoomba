package com.zoomba.UI.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.zoomba.GameObjects.ObjectFactory.Circle;
import com.zoomba.GameObjects.ObjectFactory.Factory;
import com.zoomba.GameObjects.ObjectFactory.FactoryTypes;
import com.zoomba.GameObjects.ObjectFactory.GameObject;
import com.zoomba.GameObjects.ObjectFactory.ObjectTypes;
import com.zoomba.GameObjects.ObjectFactory.Producer;
import com.zoomba.GameObjects.UI.ScoreUI;
import com.zoomba.Services.Constants;
import com.zoomba.Services.Manager.State.GameState;
import com.zoomba.Services.Manager.State.Manager;
import com.zoomba.Zoomba;

import java.util.ArrayList;

/**
 * Created by ed on 09/08/16.
 */
public class GameScreen implements Screen {
    private Zoomba zoomba;
    private ArrayList<Circle> spawnedCircles;

    private Stage mainStage;
    private OrthographicCamera worldCamera;
    private GestureController gestureController = new GestureController();
    private float viewportWidth = width, viewportHeight = height;
    private Vector3 cameraCentre = new Vector3(width, height, 0f);
    private ScoreUI scoreUi;

    public static float width = Gdx.graphics.getWidth(), height = Gdx.graphics.getHeight();
    
    public GameScreen(Zoomba zoomba) {
        this.zoomba = zoomba;

        worldCamera = new OrthographicCamera();
        mainStage = new Stage(new FitViewport(viewportWidth, viewportHeight));
        worldCamera.position.set(cameraCentre.x / 2, cameraCentre.y / 2, 0);

        scoreUi = new ScoreUI(viewportWidth, viewportHeight);
        Gdx.input.setInputProcessor(new GestureDetector(20, 0.5f, 2, 0.15f, gestureController));

        spawn();
    }

    public void spawn() {
        Manager.getInstance().setCurrentEpoch(Constants.GAME_LENGTH);
        Manager.getInstance().setState(GameState.Ongoing);
        Manager.getInstance().setDifficulty(1);
        Manager.getInstance().setPoints(0);
        populateLevel();
        Gdx.app.log("Difficulty", String.valueOf(Manager.getInstance().getDifficulty()));
    }

    private void spawnCircles(Factory circleFactory, ObjectTypes objectType, Color color, float velocity) {
        for (int i = 0; i < Constants.CIRCLE_AMOUNT * Manager.getInstance().getDifficulty(); i++) {
            spawnedCircles.add(circleFactory.generateCircle(objectType, Constants.CIRCLE_RADIUS,
                    GameObject.getRandomOrientation(), color, velocity));
        }
    }

    private void populateLevel() {
        spawnedCircles = new ArrayList<Circle>();
        spawnCircles(Producer.getFactory(FactoryTypes.Circle), ObjectTypes.Fast,
                Constants.YELLOW_500, Constants.FAST_VELOCITY);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Manager.getInstance().countdown(getCircles().size());
        if(Manager.getInstance().getState().equals(GameState.Loss)) {
            getZoomba().setScreen(new EndGameScreen(getZoomba()));
        } else if (Manager.getInstance().getState().equals(GameState.Win)) {
            spawn();
        }

        Gdx.gl.glClearColor(33f / 255f, 150f / 255f, 243f / 255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        mainStage.getViewport().apply();
        mainStage.draw();
        gestureController.update();
        getZoomba().getSpriteBatch().setProjectionMatrix(worldCamera.combined);

        for (Circle circle : getCircles()) {
            getZoomba().getSpriteBatch().begin();

            //Behaviour.circleCollision(getCircles());
            circle.onMove();
            circle.onDraw(getZoomba().getShapeRenderer());

            getZoomba().getSpriteBatch().end();
        }

        mainStage.getViewport().update((int) viewportWidth, (int) viewportHeight, false);
        worldCamera.update();
        worldCamera.position.set(cameraCentre.x / 2, cameraCentre.y / 2, 0);

        scoreUi.onUpdate(Manager.getInstance().getCurrentEpoch() / 100, 0);
        scoreUi.onDraw();
    }

    @Override
    public void resize(int width, int height) {
        mainStage.getViewport().update(width, height, false);
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
        mainStage.dispose();
        scoreUi.onDispose();
    }

    public Zoomba getZoomba() {
        return zoomba;
    }

    public ArrayList<Circle> getCircles() {
        return spawnedCircles;
    }

    class GestureController implements GestureDetector.GestureListener, InputProcessor {
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
            return false;
        }

        @Override
        public boolean longPress (float x, float y) {
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
            return false;
        }

        @Override
        public boolean zoom (float originalDistance, float currentDistance) {
            if (viewportHeight < GameScreen.height || GameScreen.width < width) {
                viewportWidth = width;
                viewportHeight = height;
            } else {
                float ratio = originalDistance / currentDistance;
                worldCamera.zoom = initialScale / ratio;
                Gdx.app.log(Constants.GESTURE_CONTROLLER_DEBUG, worldCamera.zoom + " zoom");

                viewportWidth = GameScreen.width * worldCamera.zoom;
                viewportHeight = GameScreen.height * worldCamera.zoom;
            }

            Gdx.app.log(Constants.GESTURE_CONTROLLER_DEBUG, viewportWidth + " " + viewportHeight + ", ZOOM " + worldCamera.zoom);
            return false;
        }

        @Override
        public boolean pinch (Vector2 initialFirstPointer, Vector2 initialSecondPointer, Vector2 firstPointer, Vector2 secondPointer) {
            cameraCentre.x = ((firstPointer.x + secondPointer.x) / 2) % viewportWidth;
            cameraCentre.y = ((firstPointer.y + secondPointer.y) / 2) % viewportHeight;

            Gdx.app.log("CameraDebug", String.valueOf(cameraCentre));
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

        @Override
        public boolean keyDown(int keycode) {
            return false;
        }

        @Override
        public boolean keyUp(int keycode) {
            return false;
        }

        @Override
        public boolean keyTyped(char character) {
            return false;
        }

        @Override
        public boolean touchDown(int screenX, int screenY, int pointer, int button) {
            return false;
        }

        @Override
        public boolean touchUp(int screenX, int screenY, int pointer, int button) {
            return false;
        }

        @Override
        public boolean touchDragged(int screenX, int screenY, int pointer) {
            return false;
        }

        @Override
        public boolean mouseMoved(int screenX, int screenY) {
            return false;
        }

        @Override
        public boolean scrolled(int amount) {
            return false;
        }
    }

}
