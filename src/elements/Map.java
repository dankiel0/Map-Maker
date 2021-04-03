package elements;

import java.awt.Graphics;
import java.util.ArrayList;

import tile.Tile;

public class Map {
	public static int FOREGROUND = 0;
	public static int BACKGROUND = 1;
	
	private ArrayList<Integer> foregroundIndices;
	private ArrayList<Integer> backgroundIndices;
	
	private int currentMapState = FOREGROUND;
	
	private int x;
	private int y;
	
	private int width;
	private int height;
	
	public Map() {
		backgroundIndices = new ArrayList<Integer>();
		foregroundIndices = new ArrayList<Integer>();
	}
	
	public void setMapState(int newMapState) {
		currentMapState = newMapState;
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
		graphics.drawString("Map - Foreground", 0, 10);
		graphics.drawString("<" + x + ", " + y + ">", 0, 25);
		
//		for (Tile tile : tiles)
//			tile.render(graphics);
	}
}
