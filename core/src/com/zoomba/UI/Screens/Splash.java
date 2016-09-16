package com.zoomba.UI.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.zoomba.Zoomba;

/**
 * Created by ed on 09/08/16.
 */
public class Splash implements Screen {
    private Zoomba zoomba;

    private Texture splashTexture;

    public Splash(Zoomba zoomba) {
        this.zoomba = zoomba;
        splashTexture = new Texture("badlogic.jpg");
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        getZoomba().getSpriteBatch().begin();
        getZoomba().getSpriteBatch().draw(getSplashTexture(), Gdx.graphics.getWidth() / 2 - 250,
                Gdx.graphics.getHeight() / 2 - 250, 500, 500);
        getZoomba().getSpriteBatch().end();
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
        getZoomba().getSpriteBatch().dispose();
        getSplashTexture().dispose();
    }

    public Zoomba getZoomba() {
        return zoomba;
    }

    public Texture getSplashTexture() {
        return splashTexture;
    }
}
