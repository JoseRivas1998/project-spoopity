package com.sshsgd.spoopity.entities;

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
	
	private static final int LEFT_RIGHT = 0;
	private static final int UP_DOWN = 1;
	private static final int MAX_HEALTH = 100;
	
	private int health;
	private int moveMode;
	
	
	
	public EnemyRectangle(Vector2 position, float width, float height, int moveMode) {
		bounds = new Rectangle(position.x, position.y, width, height);
		spawn = new Vector2(position);
		this.moveMode = moveMode;
	}
	public EnemyRectangle(float x, float y, float width, float height, int moveMode) {
		bounds = new Rectangle(x, y, width, height);
		spawn = new Vector2(x, y);
		this.moveMode = moveMode;
		init();
		
	}
	protected void init() {
		vel = new Vector2();
		reset();
	}

	private void reset() {
		// TODO Auto-generated method stub
		System.out.println("It worked");
	}
	@Override
	public void draw(ShapeRenderer sr, SpriteBatch sb, float dt) {
		
		sr.rect(getX(), getY(), getWidth(), getHeight());
		
	}
	
	public void update() {
		if(moveMode == LEFT_RIGHT) {
				moveLeftRight();
		}
		if(moveMode == UP_DOWN) {
				moveUpDown();
		}
		bounds.x += vel.x;
		bounds.y += vel.y;
		
	}
	private void moveLeftRight() {
			vel.x *= -1;
			if(vel.x > 0) {
				vel.x++;
			} else {
				vel.x--;
			}
		}
	
	private void moveUpDown() {
		vel.y *= -1;
		if(vel.y > 0) {
			vel.y++;
		} else {
			vel.y--;
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
