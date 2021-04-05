package map;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

import tile.Tile;

public class Map {
	public enum State { BACKGROUND, FOREGROUND, COLLISIONS; }
	
	private ArrayList<Integer> foregroundIndices;
	private ArrayList<Integer> backgroundIndices;
	
	private State mapState = State.BACKGROUND;
	
	private Point mapLocation = new Point();
	
	private int width;
	private int height;
	
	public Map() {
		backgroundIndices = new ArrayList<Integer>();
		foregroundIndices = new ArrayList<Integer>();
	}
	
	public void setState(State state) {
		mapState = state;
	}
	
	// tileset path
	// map x
	// map y
	// w
	// h
	// BACKGROUND
	// index
	// -
	// index
	// FOREGROUND
	// index
	// -
	// index
	
	public void addTile(Tile tile) {
		
//		tiles.add(tile);
	}
	
	public void render(Graphics graphics) {
		graphics.setColor(Color.BLACK);
		graphics.fillRect(0, 0, 640, 64);
		
		graphics.setColor(Color.WHITE);
		graphics.drawString("Map", 0, 10);
		graphics.drawString("State: " + mapState, 0, 20);
		graphics.drawString("Coor: <" + mapLocation.x + ", " + mapLocation.y + ">", 0, 30);
		graphics.drawString("W: " + width, 0, 40);
		graphics.drawString("H: " + height, 0, 50);
		graphics.setColor(Color.BLACK);
		
//		for (Tile tile : tiles)
//			tile.render(graphics);
	}
}
