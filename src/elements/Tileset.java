package elements;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import tile.Tile;

// the tile set that stores the sprites and whatnot.
public final class Tileset implements Renderable {
	// the width and height of the original tile set.
	private final int WIDTH;
	private final int HEIGHT;
	
	// the number of tiles that line the x and y axes.
	private final int TILES_X;
	private final int TILES_Y;
	
	// the individual tiles that are in the tile set.
	private final BufferedImage[] tiles;
	
	// the tile index that is selected.
	private int selectedTileIndex;
	
	public Tileset(BufferedImage tileset) {
		this.WIDTH = tileset.getWidth();
		this.HEIGHT = tileset.getHeight();
		
		// calculates the amount of tiles in x and y axes.
		this.TILES_X = this.WIDTH / Tile.getWidth();
		this.TILES_Y = this.HEIGHT / Tile.getHeight();
		
		// the size is the width * height.
		this.tiles = new BufferedImage[this.TILES_X * this.TILES_Y];
		
		// cuts up the tileset and stores it into the array.
		int counter = 0;
		// y is the outer loop so the tiles are read horizontally.
		for(int y = 0; y < this.HEIGHT; y += Tile.getHeight())
			for(int x = 0; x < this.WIDTH; x += Tile.getWidth())
				this.tiles[counter++] = tileset.getSubimage(x, y, Tile.getWidth(), Tile.getHeight());
	}
	
	public void setSelectedTileIndex(int selectedTileIndex) {
		this.selectedTileIndex = selectedTileIndex;
	}
	
	public int getSelectedTileIndex() {
		return this.selectedTileIndex;
	}
	
	public BufferedImage getTile(int index) {
		return this.tiles[index];
	}
	
	// renders the tile set to the panel.
	@Override
	public void render(Graphics graphics, int offsetX, int offsetY) {
		// so that the highlight is red.
		graphics.setColor(Color.RED);
		
		int counter = 0;
		while(counter < this.tiles.length) {
			// calculates the x and y of each tile using only the tile index.
			int x = (counter % this.TILES_X) * Tile.getWidth();
			int y = (counter / this.TILES_X) * Tile.getHeight();
			
			// draws the individual tile to the panel with the offsets.
			graphics.drawImage(this.getTile(counter), x + offsetX, y + offsetY, null);
			
			// highlights the selected tile.
			if(counter == this.selectedTileIndex)
				graphics.drawRect(x, y, Tile.getWidth() - 1, Tile.getHeight() - 1);
			
			counter++;
		}
		
		// draws the selected tile in the corner.
		graphics.drawImage(this.getTile(this.selectedTileIndex), 515, 0, null);
	}
}
