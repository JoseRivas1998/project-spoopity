package com.sshsgd.spoopity.entities;

import java.util.Random;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.sshsgd.spoopity.Game;

public class EnemyRectangle extends Entity {

	private float speed, radians;
	private Vector2 vel;
	private Vector2 spawn;
	
	public static final int LEFT_RIGHT = 0;
	public static final int MAX_HEALTH = 100;
	
	private int health;
	private int moveMode;
	
	
	
	public EnemyRectangle(Vector2 position, float width, float height, int moveMode) {
		bounds = new Rectangle(position.x, position.y, width, height);
		spawn = new Vector2(position);
		this.moveMode = moveMode;
		init();
	}
	public EnemyRectangle(float x, float y, float width, float height, int moveMode) {
		bounds = new Rectangle(x, y, width, height);
		spawn = new Vector2(x, y);
		this.moveMode = moveMode;
		init();
		
	}
	protected void init() {
		vel = new Vector2();
		vel.x = 1;
		reset();
	}

	private void reset() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void draw(ShapeRenderer sr, SpriteBatch sb, float dt) {
		
		sr.rect(getX(), getY(), getWidth(), getHeight());
		
	}
	
	public void update() {
		if(moveMode == LEFT_RIGHT) {
				moveLeftRight();
				
		}
	
		bounds.x += vel.x;
		bounds.y += vel.y;
		
	}
//to do list:
//	Make bounds
//	When enemy reaches bounds, multiply vel by -1
	private void moveLeftRight() {
		if(getX() >= spawn.x + 20) {
			vel.x *= -1;
			if(vel.x > 0) {
				vel.x++;
			} else {
				vel.x--;
			}
		}
		if(getX() <= spawn.x - 20) {
			vel.x *= -1;
			if(vel.x > 0) {
				vel.x--;
			} else {
				vel.x++;
			}
			
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
