package map;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import tile.Tile;
import ui.Editor;

public class Map {
	public enum State { TILES, COLLISIONS }
	
	private ArrayList<Tile> tiles;
	
	private State mapState = State.TILES;
	
	protected Point mapLocation = new Point();
	
	public Map() {
		tiles = new ArrayList<Tile>();
	}
	
	public void setState(State state) {
		mapState = state;
	}
	
	public State getState() {
		return mapState;
	}
	
	private int smallestX, smallestY;
	
	public int getSmallestY() {
		return smallestY;
	}
	
	public int getSmallestX() {
		return smallestX;
	}
	
	public void add(int index, int mouseX, int mouseY) {
		Tile tile = new Tile(convert(mouseX - mapLocation.x), convert(mouseY - mapLocation.y));
		
		boolean a = true;
		
		if (mapState == State.TILES) {
			tile.addBackground(index);
			
			for (int i = tiles.size() - 1; i >= 0; i--)
				if (tiles.get(i).getX() == tile.getX() && tiles.get(i).getY() == tile.getY()) {
					if (tiles.get(i).getBackgroundIndex() == tile.getForegroundIndex()) {
						a = true;
						tiles.remove(i);
					}
					
					if (isOpaque(tiles.get(i).getBackgroundTile()) && isOpaque(tile.getBackgroundTile())) {
						a = true;
						tiles.remove(i);
					}
					
					else {
						a = false;
						tiles.get(i).addForeground(index);
					}
					
					break;
				}
			
			if (a)
				tiles.add(tile);
		}
		
		if (mapState == State.COLLISIONS) {
			for (int i = tiles.size() - 1; i >= 0; i--)
				if (tiles.get(i).getX() == tile.getX() && tiles.get(i).getY() == tile.getY()) {
					tiles.get(i).setSolid(true);
					break;
				}
		}
	}
	
	public void remove(int mouseX, int mouseY) {
		if (mapState == State.TILES)
			for(int i = tiles.size() - 1; i >= 0; i--)
				if(tiles.get(i).getX() == convert(mouseX - mapLocation.x) && tiles.get(i).getY() == convert(mouseY - mapLocation.y)) {
					tiles.remove(i);
					break;
				}
		
		if (mapState == State.COLLISIONS)
			for(int i = tiles.size() - 1; i >= 0; i--)
				if(tiles.get(i).getX() == convert(mouseX - mapLocation.x) && tiles.get(i).getY() == convert(mouseY - mapLocation.y)) {
					tiles.get(i).setSolid(false);
					break;
				}
	}
	
	public void setTiles(ArrayList<Tile> arr) {
		tiles = arr;
	}
	
	public void setMapX(int x) {
		mapLocation.x = x;
	}
	
	public void setMapY(int y) {
		mapLocation.y = y;
	}
	
	private int biggestX, biggestY;
	
	public int getBiggestX() {
		return biggestX;
	}
	
	public int getBiggestY() {
		return biggestY;
	}
	
	private boolean isOpaque(BufferedImage image) {
		for(int y = 0; y < image.getHeight(); y++)
			for(int x = 0; x < image.getWidth(); x++)
				if((image.getRGB(x, y) >> 24) == 0x00)
					return false;
		return true;
	}
	
	private int convert(int i) {
		if (i < 0)
			return ((i - 32) / 32) * 32;
		return (i / 32) * 32;
	}
	
	public int getWidth() {
		if (tiles.size() == 0)
			return 0;
		
		double mapSmallestX = Integer.MAX_VALUE;
		double mapBiggestX = Integer.MIN_VALUE;
		
		for (Tile tile : tiles) {
			if(tile.getX() < mapSmallestX)
				mapSmallestX = tile.getX();
			if(tile.getX() > mapBiggestX)
				mapBiggestX = tile.getX();
		}
		
		smallestX = (int) mapSmallestX;
		biggestX = (int) mapBiggestX + 1;
		
		return (int) (((mapBiggestX - mapSmallestX) / 32) + 1);
	}
	
	public int getHeight() {
		if (tiles.size() == 0)
			return 0;
		
		double mapSmallestY = Integer.MAX_VALUE;
		double mapBiggestY = Integer.MIN_VALUE;
		
		for(Tile tile : tiles) {
			if(tile.getY() < mapSmallestY)
				mapSmallestY = tile.getY();
			if(tile.getY() > mapBiggestY)
				mapBiggestY = tile.getY();
		}
		
		smallestY = (int) mapSmallestY;
		biggestY = (int) mapBiggestY + 1;
		
		return (int) (((mapBiggestY - mapSmallestY) / 32) + 1);
	}
	
	public int getX() {
		return -mapLocation.x;
	}
	
	public int getY() {
		return -mapLocation.y;
	}
	
	public ArrayList<Tile> getTiles() {
		return tiles;
	}
	
	public void renderDebug(Graphics graphics) {
		graphics.setColor(Color.BLACK);
		graphics.fillRect(0, 0, 640, 64);
		
		graphics.setColor(Color.WHITE);
		graphics.drawString("Map", 0, 10);
		graphics.drawString("State: " + mapState, 0, 20);
		graphics.drawString("Coor: <" + (-mapLocation.x) + ", " + (-mapLocation.y) + ">", 0, 30);
		graphics.drawString("W: " + getWidth(), 0, 40);
		graphics.drawString("H: " + getHeight(), 0, 50);
		graphics.drawString("# of Tiles: " + tiles.size(), 0, 60);
		graphics.setColor(Color.BLACK);
	}
	
	public void renderMap(Graphics graphics) {
		for (Tile tile : tiles)
			tile.render(graphics, mapLocation.x, mapLocation.y);
		
		if (mapState == State.COLLISIONS) {
			for (Tile tile : tiles)
				if (tile.isSolid()) {
					graphics.setColor(Color.RED);
					graphics.drawOval((int) tile.getX() + mapLocation.x, (int) tile.getY() + mapLocation.y, 32, 32);
					graphics.drawOval((int) tile.getX() + 1 + mapLocation.x, (int) tile.getY() + 1 + mapLocation.y, 30, 30);
				}
		}
		
		// draws grid
//		for (int i = -32; i < 641; i += 32)
//			for (int j = -32; j < 641; j += 32)
//				graphics.drawRect(i + (mapLocation.x % 32), j + (mapLocation.y % 32), 32, 32);
	}
}
