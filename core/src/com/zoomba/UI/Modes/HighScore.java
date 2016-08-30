package com.zoomba.UI.Modes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.zoomba.GameObjects.ObjectFactory.Objects.Circle;
import com.zoomba.GameObjects.ObjectFactory.Objects.Factory;
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
import java.util.Iterator;

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

    private Texture bubbleTexture;
    private Texture centreTexture;

    public HighScore(Zoomba zoomba) {
        bubbleTexture = new Texture("bubble.png");
        centreTexture = new Texture("badlogic.jpg");

        this.zoomba = zoomba;
        scoreUi = new ScoreUI(Helper.getAspectWidth(gameHeight), gameHeight);
        spawn(true);

        camera = new OrthographicCamera(Helper.getAspectWidth(gameHeight), gameHeight);
        viewport = new FitViewport(Helper.getAspectWidth(gameHeight), gameHeight, camera);
        viewport.apply();
        camera.position.set(Helper.getAspectWidth(gameHeight) / 2, gameHeight / 2, 0);

        Gdx.input.setInputProcessor(new InputMultiplexer(
                new GestureDetector(20, 0.5f, 1f, 0.15f, new GestureController())));
    }

    public void spawn(boolean isStart) {
        Manager.getInstance().startTimer();
        Manager.getInstance().setCurrentEpoch(Constants.GAME_LENGTH);
        Manager.getInstance().setState(GameState.Ongoing);
        if(isStart) {
            Manager.getInstance().setDifficulty(0);
        }
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

    public boolean isTolerance(Camera camera, Circle circle) {
        //Gdx.app.log("Tolerance", viewport.getScreenHeight() + " " + circle.getRadius());
        return camera.position.x < circle.getX() &&
                camera.position.x > circle.getX() + 2*circle.getRadius() &&
                camera.position.y < circle.getY() + circle.getRadius() &&
                camera.position.y > circle.getY() - circle.getRadius() &&
                gameHeight <= 2*circle.getRadius();
    }

    @Override
    public void render(float delta) {
        Manager.getInstance().checkState(getCircles().size());
        if (Manager.getInstance().getState().equals(GameState.Loss)) {
            getZoomba().setScreen(new EndGameScreen(getZoomba()));
            //spawn();
        } else if (Manager.getInstance().getState().equals(GameState.Win)) {
            spawn(false);
        }

        for(Iterator<Circle> it = getCircles().iterator(); it.hasNext();) {
            Circle circle = it.next();
            circle.onMove();
            if (isTolerance(camera, circle)) {
                Manager.getInstance().incrementScore(circle);
                it.remove();
            }
        }

        Gdx.gl.glClearColor(33f / 255f, 150f / 255f, 243f / 255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        getZoomba().getSpriteBatch().begin();
        getZoomba().getSpriteBatch().setProjectionMatrix(camera.combined);
        getZoomba().getSpriteBatch().draw(centreTexture, -height, 0, height, height);
        getZoomba().getSpriteBatch().draw(centreTexture, width, 0, height, height);
        getZoomba().getSpriteBatch().draw(centreTexture, 0, height, width, width);
        getZoomba().getSpriteBatch().draw(centreTexture, 0, -width, width, width);

        getZoomba().getSpriteBatch().draw(centreTexture, camera.position.x - 25,
                camera.position.y - 25, 50, 50);

        for (Circle circle : getCircles()) {
            circle.onDraw(bubbleTexture, getZoomba().getSpriteBatch(), getZoomba().getShapeRenderer());
        }
        getZoomba().getSpriteBatch().end();

        scoreUi.onUpdate(Helper.getAspectWidth(gameHeight), gameHeight, getCircles().size());
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
        bubbleTexture.dispose();
    }

    public Zoomba getZoomba() {
        return zoomba;
    }

    public ArrayList<Circle> getCircles() {
        return spawnedCircles;
    }

    private class GestureController implements GestureDetector.GestureListener {
        float scale = 1;

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
            //translate the camera centre to the new panned coords
            Vector3 position = new Vector3(x, y, 0);
            camera.unproject(position);
            camera.translate(-deltaX, deltaY, 0);

            //anchor the camera into the bounds of the game arena
            if (camera.position.x > width) {
                camera.position.x = width;
            } else if (camera.position.x < 0) {
                camera.position.x = 0;
            } else if (camera.position.y > height) {
                camera.position.y = height;
            } else if (camera.position.y < 0) {
                camera.position.y = 0;
            }
            return true;
        }

        @Override
        public boolean panStop(float x, float y, int pointer, int button) {
            return false;
        }

        @Override
        public boolean zoom(float initialDistance, float distance) {
            float ratio = initialDistance / distance;

            scale = camera.zoom;
            camera.zoom = scale * ratio;
            Gdx.app.log("Camera", String.valueOf(camera.zoom));

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
