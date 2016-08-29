package com.zoomba.UI.Modes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.zoomba.GameObjects.ObjectFactory.Objects.Circle;
import com.zoomba.GameObjects.ObjectFactory.Objects.Factory;
import com.zoomba.GameObjects.ObjectFactory.Objects.GameObject;
import com.zoomba.GameObjects.ObjectFactory.Objects.Producer;
import com.zoomba.GameObjects.ObjectFactory.Types.FactoryTypes;
import com.zoomba.GameObjects.ObjectFactory.Types.ObjectTypes;
import com.zoomba.GameObjects.UI.ScoreUI;
import com.zoomba.Services.Constants;
import com.zoomba.Services.Manager.Gesture.Helper;
import com.zoomba.Services.Manager.State.Manager;
import com.zoomba.Services.Manager.Types.DebugState;
import com.zoomba.Services.Manager.Types.GameState;
import com.zoomba.UI.Screens.EndGameScreen;
import com.zoomba.Zoomba;

import java.util.ArrayList;

/**
 * Created by ed on 09/08/16.
 */
public class HighScore implements Screen {
    private Zoomba zoomba;
    private ArrayList<Circle> spawnedCircles = new ArrayList<Circle>();

    private ScoreUI scoreUi;

    public static int width = Gdx.graphics.getWidth(), height = Gdx.graphics.getHeight();
    public static int gameWidth = width, gameHeight = height;
    public static float aspectRatio = (float) height / (float) width;

    private OrthographicCamera camera;
    private Viewport viewport;
    private float initialScale = 1;

    private Texture texture;

    public HighScore(Zoomba zoomba) {
        texture = new Texture("bubble.png");

        this.zoomba = zoomba;
        scoreUi = new ScoreUI(Helper.getAspectWidth(gameHeight), gameHeight);
        spawn();

        camera = new OrthographicCamera(Helper.getAspectWidth(gameHeight), gameHeight);
        viewport = new FitViewport(Helper.getAspectWidth(gameHeight), gameHeight, camera);
        viewport.apply();
        camera.position.set(Helper.getAspectWidth(gameHeight) / 2, gameHeight / 2, 0);

        Gdx.input.setInputProcessor(new InputMultiplexer(
                new GestureDetector(20, 0.5f, 1f, 0.15f, new GestureController())));
    }

    public void spawn() {
        Manager.getInstance().startTimer();
        Manager.getInstance().setCurrentEpoch(Constants.GAME_LENGTH);
        Manager.getInstance().setState(GameState.Ongoing);
        Manager.getInstance().setPoints(Manager.getInstance().getPoints() +
                Manager.getInstance().getDifficulty() * 10);
        Manager.getInstance().setDifficulty(Manager.getInstance().getDifficulty() + 1);
        populateLevel();
    }

    private void populateLevel() {
        Factory circleFactory = Producer.getFactory(FactoryTypes.Circle);
        assert circleFactory != null;

        spawnedCircles.clear();
        circleFactory.generateCircle(ObjectTypes.Slow);

        for (int i = 0; i < Constants.CIRCLE_INITIAL_AMOUNT * Manager.getInstance().getDifficulty(); i++) {
            spawnedCircles.add(circleFactory.generateCircle(ObjectTypes.Slow));
        }
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Manager.getInstance().checkState(getCircles().size());
        if (Manager.getInstance().getState().equals(GameState.Loss)) {
            //getZoomba().setScreen(new EndGameScreen(getZoomba()));
            spawn();
        } else if (Manager.getInstance().getState().equals(GameState.Win)) {
            spawn();
        }

        Gdx.gl.glClearColor(33f / 255f, 150f / 255f, 243f / 255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

        camera.update();
        getZoomba().getSpriteBatch().enableBlending();
        Color color = getZoomba().getSpriteBatch().getColor();
        getZoomba().getSpriteBatch().setColor(color.r, color.g, color.b, 1f);
        getZoomba().getSpriteBatch().begin();
        getZoomba().getSpriteBatch().setProjectionMatrix(camera.combined);
        for (Circle circle : getCircles()) {
            circle.onMove();
            circle.onDraw(texture, getZoomba().getSpriteBatch(), getZoomba().getShapeRenderer());
        }
        getZoomba().getSpriteBatch().end();
        Gdx.gl.glDisable(GL20.GL_BLEND);

        scoreUi.onUpdate(gameWidth, gameHeight, getCircles().size());
        scoreUi.onDraw();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        camera.position.set(Helper.getAspectWidth(gameHeight) / 2, gameHeight / 2, 0);
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
        scoreUi.onDispose();
    }

    public Zoomba getZoomba() {
        return zoomba;
    }

    public ArrayList<Circle> getCircles() {
        return spawnedCircles;
    }

    private class GestureController implements GestureDetector.GestureListener {

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
            Gdx.app.log("DebugState", Manager.getInstance().getDebugState().name());
            if (Manager.getInstance().getDebugState().equals(DebugState.Normal))
                Manager.getInstance().setDebugState(DebugState.Outline);
            else if (Manager.getInstance().getDebugState().equals(DebugState.Outline))
                Manager.getInstance().setDebugState(DebugState.Normal);
            return true;
        }

        @Override
        public boolean fling(float velocityX, float velocityY, int button) {
            return false;
        }

        @Override
        public boolean pan(float x, float y, float deltaX, float deltaY) {
            Vector3 position = new Vector3(x, y, 0);
            camera.unproject(position);
            camera.translate(-deltaX, deltaY, 0);
            return true;
        }

        @Override
        public boolean panStop(float x, float y, int pointer, int button) {
            return false;
        }

        @Override
        public boolean zoom(float initialDistance, float distance) {
            float ratio = initialDistance / distance;
            Gdx.app.log("Zoom", String.valueOf(ratio));

            camera.zoom = initialScale * ratio;
            if (camera.zoom > 1) camera.zoom = 1;
            else {
                gameHeight = (int) (gameHeight * camera.zoom);
                gameWidth = Helper.getAspectWidth(gameHeight);
            }
            return true;
        }

        @Override
        public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
            return false;
        }

        @Override
        public void pinchStop() {

        }
    }
}
