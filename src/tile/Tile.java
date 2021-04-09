package tile;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import ui.Editor;

// a tile.
public class Tile {
	private double x;
	private double y;
	
	private int index;
	
	private boolean isSolid;
	
	private BufferedImage tile;
	
	public Tile(int x, int y, int tileIndex) {
		this.x = x;
		this.y = y;
		
		index = tileIndex;
		
		tile = Editor.getTile(index);
	}
	
	public int getIndex() {
		return index;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public void setSolid(boolean solid) {
		isSolid = solid;
	}
	
	public boolean isSolid() {
		return isSolid;
	}
	
	public void render(Graphics graphics, int offsetX, int offsetY) {
		graphics.drawImage(tile, (int) (x + offsetX), (int) (y + offsetY), null);
	}
}
