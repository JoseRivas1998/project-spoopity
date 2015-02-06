package com.sshsgd.spoopity.entities;

import java.util.Random;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;
import com.sshsgd.spoopity.Game;
import com.sshsgd.spoopity.MyCamera;

public class EnemyDemon extends Entity {

	private MyCamera cam;
	private float speed, radians;
	private Vector2 vel;
	private Vector2 spawn;
	private boolean moveTowards;
	private float eX, eY;
	private Timer t;
	
	public static final int MOVE_TOWARDS = 0;
	public static final int MAX_HEALTH = 100;
	public static float MAX_VEL = 5;
	
	private int health;
	private int moveMode;

	
	
	public EnemyDemon(Vector2 position, float width, float height, int moveMode) {
		bounds = new Rectangle(position.x, position.y, width, height);
		spawn = new Vector2(position);
		this.moveMode = moveMode;
		init(width, height, moveTowards);
	}
	public EnemyDemon(float x, float y, float width, float height, int moveMode) {
		bounds = new Rectangle(x, y, width, height);
		spawn = new Vector2(x, y);
		this.moveMode = moveMode;
		init(x, y, moveTowards);
		
	}
	protected void init(float x, float y, boolean moveTowards) {
		vel = new Vector2();
		vel.x = 0;
		vel.y = 0;
		
		eX = x;
		eY = y;
		
		cam = new MyCamera(256, 256);
		reset();
	}

	private void reset() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void draw(ShapeRenderer sr, SpriteBatch sb, float dt) {
		
		sr.rect(getX(), getY(), getWidth(), getHeight());
		
	}
	
	public void update(float dt, EnemyRectangle e) {
		
		if(moveMode == MOVE_TOWARDS) {
				moveTowards(e);
		}
		
		cam.position.set(bounds.x + (bounds.width * .5f), bounds.y + (bounds.height * .5f), 0);
		cam.update();
		if(cam.inView(e.getPosition()) && moveTowards) {
			moveTowards(e);
		} else if(bounds.x != eX || bounds.y != eY) {
			returnToSpawn();
		}
		
	
		bounds.x += vel.x;
		bounds.y += vel.y;
		
	}
	
	private void returnToSpawn() {
		if(getX() > spawn.x) {
			vel.x = -3;
		} else if(getX() == spawn.x) {
			vel.x = 0;
		}

		if(getX() < spawn.x) {
			vel.x = 3;
		} else if(getX() == spawn.x) {
			vel.x = 0;
		}
		
		if(getY() > spawn.y) {
			vel.y = -3;
		} else if(getY() == spawn.y) {
			vel.y = 0;
		}
		
		if(getY() < spawn.y) {
			vel.y = 3;
		} else if(getY() == spawn.y) {
			vel.y = 0;
		}
	}
	
	private void moveTowards(EnemyRectangle e) {
		if(e.getX() > this.bounds.x) {
			this.vel.x = 5;
		}
		if(e.getX() < this.bounds.x) {
			this.vel.x = -5;
		}
		if(e.getX() == this.bounds.x) {
			this.vel.x = 0;
		}
		if(e.getY() > this.bounds.y) {
			this.vel.y = 5;
		}
		if(e.getY() < this.bounds.y) {
			this.vel.y = -5;
		}
		if(e.getY() == this.bounds.y) {
			this.vel.y = 0;
		}
		
		
	}
	
	private void maxHealth() {
		
	}
	
	private void health() {
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
	
}
