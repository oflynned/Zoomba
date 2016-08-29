package com.zoomba;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.zoomba.UI.Modes.HighScore;

public class Zoomba extends Game {
	private SpriteBatch spriteBatch;
    private ShapeRenderer shapeRenderer;
	
	@Override
	public void create () {
		spriteBatch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();

		setScreen(new HighScore(this));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () { }

	public SpriteBatch getSpriteBatch() {
		return spriteBatch;
	}

    public ShapeRenderer getShapeRenderer() {
        return shapeRenderer;
    }
}
