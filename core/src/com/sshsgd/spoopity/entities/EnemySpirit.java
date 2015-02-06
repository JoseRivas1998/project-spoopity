package com.sshsgd.spoopity.entities;

import java.util.Random;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.sshsgd.spoopity.Game;

public class EnemySpirit extends Entity {

	private float speed, radians;
	private Vector2 vel;
	private Vector2 spawn;
	
	private static final int UP_DOWN = 0;
	private static final int MAX_HEALTH = 100;
	
	private int health;
	private int moveMode;
	private int fall;
	
	
	public EnemySpirit(Vector2 position, float width, float height, int moveMode) {
		bounds = new Rectangle(position.x, position.y, width, height);
		spawn = new Vector2(position);
		this.moveMode = moveMode;
		init();
	}
	public EnemySpirit(float x, float y, float width, float height, int moveMode) {
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
		if(moveMode == UP_DOWN) {
				fly();
				moveLeftRight();
		}
	
		bounds.x += vel.x;
		bounds.y += vel.y;
		
	}
//to do list:
//	Make bounds
//	When enemy reaches bounds, multiply by -1
	private void moveLeftRight() {
		if(getX() >= spawn.x + 75 || getX() <= spawn.x - 75) {
			vel.x *= -1;
		} 
	
	}
	

	
	private void fly() {
		
		if(getY() >= spawn.y + 50) {
			vel.y--;
		
		} else if (getY() >= spawn.y - 45 && getX() <= spawn.x) {
			vel.y++;
			
		} else if (getY() 
				>= spawn.y - 75 && getX() >= spawn.x) {
			vel.y++;
			
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
