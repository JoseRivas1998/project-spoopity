package com.sshsgd.spoopity.gamestates;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.sshsgd.spoopity.Game;
import com.sshsgd.spoopity.MyCamera;
import com.sshsgd.spoopity.managers.GameStateManager;
import com.sshsgd.spoopity.managers.MyInput;

public class SplashState extends GameState {

	private String santa, game, presents;
	private float sX, sY, gX, gY, pX, pY;
	
	private MyCamera cam;
	
	private float time, timer;
	
	public SplashState(GameStateManager gsm) {
		super(gsm);
	}

	@Override
	protected void init() {
		santa = "Santa Susana High School";
		game = "Game Development Club";
		presents = "Presents";
		
		cam = new MyCamera(Game.SIZE, true);
		
		time = 0;
		timer = 5;
		
		setTextValues();
	}

	@Override
	public void handleInput() {
		if(MyInput.anyKeyPressed()) {
			gsm.setState(gsm.TITLE);
		}
	}

	@Override
	public void update(float dt) {
		setTextValues();
		time += dt;
		if(time >= timer) {
			gsm.setState(gsm.TITLE);
		}
	}
	
	private void setTextValues() {
		sX = Game.CENTER.x - (Game.res.getWidth("large", santa) * .5f);
		gX = Game.CENTER.x - (Game.res.getWidth("large", game) * .5f);
		pX = Game.CENTER.x - (Game.res.getWidth("large", presents) * .5f);
		
		gY = Game.CENTER.y + (Game.res.getHeight("large", game) * .5f);
		sY = gY + Game.res.getHeight("large", game) + 10;
		pY = gY - (Game.res.getHeight("large", game)) - 10;
	}

	@Override
	public void draw(SpriteBatch sb, ShapeRenderer sr, float dt) {

		sb.begin();
		sb.setProjectionMatrix(cam.combined);
		Game.res.getFont("large").draw(sb, santa, sX, sY);
		Game.res.getFont("large").draw(sb, game, gX, gY);
		Game.res.getFont("large").draw(sb, presents, pX, pY);
		sb.end();

	}

	@Override
	public void resize(Vector2 size) {
		cam.resize(size, true);
	}

	@Override
	public void dispose() {
		// TODO Logo will be disposed

	}

}
