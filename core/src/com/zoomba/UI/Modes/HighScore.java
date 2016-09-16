package com.zoomba.UI.Modes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.zoomba.GameObjects.ObjectFactory.Objects.Circle;
import com.zoomba.GameObjects.ObjectFactory.Objects.Factory;
import com.zoomba.GameObjects.ObjectFactory.Objects.GameObject;
import com.zoomba.GameObjects.ObjectFactory.Objects.Hazard;
import com.zoomba.GameObjects.ObjectFactory.Objects.Powerup;
import com.zoomba.GameObjects.ObjectFactory.Objects.Producer;
import com.zoomba.GameObjects.ObjectFactory.Types.FactoryTypes;
import com.zoomba.GameObjects.ObjectFactory.Types.CircleTypes;
import com.zoomba.GameObjects.ObjectFactory.Types.HazardTypes;
import com.zoomba.GameObjects.ObjectFactory.Types.PowerupTypes;
import com.zoomba.GameObjects.UI.ScoreUI;
import com.zoomba.Services.Constants;
import com.zoomba.Services.Manager.Gesture.Helper;
import com.zoomba.Services.Manager.State.Manager;
import com.zoomba.Services.Manager.Types.DebugState;
import com.zoomba.Services.Manager.Types.GameState;
import com.zoomba.UI.Screens.EndGameScreen;
import com.zoomba.UI.Screens.MainMenu;
import com.zoomba.Zoomba;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by ed on 09/08/16.
 */
public class HighScore implements Screen {
    private Zoomba zoomba;
    private ArrayList<Circle> spawnedCircles = new ArrayList<Circle>();
    private ArrayList<Powerup> powerups = new ArrayList<Powerup>();
    private ArrayList<Hazard> hazards = new ArrayList<Hazard>();

    private ScoreUI scoreUi;

    public static int width = Gdx.graphics.getWidth(), height = Gdx.graphics.getHeight();
    public static int gameHeight = height;
    public static float aspectRatio = (float) height / (float) width;

    private OrthographicCamera camera;
    private GestureController gestureController;
    private Viewport viewport;

    //hazards
    private Texture decreaseCircleSizeTexture, decreaseScrollTexture, decreaseZoomSpeedTexture,
            increaseCircleSpeedTexture, instantLossTexture, invertibilityTexture, invisibilityTexture;
    //powerups
    private Texture decreaseCircleSpeedTexture, increaseCircleSizeTexture, increaseScrollSpeedTexture,
            increaseZoomSpeedTexture;

    //circles
    private Texture bubbleTexture;

    private Texture crosshairTexture;
    private Texture debugTexture;

    public HighScore(Zoomba zoomba) {
        //test textures
        bubbleTexture = new Texture("bubble.png");
        debugTexture = new Texture("badlogic.jpg");

        //for debug for zooming to centre, will be removed in release iteration
        crosshairTexture = new Texture("crosshair.png");

        //hazards
        decreaseCircleSizeTexture = new Texture("haz_1.png");
        decreaseScrollTexture = new Texture("haz_2.png");
        decreaseZoomSpeedTexture = new Texture("haz_3.png");
        increaseCircleSpeedTexture = new Texture("haz_4.png");
        instantLossTexture = new Texture("haz_5.png");
        invertibilityTexture = new Texture("haz_6.png");
        invisibilityTexture = new Texture("haz_7.png");

        //powerups
        decreaseCircleSpeedTexture = new Texture("pow_1.png");
        increaseCircleSizeTexture = new Texture("pow_2.png");
        increaseScrollSpeedTexture = new Texture("pow_3.png");
        increaseZoomSpeedTexture = new Texture("pow_4.png");

        this.zoomba = zoomba;
        scoreUi = new ScoreUI(Helper.getAspectWidth(gameHeight), gameHeight);
        spawn(true);

        camera = new OrthographicCamera(Helper.getAspectWidth(gameHeight), gameHeight);
        viewport = new FitViewport(Helper.getAspectWidth(gameHeight), gameHeight, camera);
        viewport.apply();
        camera.position.set(Helper.getAspectWidth(gameHeight) / 2, gameHeight / 2, 0);

        gestureController = new GestureController();
        Gdx.input.setCatchBackKey(true);
        Gdx.input.setInputProcessor(new InputMultiplexer(
                new GestureDetector(20, 0.5f, 1f, 0.15f, gestureController)));
    }

    public void spawn(boolean isStart) {
        Manager.getInstance().startTimer();
        Manager.getInstance().startSpawnTimer();
        Manager.getInstance().setState(GameState.Ongoing);

        getHazards().clear();
        getPowerups().clear();
        getSpawnedCircles().clear();

        if (isStart) Manager.getInstance().setDifficulty(0);
        Manager.getInstance().setPoints(Manager.getInstance().getPoints() +
                Manager.getInstance().getDifficulty() * 10);
        Manager.getInstance().setDifficulty(Manager.getInstance().getDifficulty() + 1);
        populateLevel();
    }

