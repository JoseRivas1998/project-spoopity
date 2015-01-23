package com.sshsgd.spoopity.gamestates;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.sshsgd.spoopity.Game;
import com.sshsgd.spoopity.MyCamera;
import com.sshsgd.spoopity.entities.*;
import com.sshsgd.spoopity.managers.GameStateManager;
import com.sshsgd.spoopity.managers.MyInput;

public class PlayState extends GameState {
	
	private World w;
	
	private MyCamera cam;

	private Array<Basic> basics;
	
	private boolean paused;
	
	private HUD hud;
	
	public PlayState(GameStateManager gsm) {
		super(gsm);
	}

	@Override
	protected void init() {
		w = new World();
		
		basics = new Array<Basic>();
		
		paused = false;
		
		hud = new HUD();
		
		for(Vector2 v: w.getObjects()) {
			basics.add(new Basic("basics/temp/walkr.png", 4, v.x, v.y, .1f));
		}
		
		cam = new MyCamera(Game.SIZE, true);
	}

	@Override
	public void handleInput() {
		if(MyInput.keyDown(MyInput.RIGHT)) {
			cam.position.x += 20;
		}
		if(MyInput.keyDown(MyInput.LEFT)) {
			cam.position.x -= 20;
		}
		if(MyInput.keyDown(MyInput.UP)) {
			cam.position.y += 20;
		}
		if(MyInput.keyDown(MyInput.DOWN)) {
			cam.position.y -= 20;
		}
		if(MyInput.keyPressed(MyInput.START)) {
			paused = !paused;
		}
		if(MyInput.keyPressed(MyInput.BACK) && paused) {
			gsm.setState(gsm.TITLE);
		}
	}

	@Override
	public void update(float dt) {
		if(cam.position.x < Game.CENTER.x) {
			cam.position.x = Game.CENTER.x;
		}
		if(cam.position.y < Game.CENTER.y) {
			cam.position.y = Game.CENTER.y;
		}
		if(cam.position.x > w.getWidth() - Game.CENTER.x) {
			cam.position.x = w.getWidth() - Game.CENTER.x;
		}
		if(cam.position.y > w.getHeight() - Game.CENTER.y) {
			cam.position.y = w.getHeight() - Game.CENTER.y;
		}
		cam.update();
		for(Basic b : basics) {
			b.upadate(w);
		}
	}

	@Override
	public void draw(SpriteBatch sb, ShapeRenderer sr, float dt) {
		w.render(cam);
		
		sb.begin();
		sb.setProjectionMatrix(cam.combined);
		for(Basic b : basics) {
			b.draw(sr, sb, dt);
		}
		sb.end();
		
		sr.begin(ShapeType.Filled);
		sr.setProjectionMatrix(cam.combined);
		sr.setColor(Color.GREEN);
		for(EnemyRectangle e : w.getEnemies()) {
			e.draw(sr, sb, dt);
		}
		sr.end();
		
		hud.render(sb, sr, dt, paused);
	}

	@Override
	public void resize(Vector2 size) {
		cam.resize(size, false);
		hud.resize(size);
	}

	@Override
	public void dispose() {
		for(Basic b : basics) {
			b.dispose();
		}

	}

}
