package map;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import tile.Tile;
import ui.Editor;

public class Map {
	public enum State { BACKGROUND, FOREGROUND, COLLISIONS; }
	
	private ArrayList<Tile> backgroundTiles;
	private ArrayList<Tile> foregroundTiles;
	
	private State mapState = State.BACKGROUND;
	
	protected Point mapLocation = new Point();
	
	public Map() {
		backgroundTiles = new ArrayList<Tile>();
		foregroundTiles = new ArrayList<Tile>();
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
	
	public void addBackgroundTile(int index, int mouseX, int mouseY) {
		Tile tile = new Tile(convert(mouseX - mapLocation.x), convert(mouseY - mapLocation.y), index);
		
		for (int i = backgroundTiles.size() - 1; i >= 0; i--)
			if (backgroundTiles.get(i).getX() == tile.getX() && backgroundTiles.get(i).getY() == tile.getY()) {
				if (backgroundTiles.get(i).getIndex() == tile.getIndex() || (!isTransparent(Editor.getTile(backgroundTiles.get(i).getIndex())) && !isTransparent(Editor.getTile(index))))
					backgroundTiles.remove(i);
				break;
			}
		
		backgroundTiles.add(tile);
	}
	
	public void addForegroundTile(int index, int mouseX, int mouseY) {
		Tile tile = new Tile(convert(mouseX - mapLocation.x), convert(mouseY - mapLocation.y), index);
		
		for(int i = foregroundTiles.size() - 1; i >= 0; i--)
			if(foregroundTiles.get(i).getX() == tile.getX() && foregroundTiles.get(i).getY() == tile.getY()) {
				if(foregroundTiles.get(i).getIndex() == tile.getIndex())
					foregroundTiles.remove(i);
				break;
			}
		
		foregroundTiles.add(tile);
	}
	
	public void removeBackgroundTile(int mouseX, int mouseY) {
		for(int i = backgroundTiles.size() - 1; i >= 0; i--)
			if(backgroundTiles.get(i).getX() == convert(mouseX - mapLocation.x) && backgroundTiles.get(i).getY() == convert(mouseY - mapLocation.y)) {
				backgroundTiles.remove(i);
				break;
			}
	}
	
	public void addCollision(int mouseX, int mouseY) {
		for(int i = backgroundTiles.size() - 1; i >= 0; i--)
			if(backgroundTiles.get(i).getX() == convert(mouseX - mapLocation.x) && backgroundTiles.get(i).getY() == convert(mouseY - mapLocation.y)) {
				backgroundTiles.get(i).setSolid(true);
				break;
			}
	}
	
	public void setBackground(ArrayList<Tile> tiles) {
		backgroundTiles = tiles;
	}
	
	public void setForeground(ArrayList<Tile> tiles) {
		foregroundTiles = tiles;
	}
	
	public void setMapX(int x) {
		mapLocation.x = x;
	}
	
	public void setMapY(int y) {
		mapLocation.y = y;
	}
	
	public void removeCollision(int mouseX, int mouseY) {
		for(int i = backgroundTiles.size() - 1; i >= 0; i--)
			if(backgroundTiles.get(i).getX() == convert(mouseX - mapLocation.x) && backgroundTiles.get(i).getY() == convert(mouseY - mapLocation.y)) {
				backgroundTiles.get(i).setSolid(false);
				break;
			}
	}
	
	public void removeForegroundTile(int mouseX, int mouseY) {
		for(int i = foregroundTiles.size() - 1; i >= 0; i--)
			if(foregroundTiles.get(i).getX() == convert(mouseX - mapLocation.x) && foregroundTiles.get(i).getY() == convert(mouseY - mapLocation.y)) {
				foregroundTiles.remove(i);
				break;
			}
	}
	
	private int biggestX, biggestY;
	
	public int getBiggestX() {
		return biggestX;
	}
	
	public int getBiggestY() {
		return biggestY;
	}
	
	private boolean isTransparent(BufferedImage image) {
		for(int y = 0; y < image.getHeight(); y++)
			for(int x = 0; x < image.getWidth(); x++)
				if((image.getRGB(x, y) >> 24) == 0x00)
					return true;
		return false;
	}
	
	private int convert(int i) {
		if (i < 0)
			return ((i - 32) / 32) * 32;
		return (i / 32) * 32;
	}
	
	public int getWidth() {
		if (backgroundTiles.size() == 0)
			return 0;
		
		double mapSmallestX = Integer.MAX_VALUE;
		double mapBiggestX = Integer.MIN_VALUE;
		
		for (Tile tile : backgroundTiles) {
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
		if (backgroundTiles.size() == 0)
			return 0;
		
		double mapSmallestY = Integer.MAX_VALUE;
		double mapBiggestY = Integer.MIN_VALUE;
		
		for(Tile tile : backgroundTiles) {
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
	
	public ArrayList<Tile> getBackground() {
		return backgroundTiles;
	}
	
	public ArrayList<Tile> getForeground() {
		return foregroundTiles;
	}
	
	private boolean highlightBackground;
	private boolean highlightForeground;
	
	public void triggerBackground() {
		highlightBackground = !highlightBackground;
		Editor.repaintMap();
	}
	
	public void triggerForeground() {
		highlightForeground = !highlightForeground;
		Editor.repaintMap();
	}
	
	public void render(Graphics graphics) {
		for (Tile tile : backgroundTiles) {
			tile.render(graphics, mapLocation.x, mapLocation.y);
			if (highlightBackground) {
				graphics.setColor(Color.YELLOW);
				graphics.drawOval((int) tile.getX() + mapLocation.x, (int) tile.getY() + mapLocation.y, 32, 32);
				graphics.drawOval((int) tile.getX() + 1 + mapLocation.x, (int) tile.getY() + 1 + mapLocation.y, 30, 30);
			}
		}
		
		for (Tile tile : foregroundTiles) {
			tile.render(graphics, mapLocation.x, mapLocation.y);
			if (highlightForeground) {
				graphics.setColor(Color.YELLOW);
				graphics.drawOval((int) tile.getX() + mapLocation.x, (int) tile.getY() + mapLocation.y, 32, 32);
				graphics.drawOval((int) tile.getX() + 1 + mapLocation.x, (int) tile.getY() + 1 + mapLocation.y, 30, 30);
			}
		}
		
		if (mapState == State.COLLISIONS) {
			for (Tile tile : backgroundTiles)
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
		
		graphics.setColor(Color.BLACK);
		graphics.fillRect(0, 0, 640, 64);
		
		graphics.setColor(Color.WHITE);
		graphics.drawString("Map", 0, 10);
		graphics.drawString("State: " + mapState, 0, 20);
		graphics.drawString("Coor: <" + (-mapLocation.x) + ", " + (-mapLocation.y) + ">", 0, 30);
		graphics.drawString("W: " + getWidth(), 0, 40);
		graphics.drawString("H: " + getHeight(), 0, 50);
		graphics.drawString("# of Tiles: " + (backgroundTiles.size() + foregroundTiles.size()), 0, 60);
		graphics.setColor(Color.BLACK);
	}
}
