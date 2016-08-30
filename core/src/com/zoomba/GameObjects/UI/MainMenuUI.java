package com.zoomba.GameObjects.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.zoomba.Services.Constants;
import com.zoomba.Services.Interfaces.UIObject;
import com.zoomba.Services.Manager.State.Manager;
import com.zoomba.UI.Modes.HighScore;
import com.zoomba.Zoomba;

/**
 * Created by ed on 27/08/16.
 */
public class MainMenuUI implements UIObject {
    private Skin skinText;
    private Stage uiStage;
    private Pixmap pixmap;

    private TextButton highScoreBtn, survivalBtn, snakeBtn, title;
    private Table table;

    public MainMenuUI(final Zoomba zoomba) {
        uiStage = new Stage(new FitViewport(HighScore.width, HighScore.height));
        skinText = new Skin();

        pixmap = new Pixmap(150, 150, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();

        BitmapFont bitmapFont = new BitmapFont();
        bitmapFont.setColor(Color.BLACK);

        skinText.add("black", new Texture(pixmap));
        skinText.add("default", bitmapFont);

        TextButton.TextButtonStyle textStyle = new TextButton.TextButtonStyle();
        textStyle.up = skinText.newDrawable("black", Color.BLACK);
        textStyle.down = skinText.newDrawable("black", Color.BLACK);
        textStyle.checked = skinText.newDrawable("black", Color.BLACK);
        textStyle.over = skinText.newDrawable("black", Color.BLACK);
        textStyle.font = skinText.getFont("default");
        skinText.add("default", textStyle);

        title = new TextButton("Zoomba", skinText);

        highScoreBtn = new TextButton("High Score", skinText);
        survivalBtn = new TextButton("Survival", skinText);
        snakeBtn = new TextButton("Snake", skinText);

        title.getLabel().setFontScale(Constants.TITLE_SCALE);
        highScoreBtn.getLabel().setFontScale(Constants.REG_SCALE);
        survivalBtn.getLabel().setFontScale(Constants.REG_SCALE);
        snakeBtn.getLabel().setFontScale(Constants.REG_SCALE);

        table = new Table();
        table.setWidth(uiStage.getWidth());
        table.setPosition(0, uiStage.getHeight() / 2);

        Table statItems = new Table();
        statItems.setWidth(uiStage.getWidth());
        statItems.setPosition(uiStage.getWidth() / 2, uiStage.getHeight() / 2);

        Table actionItems = new Table();
        actionItems.setWidth(uiStage.getWidth());
        actionItems.setPosition(uiStage.getWidth() / 2, uiStage.getHeight() / 2);

        highScoreBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                zoomba.setScreen(new HighScore(zoomba));
            }
        });

        survivalBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

            }
        });

        snakeBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

            }
        });

        actionItems.add(highScoreBtn);
        actionItems.row();
        actionItems.add(survivalBtn);
        actionItems.row();
        actionItems.add(snakeBtn);

        table.center();
        table.add(title);
        table.row();
        table.add(actionItems);

        uiStage.addActor(table);

        Gdx.input.setInputProcessor(uiStage);
    }

    @Override
    public void onDraw() {
        uiStage.draw();
    }

    @Override
    public void onUpdate(int width, int height, int instances) {

    }

    @Override
    public void onDispose() {
        uiStage.dispose();
        skinText.dispose();
        pixmap.dispose();
    }
}
