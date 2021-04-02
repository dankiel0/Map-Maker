package elements;

import java.awt.Graphics;
import java.util.ArrayList;

import tile.Tile;

public class Map {
	private ArrayList<Tile> tiles;
	
	public Map() {
		tiles = new ArrayList<Tile>();
	}
	
	public void addTile(Tile tile) {
		tiles.add(tile);
	}
	
	public void render(Graphics graphics) {
		for (Tile tile : tiles)
			tile.render(graphics);
	}
}
