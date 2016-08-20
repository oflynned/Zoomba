package com.zoomba.UI.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.zoomba.GameObjects.CircleFactory.Factory;
import com.zoomba.GameObjects.CircleFactory.FactoryTypes;
import com.zoomba.GameObjects.CircleFactory.GameObject;
import com.zoomba.GameObjects.CircleFactory.ObjectTypes;
import com.zoomba.GameObjects.CircleFactory.Producer;
import com.zoomba.GameObjects.Circles.Slow;
import com.zoomba.Services.Constants;
import com.zoomba.Services.Manager.States.Manager;
import com.zoomba.Zoomba;

import java.util.ArrayList;

/**
 * Created by ed on 09/08/16.
 */
public class GameScreen implements Screen {
    private Zoomba zoomba;
    private OrthographicCamera camera;
    private Viewport viewport;
    ArrayList<Slow> circles;

    public GameScreen(Zoomba zoomba) {
        this.zoomba = zoomba;
        this.camera = new OrthographicCamera();
        this.viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), getCamera());

        Manager.getInstance().setGestureDetector(new GestureDetector(20, 0.5f, 2, 0.15f, Manager.getInstance()));
        Gdx.input.setInputProcessor(Manager.getInstance().getGestureDetector());

        Factory circleFactory = Producer.getFactory(FactoryTypes.Circle);
        circles = new ArrayList<Slow>();
        for(int i=0; i<1; i++) {
            assert circleFactory != null;
            circles.add(circleFactory.generateSlowCircle(ObjectTypes.Slow,
                    Constants.CIRCLE_RADIUS, GameObject.getRandomOrientation()));
        }
        for (Slow circle : circles) {
            System.out.println(circle.getClass() + " " + circle.getX() + " " + circle.getY());
        }
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        Manager.getInstance().update(getCamera());
        setCamera(Manager.getInstance().getCamera());
        getZoomba().getSpriteBatch().setProjectionMatrix(getCamera().combined);

        for (Slow circle : getCircles()) {
            getZoomba().getSpriteBatch().begin();

            //circle.onMove();
            circle.onDraw(getZoomba().getSpriteBatch(), getZoomba().getShapeRenderer(),
                    Manager.getInstance().getScale());

            getZoomba().getSpriteBatch().end();
        }
    }

    @Override
    public void resize(int width, int height) {
        getViewport().update(width, height);
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

    public void setCamera(OrthographicCamera camera) {
        this.camera = camera;
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    public Viewport getViewport() {
        return viewport;
    }

    public ArrayList<Slow> getCircles() {
        return circles;
    }
}