    private void populateLevel() {
        Factory circleFactory = Producer.getFactory(FactoryTypes.Circle);
        for (int i = 0; i < Constants.CIRCLE_INITIAL_AMOUNT * Manager.getInstance().getDifficulty(); i++) {
            spawnedCircles.add(circleFactory.generateCircle(CircleTypes.Slow));
        }
    }

    @Override
    public void show() {

    }

    public boolean isTolerance(Camera camera, float zoom, GameObject gameObject) {
        return camera.position.x > gameObject.getX() &&
                camera.position.x < gameObject.getX() + 2 * gameObject.getRadius() &&
                camera.position.y < gameObject.getY() + gameObject.getRadius() &&
                camera.position.y > gameObject.getY() - gameObject.getRadius() &&
                zoom <= 0.1f;
    }

    @Override
    public void render(float delta) {
        handleUpdateLogic();
        handleRenderingLogic();
    }

    private void handleUpdateLogic() {
        if (Manager.getInstance().getSpawnEpoch() == 0 && Manager.getInstance().getCurrentEpoch() > 0) {
            GameObject gameObject = Manager.getInstance().generateHazard(HazardTypes.DecreaseCircleSize);
            //Manager.getInstance().generatePickup();

            if (gameObject.getClass().getSuperclass().equals(Powerup.class))
                powerups.add((Powerup) gameObject);
            else if (gameObject.getClass().getSuperclass().equals(Hazard.class))
                hazards.add((Hazard) gameObject);

            Manager.getInstance().startSpawnTimer();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.BACK)) {
            getZoomba().setScreen(new MainMenu(getZoomba()));
        }

        Manager.getInstance().checkState(getSpawnedCircles().size());
        if (Manager.getInstance().getState().equals(GameState.Loss)) {
            getZoomba().setScreen(new EndGameScreen(getZoomba()));
        } else if (Manager.getInstance().getState().equals(GameState.Win)) {
            spawn(false);
        }

        for (Iterator<Circle> it = getSpawnedCircles().iterator(); it.hasNext();) {
            Circle circle = it.next();
            circle.onMove();
            if (isTolerance(camera, camera.zoom, circle)) {
                Manager.getInstance().incrementScore(circle);
                it.remove();
            }
        }

        for (Iterator<Powerup> it = getPowerups().iterator(); it.hasNext();) {
            Powerup powerup = it.next();
            powerup.onMove();
            if (isTolerance(camera, camera.zoom, powerup)) {
                powerup.onPickup(getSpawnedCircles());
                it.remove();
            }
        }

