package tile;

import java.awt.Graphics;

// a tile.
public class Tile {
	private static int width = 32;
	private static int height = 32;
	
	private int x;
	private int y;
	
	private int tileIndex;
	
	private boolean isSolid;
	
	public Tile(int x, int y, int tileIndex) {
		this.x = x;
		this.y = y;
		
		this.tileIndex = tileIndex;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public static void setWidth(int width) {
		// cannot set the tile width if it's already set.
		// cannot set the tile width if it's too small.
		if(Tile.width != -1 || width <= 1)
			return;
		
		Tile.width = width;
	}
	
	public static int getWidth() {
		return Tile.width;
	}
	
	public static void setHeight(int height) {
		// cannot set the tile height if it's already set.
		// cannot set the tile height if it's too small.
		if(Tile.height != -1 || height <= 1)
			return;
		
		Tile.height = height;
	}
	
	public static int getHeight() {
		return Tile.height;
	}
	
	public void render(Graphics graphics) {
		
	}
}
