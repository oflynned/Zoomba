package com.zoomba.GameObjects.UI;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.zoomba.Services.Constants;
import com.zoomba.Services.Manager.State.Manager;
import com.zoomba.UI.Screens.GameScreen;

import javafx.scene.control.Tab;

/**
 * Created by ed on 27/08/16.
 */
public class EndGameUI extends UIObject {
    Skin skinPressable, skinText;
    Stage uiStage;
    Pixmap pixmap;

    TextButton restart, exit;
    TextButton title, message, scoreValue, score, difficultyValue, difficulty, timeValue, time;
    Table table;

    public EndGameUI() {
        uiStage = new Stage(new FitViewport(GameScreen.width, GameScreen.height));
        skinText = new Skin();

        pixmap = new Pixmap(150, 150, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();

        /*skinPressable.add("black", new Texture(pixmap));
        skinPressable.add("default", new BitmapFont());

        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skinPressable.newDrawable("white", Color.WHITE);
        textButtonStyle.down = skinPressable.newDrawable("white", Color.WHITE);
        textButtonStyle.checked = skinPressable.newDrawable("white", Color.WHITE);
        textButtonStyle.over = skinPressable.newDrawable("white", Color.WHITE);
        textButtonStyle.font = skinPressable.getFont("default");
        skinPressable.add("default", textButtonStyle);*/

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
    }

    @Override
    public void onDraw() {
        uiStage.draw();
    }

    @Override
    public void onUpdate(int time, int score) {

    }

    @Override
    public void onDispose() {
        uiStage.dispose();
        skinPressable.dispose();
        skinText.dispose();
        pixmap.dispose();
    }
}
