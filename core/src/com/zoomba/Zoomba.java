package com.zoomba;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.zoomba.Services.Manager.State.Manager;
import com.zoomba.UI.Modes.HighScore;
import com.zoomba.UI.Screens.MainMenu;

public class Zoomba extends Game {
	private SpriteBatch spriteBatch;
	private boolean isBackPressed = false;
	
	@Override
	public void create () {
		spriteBatch = new SpriteBatch();
		setScreen(new HighScore(this));
	}

	@Override
	public void render () {
		super.render();
		isBackPressed = Gdx.input.isKeyPressed(Input.Keys.BACK);
		if (isBackPressed) Gdx.app.exit();
	}
	
	@Override
	public void dispose () { }

	public SpriteBatch getSpriteBatch() {
		return spriteBatch;
	}
}
