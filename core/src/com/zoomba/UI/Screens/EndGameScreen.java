package com.zoomba.UI.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.zoomba.GameObjects.UI.EndGameUI;
import com.zoomba.Zoomba;

/**
 * Created by ed on 27/08/16.
 */
public class EndGameScreen implements Screen {
    EndGameUI endGameUI;

    public EndGameScreen(Zoomba zoomba) {
        endGameUI = new EndGameUI(zoomba);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        endGameUI.onDraw();
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
        endGameUI.onDispose();
    }
}
