package elements;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;

import tile.Tile;
import ui.Editor;

public class Tileset {
	private Dimension tilesetSize, tileGrid;
	
	private BufferedImage[] tiles;
	
	private int selectedTileIndex;
	
	private Point tilesetLocation = new Point();
	
	public void setTileset(BufferedImage tileset) {
		if (tiles != null)
			return;
		
		tilesetSize = new Dimension(tileset.getWidth(), tileset.getHeight());
		tileGrid = new Dimension(tileset.getWidth() / Tile.getWidth(), tileset.getHeight() / Tile.getHeight());
		
		tiles = new BufferedImage[tileGrid.width * tileGrid.height];
		
		int counter = 0;
		for (int y = 0; y < tileset.getHeight(); y += Tile.getHeight())
			for (int x = 0; x < tileset.getWidth(); x += Tile.getWidth())
				tiles[counter++] = tileset.getSubimage(x, y, Tile.getWidth(), Tile.getHeight());
	}
	
	public void setSelectedTileIndex(int mouseX, int mouseY) {
		if (mouseX < tilesetLocation.x)
			return;
		if (mouseX > tilesetLocation.x + tilesetSize.width - 1)
			return;
		if (mouseY < tilesetLocation.y + Tile.getHeight() * 2)
			return;
		if (mouseY > tilesetLocation.y + tilesetSize.height + Tile.getHeight() * 2 - 1)
			return;
		
		int x = (mouseX - tilesetLocation.x) / Tile.getWidth();
		int y = (mouseY - tilesetLocation.y) / Tile.getHeight() - 2;
		
		selectedTileIndex = y * tileGrid.width + x;
	}
	
	public BufferedImage getTile(int index) {
		return tiles[index];
	}
	
	public int getSelectedTileIndex() {
		return selectedTileIndex;
	}
	
	public Point getTilesetLocation() {
		return tilesetLocation;
	}
	
	private void renderSelectedTile(Graphics graphics) {
		// draws background.
		graphics.setColor(Color.BLACK);
		graphics.fillRect(0, 0, 640, 64);
		
		// draws the selected tile in the corner.
		if (!exists()) {
			graphics.setColor(Color.WHITE);
			graphics.fillRect(304, 16, 32, 32);
			graphics.setColor(Color.BLACK);
		}
		
		else graphics.drawImage(getTile(selectedTileIndex), 304, 16, null);
	}
	
	public boolean exists() {
		return tiles != null;
	}
	
	public void render(Graphics graphics) {
		renderSelectedTile(graphics);
		
		graphics.drawString("Tileset", 0, 74);
		graphics.drawString("<" + tilesetLocation.x + ", " + tilesetLocation.y + ">", 0, 89);
		
		if (!exists())
			return;
		
		int counter = 0;
		while (counter < tiles.length) {
			// calculates the x and y of each tile using only the tile index.
			int x = counter % tileGrid.width * Tile.getWidth();
			int y = counter / tileGrid.width * Tile.getHeight();
			
			// draws the individual tile to the panel with the offsets.
			graphics.drawImage(getTile(counter), x + tilesetLocation.x, y + (Tile.getHeight() * 2) + tilesetLocation.y, null);
			
			// highlights the selected tile.
			if (counter == selectedTileIndex) {
				graphics.setColor(Color.RED);
				graphics.drawRect(x + tilesetLocation.x, y + (Tile.getHeight() * 2) + tilesetLocation.y, Tile.getWidth() - 1, Tile.getHeight() - 1);
			}
			
			counter++;
		}
		
		// draws border around tileset.
		graphics.setColor(Color.BLACK);
		graphics.drawRect(tilesetLocation.x, tilesetLocation.y + Tile.getHeight() * 2, tilesetSize.width, tilesetSize.height);
	}
}
