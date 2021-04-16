package tile;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import ui.Editor;

// a tile.
public class Tile {
	private int x;
	private int y;
	
	private int[] indices = {-1, -1};
	private BufferedImage[] tiles = new BufferedImage[2];
	
	private boolean isSolid;
	
	public Tile(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void addBackground(int index) {
		indices[0] = index;
		tiles[0] = Editor.getCurrentEditor().getTile(index);
	}
	
	public void addForeground(int index) {
		indices[1] = index;
		
		if (index != -1)
			tiles[1] = Editor.getCurrentEditor().getTile(index);
	}
	
	public int getBackgroundIndex() {
		return indices[0];
	}
	
	public int getForegroundIndex() {
		return indices[1];
	}
	
	public BufferedImage getBackgroundTile() {
		return tiles[0];
	}
	
	public BufferedImage getForegroundTile() {
		return tiles[1];
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public boolean isSolid() {
		return isSolid;
	}
	
	public void setSolid(boolean solid) {
		isSolid = solid;
	}
	
	public void render(Graphics graphics, int offsetX, int offsetY) {
		if (tiles[0] != null)
			graphics.drawImage(tiles[0], x + offsetX, y + offsetY, null);
		
		if (tiles[1] != null)
			graphics.drawImage(tiles[1], x + offsetX, y + offsetY, null);
	}
}
