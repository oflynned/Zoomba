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
public class EndGameUI implements UIObject {
    private Skin skinText;
    private Stage uiStage;
    private Pixmap pixmap;

    private TextButton restart, exit;
    private TextButton title, message, scoreValue, score, difficultyValue, difficulty, timeValue, time;
    private Table table;

    public EndGameUI(final Zoomba zoomba) {
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

        title = new TextButton("End of Game", skinText);
        message = new TextButton("Well done on reaching the end of the game, here are some stats:", skinText);

        time = new TextButton("Time:", skinText);
        timeValue = new TextButton("TimeValue", skinText);
        score = new TextButton("Score:", skinText);
        scoreValue = new TextButton("ScoreValue", skinText);
        difficulty = new TextButton("Level:", skinText);
        difficultyValue = new TextButton("DifficultyValue", skinText);

        exit = new TextButton("Exit Game", skinText);
        restart = new TextButton("Restart Game", skinText);

        title.getLabel().setFontScale(Constants.TITLE_SCALE);
        message.getLabel().setFontScale(Constants.REG_SCALE);
        time.getLabel().setFontScale(Constants.REG_SCALE);
        timeValue.getLabel().setFontScale(Constants.REG_SCALE);
        score.getLabel().setFontScale(Constants.REG_SCALE);
        scoreValue.getLabel().setFontScale(Constants.REG_SCALE);
        difficulty.getLabel().setFontScale(Constants.REG_SCALE);
        difficultyValue.getLabel().setFontScale(Constants.REG_SCALE);
        exit.getLabel().setFontScale(Constants.REG_SCALE);
        restart.getLabel().setFontScale(Constants.REG_SCALE);

        table = new Table();
        table.setWidth(uiStage.getWidth());
        table.setPosition(0, uiStage.getHeight() / 2);

        Table statItems = new Table();
        statItems.setWidth(uiStage.getWidth());
        statItems.setPosition(uiStage.getWidth() / 2, uiStage.getHeight() / 2);

        Table actionItems = new Table();
        actionItems.setWidth(uiStage.getWidth());
        actionItems.setPosition(uiStage.getWidth() / 2, uiStage.getHeight() / 2);

        statItems.center();
        statItems.add(time);
        statItems.add(timeValue);
        statItems.row();
        statItems.add(score);
        statItems.add(scoreValue);
        statItems.row();
        statItems.add(difficulty);
        statItems.add(difficultyValue);

        restart.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                zoomba.setScreen(new HighScore(zoomba));
            }
        });

        exit.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

            }
        });

        actionItems.add(restart);
        actionItems.add(new TextButton("\t", skinText));
        actionItems.add(exit);

        table.center();
        table.add(title);
        table.row();
        table.add(message);
        table.row();
        table.add(statItems);
        table.row();
        table.add(actionItems);

        uiStage.addActor(table);

        timeValue.setText(Constants.GAME_LENGTH / 100 + "s");
        scoreValue.setText(String.valueOf(Manager.getInstance().getPoints()));
        difficultyValue.setText(String.valueOf(Manager.getInstance().getDifficulty()));

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
