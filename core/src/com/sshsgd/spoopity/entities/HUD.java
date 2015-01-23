package com.sshsgd.spoopity.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.sshsgd.spoopity.Game;
import com.sshsgd.spoopity.MyCamera;

public class HUD {
	
	private MyCamera cam;
	
	public HUD() {
		cam = new MyCamera(Game.SIZE, true);
	}
	
	public void render(SpriteBatch sb, ShapeRenderer sr, float dt, boolean paused) {
		
		if(paused) {
			String p1, p2, p3;
			Rectangle rect = new Rectangle();
			p1 = "The Game is Paused";
			p2 = "To return to the game, press start";
			p3 = "To exit to title, press back";
			float p1X, p1Y, p2X, p2Y, p3X, p3Y;
			p1X = Game.CENTER.x - (Game.res.getWidth("main", p1) * .5f);
			p2X = Game.CENTER.x - (Game.res.getWidth("main", p2) * .5f);
			p3X = Game.CENTER.x - (Game.res.getWidth("main", p3) * .5f);
			p2Y = Game.CENTER.y + (Game.res.getHeight("main", p2) * .5f);
			p1Y = p2Y + Game.res.getHeight("main", p2);
			p3Y = p2Y - (Game.res.getHeight("main", p2));
			rect.x = min(p1X, p2X, p3X) - 10;
			rect.y = p3Y - Game.res.getHeight("main", p3) - 10;
			rect.height = ((p1Y - p3Y) + (Game.res.getHeight("main", p3))) + 20;
			rect.width = max(Game.res.getWidth("main", p1), Game.res.getWidth("main", p2), Game.res.getWidth("main", p3)) + 20;
			sr.begin(ShapeType.Filled);
			sr.setProjectionMatrix(cam.combined);
			sr.setColor(Color.BLACK);
			sr.rect(rect.x, rect.y, rect.width, rect.height);
			sr.end();
			sr.begin(ShapeType.Line);
			sr.setProjectionMatrix(cam.combined);
			sr.setColor(Color.WHITE);
			sr.rect(rect.x, rect.y, rect.width, rect.height);
			sr.end();
			sb.begin();
			sb.setProjectionMatrix(cam.combined);
			Game.res.getFont("main").draw(sb, p1, p1X, p1Y);
			Game.res.getFont("main").draw(sb, p2, p2X, p2Y);
			Game.res.getFont("main").draw(sb, p3, p3X, p3Y);
			sb.end();
		}
		
	}
	
	public void resize(Vector2 resize) {
		cam.resize(resize, true);
	}
	
	private float max(float n1, float n2, float n3) {
		return Math.max(Math.max(n1, n2), n3);
	}
	
	private float min(float n1, float n2, float n3) {
		return Math.min(Math.min(n1, n2), n3);
	}
	
}