        for (Iterator<Hazard> it = getHazards().iterator(); it.hasNext();) {
            Hazard hazard = it.next();
            hazard.onUpdate();
            hazard.onMove();
            if (isTolerance(camera, camera.zoom, hazard) && !hazard.isPickupUp()) {
                hazard.onPickup(getSpawnedCircles());
                hazard.setPickupUp(true);
            }
            if (!hazard.isAlive()) {
                hazard.onDestroy(getSpawnedCircles());
                it.remove();
            }
            Gdx.app.log("Hazard", "Hazard: existence " + hazard.getExistence() +
                    ", lifetime: " + hazard.getLifetime() +
                    ", invoked: " + hazard.isInvoked() + ", alive: " + hazard.isAlive());
        }
    }

    private void handleRenderingLogic() {
        Gdx.gl.glClearColor(33f / 255f, 150f / 255f, 243f / 255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        gestureController.update();
        camera.update();
        getZoomba().getSpriteBatch().begin();
        getZoomba().getSpriteBatch().setProjectionMatrix(camera.combined);
        if (Manager.getInstance().getDebugState().equals(DebugState.Walls)) {
            //draw walls
            //left
            getZoomba().getSpriteBatch().draw(debugTexture, -height, 0, height, height);
            //right
            getZoomba().getSpriteBatch().draw(debugTexture, width, 0, height, height);
            //top
            getZoomba().getSpriteBatch().draw(debugTexture, 0, height, width, width);
            //bottom
            getZoomba().getSpriteBatch().draw(debugTexture, 0, -width, width, width);
        }

        //draw game objects to the buffer by sprite batch
        for (Circle circle : getSpawnedCircles()) {
            circle.onDraw(bubbleTexture, getZoomba().getSpriteBatch());
        }

        for (Hazard hazard : hazards) {
            if (!hazard.isPickupUp()) {
                if (hazard.getHazardType().equals(HazardTypes.DecreaseCircleSize)) {
                    hazard.onDraw(decreaseCircleSizeTexture, getZoomba().getSpriteBatch());
                } else if (hazard.getHazardType().equals(HazardTypes.DecreaseScrollSpeed)) {
                    hazard.onDraw(decreaseScrollTexture, getZoomba().getSpriteBatch());
                } else if (hazard.getHazardType().equals(HazardTypes.DecreaseZoomSpeed)) {
                    hazard.onDraw(decreaseZoomSpeedTexture, getZoomba().getSpriteBatch());
                } else if (hazard.getHazardType().equals(HazardTypes.IncreaseCircleSpeed)) {
                    hazard.onDraw(increaseCircleSpeedTexture, getZoomba().getSpriteBatch());
                } else if (hazard.getHazardType().equals(HazardTypes.InstantLoss)) {
                    hazard.onDraw(instantLossTexture, getZoomba().getSpriteBatch());
                } else if (hazard.getHazardType().equals(HazardTypes.Invertibility)) {
                    hazard.onDraw(invertibilityTexture, getZoomba().getSpriteBatch());
                } else if (hazard.getHazardType().equals(HazardTypes.Invisibility)) {
                    hazard.onDraw(invisibilityTexture, getZoomba().getSpriteBatch());
                }
            }
        }

        for (Powerup powerup : powerups) {
            if (powerup.getPowerupType().equals(PowerupTypes.IncreaseZoomSpeed)) {
                powerup.onDraw(increaseZoomSpeedTexture, getZoomba().getSpriteBatch());
            } else if (powerup.getPowerupType().equals(PowerupTypes.IncreaseScrollSpeed)) {
                powerup.onDraw(increaseScrollSpeedTexture, getZoomba().getSpriteBatch());
            } else if (powerup.getPowerupType().equals(PowerupTypes.IncreaseCircleSize)) {
                powerup.onDraw(increaseCircleSizeTexture, getZoomba().getSpriteBatch());
            } else if (powerup.getPowerupType().equals(PowerupTypes.DecreaseCircleSpeed)) {
                powerup.onDraw(decreaseCircleSpeedTexture, getZoomba().getSpriteBatch());
            }
        }

        //centre of camera for reference
        getZoomba().getSpriteBatch().draw(crosshairTexture, camera.position.x - 100,
                camera.position.y - 100, 200, 200);

        getZoomba().getSpriteBatch().end();

        scoreUi.onUpdate(Helper.getAspectWidth(gameHeight), gameHeight, getSpawnedCircles().size());
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
        decreaseCircleSizeTexture.dispose();
        decreaseScrollTexture.dispose();
        decreaseZoomSpeedTexture.dispose();
        increaseCircleSpeedTexture.dispose();
        increaseCircleSpeedTexture.dispose();
        instantLossTexture.dispose();
        invertibilityTexture.dispose();
        invisibilityTexture.dispose();
        decreaseCircleSpeedTexture.dispose();
        increaseCircleSizeTexture.dispose();
        increaseScrollSpeedTexture.dispose();
        increaseZoomSpeedTexture.dispose();
    }

    public Zoomba getZoomba() {
        return zoomba;
    }

    public ArrayList<Circle> getSpawnedCircles() {
        return spawnedCircles;
    }

    public ArrayList<Powerup> getPowerups() {
        return powerups;
    }

    public ArrayList<Hazard> getHazards() {
        return hazards;
    }

    private class GestureController implements GestureDetector.GestureListener {
        float velX, velY, velZ;
        boolean fling = false;

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
                Manager.getInstance().setDebugState(DebugState.Walls);
            else if (Manager.getInstance().getDebugState().equals(DebugState.Walls))
                Manager.getInstance().setDebugState(DebugState.Normal);
            return true;
        }

        @Override
        public boolean fling(float velocityX, float velocityY, int button) {
            velX = camera.zoom * velocityX * 0.5f;
            velY = camera.zoom * velocityY * 0.5f;
            fling = true;
            return true;
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
            velZ = (initialDistance / distance);
            //smooth zoom by taking enumeration of touch points over time
            camera.zoom += velZ < 1 ? -velZ * 0.01f : velZ * 0.01f;

            //anchor to max and min
            camera.zoom = camera.zoom < 0.05f ? 0.05f : camera.zoom;
            camera.zoom = camera.zoom > 2 ? 2 : camera.zoom;

            return true;
        }

        @Override
        public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2,
                             Vector2 pointer1, Vector2 pointer2) {
            return false;
        }

        @Override
        public void pinchStop() {

        }

        public void update() {
            System.out.println(camera.zoom);

            if (fling) {
                velX *= 0.98f;
                velY *= 0.98f;

                camera.position.add(-velX * Gdx.graphics.getDeltaTime(),
                        velY * Gdx.graphics.getDeltaTime(), 0);

                velX = Math.abs(velX) < 0.01f ? 0 : velX;
                velY = Math.abs(velY) < 0.01f ? 0 : velY;

                fling = !(velX == 0 || velY == 0);

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

                if (camera.position.x < 0 && camera.position.y < 0) {
                    camera.position.set(0, 0, 0);
                } else if (camera.position.x < 0 && camera.position.y > height) {
                    camera.position.set(0, height, 0);
                } else if (camera.position.x > width && camera.position.y > height) {
                    camera.position.set(width, height, 0);
                } else if (camera.position.x > width && camera.position.y < 0) {
                    camera.position.set(width, 0, 0);
                }
            }
        }
    }
}
