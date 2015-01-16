package com.sshsgd.spoopity.managers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.sshsgd.spoopity.gamestates.*;

public class GameStateManager {

	private SpriteBatch sb;
	private ShapeRenderer sr;
	
	private GameState gamestate;

	public final int SPLASH = 0;
	public final int TITLE = 1;
	public final int PLAY = 2;
	
	public GameStateManager() {
		sb = new SpriteBatch();
		sr = new ShapeRenderer();
		setState(SPLASH);
	}
	
	public void setState(int state) {
		if(gamestate != null) gamestate.dispose();
		if(state == SPLASH) {
			gamestate = new SplashState(this);
		}
		if(state == TITLE) {
			gamestate = new TitleState(this);
		}
		if(state == PLAY) {
			gamestate = new PlayState(this);
		}
	}
	
	public void handleInput() {
		gamestate.handleInput();
	}
	
	public void update(float dt) {
		
		gamestate.update(dt);
	}
	
	public void draw(float dt) {
		gamestate.draw(sb, sr, dt);
	}
	
	public void resize(Vector2 size) {
		gamestate.resize(size);
	}
	
	public void dispose() {
		gamestate.dispose();
		sb.dispose();
		sr.dispose();
	}
}
