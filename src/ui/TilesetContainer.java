package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import elements.Tileset;
import resource_loaders.ImageLoader;

public class TilesetContainer extends JPanel {
	private static final long serialVersionUID = 1L;
	private static TilesetContainer container = new TilesetContainer();
	
	private Dimension containerSize = new Dimension(640, 640);
	private Tileset tileset = new Tileset(ImageLoader.loadFromDrive("C:\\Users\\khrap\\Desktop\\items.png"));
	
	private TilesetContainer() {
		MouseHandler mouseHandler = new MouseHandler();
		
		super.addMouseListener(mouseHandler);
		super.addMouseMotionListener(mouseHandler);
	}
	
	@Override
	public Dimension getPreferredSize() {
		return containerSize;
	}
	
	@Override
	public Color getBackground() {
		return Color.WHITE;
	}
	
	@Override
	public void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		tileset.render(graphics);
	}
	
	public static TilesetContainer getInstance() {
		return container;
	}
	
	private class MouseHandler extends MouseAdapter {
		private Point offset = new Point();
		
		@Override
		public void mousePressed(MouseEvent e) {
			if (SwingUtilities.isLeftMouseButton(e)) {
				tileset.setSelectedTileIndex(e.getX(), e.getY());
				repaint();
			} else if (SwingUtilities.isMiddleMouseButton(e)) {
				Point location = tileset.getTilesetLocation();
				
				offset.x = e.getX() - location.x;
				offset.y = e.getY() - location.y;
				
				repaint();
			}
		}
		
		@Override
		public void mouseDragged(MouseEvent e) {
			if (SwingUtilities.isLeftMouseButton(e)) {
				tileset.setSelectedTileIndex(e.getX(), e.getY());
				repaint();
			} else if (SwingUtilities.isMiddleMouseButton(e)) {
				Point location = tileset.getTilesetLocation();
				
				location.x = e.getX() - offset.x;
				location.y = e.getY() - offset.y;
				
				repaint();
			}
		}
	}
}
