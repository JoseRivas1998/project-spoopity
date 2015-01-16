package com.sshsgd.spoopity;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.sshsgd.spoopity.managers.*;

public class Game extends ApplicationAdapter {
	
	public static final String TITLE = "Project Spoopity";
	
	private int frames;
	private float ftime;
	public static int fps;
	
	public static Vector2 SIZE, CENTER;
	
	public GameStateManager gsm;
	
	public static Content res;
	
	@Override
	public void create () {

		int width = Gdx.graphics.getWidth();
		int height = Gdx.graphics.getHeight();
		SIZE = new Vector2();
		CENTER = new Vector2();
		SIZE.set(width, height);
		CENTER.set(width *.5f, height * .5f);
		
		res = new Content();
		
		res.loadBitmapFont("font", "papyrus.TTF", "large", 56, Color.WHITE);
		res.loadBitmapFont("font", "papyrus.TTF", "mItems", 34, Color.WHITE);
		
		Gdx.input.setInputProcessor(new MyInputProcessor());
		Controllers.addListener(new MyControllerProcessor());
		
		gsm = new GameStateManager();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		float dt = Gdx.graphics.getDeltaTime();
		
		gsm.handleInput();
		gsm.update(dt);
		gsm.draw(dt);
		
		ftime += dt;
		frames++;
		if(ftime >= 1) {
			fps = frames;
			ftime = 0;
			frames = 0;
		}
		
		if(MyInput.keyPressed(MyInput.FULLSCREEN)) {
			if(Gdx.graphics.isFullscreen()) {
				Gdx.graphics.setDisplayMode(800, 600, false);
			} else {
				int width = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
				int height = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
				Gdx.graphics.setDisplayMode(width, height, true);
			}
			Gdx.input.setCursorCatched(Gdx.graphics.isFullscreen());
		}
		
		Gdx.graphics.setTitle(Game.TITLE + " | " + Game.fps + "fps");
		
		MyInput.update();
	}
	
	@Override
	public void resize(int width, int height) {
		SIZE.set(width, height);
		CENTER.set(width *.5f, height * .5f);
		gsm.resize(Game.SIZE);
	}

	@Override
	public void dispose() {
		gsm.dispose();
	}
}
