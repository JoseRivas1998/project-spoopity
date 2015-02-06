package com.sshsgd.spoopity.entities;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.EllipseMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Ellipse;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.sshsgd.spoopity.MyCamera;

public class World {


	private Array<Rectangle> bounds;
	private Array<Vector2> objects;
	private Array<EnemySpirit> spirits;
	private Array<EnemyRectangle> rectangles;
	private Array<EnemyDemon> demons;
	
	private TiledMap tileMap;
	private OrthogonalTiledMapRenderer tmr;
	
	private float width, height, tileWidth, tileHeight;
	
	private float tileSize;
	
	public World() {
		bounds = new Array<Rectangle>();
		objects = new Array<Vector2>();
		spirits = new Array<EnemySpirit>();
		rectangles = new Array<EnemyRectangle>();
		demons = new Array<EnemyDemon>();
		
		createTiles();
	}
	
	private void createTiles() {
		tileMap = new TmxMapLoader().load("maps/test.tmx");
		tmr = new OrthogonalTiledMapRenderer(tileMap);
		tileSize = tileMap.getProperties().get("tilewidth", Integer.class);
		
		TiledMapTileLayer ground;
		MapLayer object;
		
		ground = (TiledMapTileLayer) tileMap.getLayers().get("Tile Layer 1");
		object = tileMap.getLayers().get("terrys");
		
		createLayer(ground, bounds);
		
		createObjectLayer(object, objects);

		object = tileMap.getLayers().get("spirits");
		createEnemySpirits(object, spirits);
		
		object = tileMap.getLayers().get("rectangles");
		createEnemyRectangle(object, rectangles);
		
		object = tileMap.getLayers().get("demons");
		createEnemyDemon(object, demons);
		
		width = ground.getWidth() * tileSize;
		height = ground.getHeight() * tileSize;
		tileWidth = ground.getWidth();
		tileHeight = ground.getHeight();
		
	}
	
	private void createLayer(TiledMapTileLayer layer, Array<Rectangle> rect) {
		
		for(int row = 0; row < layer.getHeight(); row++) {
			for(int col = 0; col < layer.getWidth(); col++) {
				
				Cell cell = layer.getCell(col, row);
				
				if(cell == null) continue;
				if(cell.getTile() == null) continue;
				
				rect.add(new Rectangle(col * tileSize, row * tileSize, tileSize, tileSize));
				
			}
		}
		
	}
	
	private void createObjectLayer(MapLayer layer, Array<Vector2> objects) {
		
		for(MapObject mo : layer.getObjects()) {
			Ellipse e = ((EllipseMapObject) mo).getEllipse();
			float x = e.x;
			float y = e.y;
			
			objects.add(new Vector2(x, y));
		}
		
	}
	
	public void createEnemySpirits(MapLayer layer, Array<EnemySpirit> enemies ) {
		
		for(MapObject mo : layer.getObjects()) {
				Ellipse e = ((EllipseMapObject) mo).getEllipse();
				float x = e.x;
				float y = e.y;
				
				enemies.add(new EnemySpirit(x, y, 32, 32, EnemyRectangle.LEFT_RIGHT));
		}
	}
	
	public void createEnemyRectangle(MapLayer layer, Array<EnemyRectangle> enemies ) {
		
		for(MapObject mo : layer.getObjects()) {
				Ellipse e = ((EllipseMapObject) mo).getEllipse();
				float x = e.x;
				float y = e.y;
				
				enemies.add(new EnemyRectangle(x, y, 32, 32, EnemyRectangle.LEFT_RIGHT));
		}
	}
	
	public void createEnemyDemon(MapLayer layer, Array<EnemyDemon> enemies) {
		
		for(MapObject mo : layer.getObjects()) {
				Ellipse e = ((EllipseMapObject) mo).getEllipse();
				float x = e.x;
				float y = e.y;
				
				enemies.add(new EnemyDemon(x, y, 32, 32, EnemyRectangle.LEFT_RIGHT));
		}
	}
	
	public void render(MyCamera cam) {
		tmr.setView(cam);
		tmr.render();
	}
	
	public Array<Rectangle> getBounds() {
		return bounds;
	}
 
	public void setBounds(Array<Rectangle> bounds) {
		this.bounds = bounds;
	}
 
	public Array<Vector2> getObjects() {
		return objects;
	}
 
	public void setObjects(Array<Vector2> objects) {
		this.objects = objects;
	}
	
	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public float getTileWidth() {
		return tileWidth;
	}

	public void setTileWidth(float tileWidth) {
		this.tileWidth = tileWidth;
	}

	public float getTileHeight() {
		return tileHeight;
	}

	public void setTileHeight(float tileHeight) {
		this.tileHeight = tileHeight;
	}

	public Array<EnemySpirit> getSpirits() {
		return spirits;
	}

	public void setSpirits(Array<EnemySpirit> spirits) {
		this.spirits = spirits;
	}

	public Array<EnemyRectangle> getRectangles() {
		return rectangles;
	}

	public void setRectangles(Array<EnemyRectangle> rectangles) {
		this.rectangles = rectangles;
	}

	public Array<EnemyDemon> getDemons() {
		return demons;
	}

	public void setDemons(Array<EnemyDemon> demons) {
		this.demons = demons;
	}
	
}
