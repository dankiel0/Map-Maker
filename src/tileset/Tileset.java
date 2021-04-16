package tileset;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;

import resource_loaders.ImageLoader;

public class Tileset {
	private Dimension tilesetSize;
	private Dimension tileGrid;
	
	private BufferedImage[] tiles;
	
	private int selectedTileIndex;
	
	private Point tilesetLocation = new Point();
	
	private String path;
	
	public void setTileset(String tilesetPath) {
		if (exists())
			return;
		
		path = tilesetPath;
		
		BufferedImage tileset = ImageLoader.loadFromDrive(tilesetPath);
		
		tilesetSize = new Dimension(tileset.getWidth(), tileset.getHeight());
		tileGrid = new Dimension(tileset.getWidth() / 32, tileset.getHeight() / 32);
		
		tiles = new BufferedImage[tileGrid.width * tileGrid.height];
		
		int counter = 0;
		for (int y = 0; y < tileset.getHeight(); y += 32)
			for (int x = 0; x < tileset.getWidth(); x += 32)
				tiles[counter++] = tileset.getSubimage(x, y, 32, 32);
	}
	
	public void setSelectedTileIndex(int mouseX, int mouseY) {
		if (mouseX < tilesetLocation.x)
			return;
		if (mouseX > tilesetLocation.x + tilesetSize.width - 1)
			return;
		if (mouseY < tilesetLocation.y)
			return;
		if (mouseY > tilesetLocation.y + tilesetSize.height - 1)
			return;
		
		int x = (mouseX - tilesetLocation.x) / 32;
		int y = (mouseY - tilesetLocation.y) / 32;
		
		selectedTileIndex = y * tileGrid.width + x;
	}
	
	public boolean exists() {
		return tiles != null;
	}
	
	public int getSelectedTileIndex() {
		return selectedTileIndex;
	}
	
	public BufferedImage getTile(int index) {
		return tiles[index];
	}
	
	public Point getLocation() {
		return tilesetLocation;
	}
	
	public String getPath() {
		return path;
	}
	
	public void renderDebug(Graphics graphics) {
		// draws background.
		graphics.setColor(Color.BLACK);
		graphics.fillRect(0, 0, 640, 64);
		
		// draws the selected tile.
		if (!exists()) {
			graphics.setColor(Color.WHITE);
			graphics.fillRect(304, 16, 32, 32);
			graphics.setColor(Color.BLACK);
		}
		
		else graphics.drawImage(tiles[selectedTileIndex], 304, 16, null);
		
		graphics.setColor(Color.WHITE);
		graphics.drawString("Tileset", 0, 10);
		graphics.drawString("Coor: <" + tilesetLocation.x + ", " + tilesetLocation.y + ">", 0, 20);
		graphics.drawString("Index: " + selectedTileIndex, 0, 30);
		graphics.setColor(Color.BLACK);
	}
	
	public void renderTileset(Graphics graphics) {
		if (exists()) {
			int counter = 0;
			while (counter < tiles.length) {
				// calculates the x and y of each tile using only the tile index.
				int x = counter % tileGrid.width * 32;
				int y = counter / tileGrid.width * 32;
				
				// draws the individual tile to the panel with the offsets.
				graphics.drawImage(tiles[counter], x + tilesetLocation.x, y + tilesetLocation.y, null);
				
				// highlights the selected tile.
				if (counter == selectedTileIndex) {
					graphics.setColor(Color.RED);
					graphics.drawRect(x + tilesetLocation.x + 1, y + tilesetLocation.y + 1, 30, 30);
				}
				
				counter++;
			}
			
			// draws border around tileset.
			graphics.setColor(Color.BLACK);
			graphics.drawRect(tilesetLocation.x, tilesetLocation.y, tilesetSize.width, tilesetSize.height);
		}
	}
}
