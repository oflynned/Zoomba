package com.zoomba.GameObjects.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.stbtt.TrueTypeFontFactory;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;

import sun.font.TrueTypeFont;

/**
 * Created by ed on 25/08/16.
 */
public class UI extends UIObject implements InputProcessor {

    private Stage uiStage;
    private Skin skin;
    private Pixmap pixmap;
    private Table table;
    private TextButton scoreText, scoreValue, timeText, timeValue;

    public UI(float width, float height) {
        uiStage = new Stage(new FitViewport(width, height));
        skin = new Skin();

        pixmap = new Pixmap(150, 150, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();

        skin.add("white", new Texture(pixmap));
        skin.add("default", new BitmapFont());

        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.newDrawable("white", Color.DARK_GRAY);
        textButtonStyle.down = skin.newDrawable("white", Color.DARK_GRAY);
        textButtonStyle.checked = skin.newDrawable("white", Color.BLUE);
        textButtonStyle.over = skin.newDrawable("white", Color.LIGHT_GRAY);
        textButtonStyle.font = skin.getFont("default");
        skin.add("default", textButtonStyle);

        scoreText = new TextButton("Score:", skin);
        scoreValue = new TextButton("ScoreValue", skin);
        timeText = new TextButton("Time:", skin);
        timeValue = new TextButton("TimeValue", skin);

        scoreText.getLabel().setFontScale(3);
        scoreValue.getLabel().setFontScale(3);
        timeText.getLabel().setFontScale(3);
        timeValue.getLabel().setFontScale(3);

        table = new Table();
        table.setWidth(uiStage.getWidth());
        table.align(Align.center|Align.top);
        table.setPosition(0, Gdx.graphics.getHeight());

        table.add(scoreText);
        table.add(scoreValue);
        table.add(timeText);
        table.add(timeValue);

        uiStage.addActor(table);
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

    @Override
    public void onDraw() {
        uiStage.draw();
    }

    @Override
    public void onUpdate(int time, int score) {
        timeValue.setText(String.valueOf(time));
        scoreValue.setText(String.valueOf(score));
    }

    @Override
    public void onDispose() {
        uiStage.dispose();
        skin.dispose();
        pixmap.dispose();
    }
}
