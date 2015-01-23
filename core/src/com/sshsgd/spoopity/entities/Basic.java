package com.sshsgd.spoopity.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public class Basic extends Entity {

	private Rectangle ls, rs, ts, bs;
	private Texture t;
	private TextureRegion currentFrame;
	private Animation anim;
	private float stateTime;
	
	private boolean touchingG;
	
	public Basic(String path, int numFrames, float x, float y, float animTime) {
		super();
		bounds.x = x;
		bounds.y = y;
		ls = new Rectangle();
		rs = new Rectangle();
		ts = new Rectangle();
		bs = new Rectangle();
		
		t = new Texture(path);
		TextureRegion[] frames = TextureRegion.split(t, t.getWidth() / numFrames, t.getHeight())[0];
        
		currentFrame = frames[0];
		
		anim = new Animation(animTime, frames);
		
		stateTime = 0;
	}
	
	@Override
	public void draw(ShapeRenderer sr, SpriteBatch sb, float dt) {
		stateTime += dt;
		currentFrame = anim.getKeyFrame(stateTime, true);
		sb.draw(currentFrame, getX(), getY());
	}
	
	public void upadate(World w) {
		bounds.width = currentFrame.getRegionWidth();
		bounds.height = currentFrame.getRegionHeight();
		
		if(!touchingG && vel.y > -20) {
			vel.y--;
		}
		
		bounds.x += vel.x;
		bounds.y += vel.y;
		
		if(bounds.x < 0) {
			bounds.x = 0;
		}
		if(getRight() > w.getWidth()) {
			bounds.x = w.getWidth() - bounds.width;
		}
		if(getTop() > w.getHeight()) {
			bounds.y = w.getHeight() - bounds.height;
		}
		
		if(getTop() < -500) {
			bounds.x = 32;
			bounds.y = 32;
		}
		
		resetBounds();
		collisions(w);
		resetBounds();
	}

	private void resetBounds() {
		ls.width = 2;
		rs.width = 2;
		ts.width = 4;
		bs.width = 4;
		ls.height = 4;
		rs.height = 4;
		ts.height = 2;
		bs.height = 2;
		
		ls.x = bounds.x;
		ls.y = getCenter().y - (ls.height * .5f);
		rs.x = getRight() - rs.width;
		rs.y = ls.y;
		bs.x = getCenter().x - (bs.width * .5f);
		bs.y = bounds.y;
		ts.x = bs.x;
		ts.y = getTop() - ts.height;
	}
	
	private void collisions(World w) {
		for(Rectangle r : w.getBounds()) {
			if(bs.overlaps(r)) {
				bounds.y = r.y + r.height - 4;
				touchingG = true;
				break;
			} else {
				touchingG = false;
			}
		}
		resetBounds();
		for(Rectangle r : w.getBounds()) {
			if(rs.overlaps(r)) {
				bounds.x = r.x - bounds.width;
				break;
			} 
		}
		resetBounds();
		for(Rectangle r : w.getBounds()) {
			if(ls.overlaps(r)) {
				bounds.x = r.x + bounds.width;
				break;
			}
		}
		resetBounds();
		for(Rectangle r : w.getBounds()) {
			if(ts.overlaps(r)) {
				bounds.y = r.y - bounds.height;
				vel.y = 0;
				break;
			}
		}
	}

	@Override
	public void dispose() {
		t.dispose();

	}

}
