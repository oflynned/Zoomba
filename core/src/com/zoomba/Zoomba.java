package com.zoomba;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Timer;
import com.zoomba.Services.Manager.State.Manager;
import com.zoomba.UI.Modes.HighScore;
import com.zoomba.UI.Screens.MainMenu;
import com.zoomba.UI.Screens.Splash;

public class Zoomba extends Game {
	private SpriteBatch spriteBatch;
	private boolean isBackPressed = false;

	private final float SPLASH_DURATION = 2000f;
	
	@Override
	public void create () {
		spriteBatch = new SpriteBatch();
		setScreen(new HighScore(this));
        //startGameOrder();
	}

	private void startGameOrder() {
		final long startTime = System.currentTimeMillis();

		setScreen(new Splash(this));
		new Thread(new Runnable() {
			@Override
			public void run() {
				Gdx.app.postRunnable(new Runnable() {
					@Override
					public void run() {
						long splash_elapsed_time = System.currentTimeMillis() - startTime;
						if (splash_elapsed_time < SPLASH_DURATION) {
							Timer.schedule(
									new Timer.Task() {
										@Override
										public void run() {
											setScreen(new MainMenu(Zoomba.this));
										}
									}, (SPLASH_DURATION - splash_elapsed_time) / 1000f);
						} else {
							setScreen(new MainMenu(Zoomba.this));
						}
					}
				});
			}
		}).start();
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
