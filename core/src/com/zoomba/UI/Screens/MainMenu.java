package com.zoomba.UI.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.zoomba.GameObjects.UI.MainMenuUI;
import com.zoomba.Zoomba;

/**
 * Created by ed on 09/08/16.
 */
public class MainMenu implements Screen {

    private Zoomba zoomba;
    private MainMenuUI mainMenuUI;

    public MainMenu(Zoomba zoomba) {
        this.zoomba = zoomba;
        mainMenuUI = new MainMenuUI(getZoomba());
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        mainMenuUI.onDraw();
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
        mainMenuUI.onDispose();
    }

    public Zoomba getZoomba() {
        return zoomba;
    }
}
