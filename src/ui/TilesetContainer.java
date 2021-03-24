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
import file.FileUtil;
import resource_loaders.ImageLoader;

public class TilesetContainer extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private Dimension containerSize = new Dimension(640, 640);
	
	Tileset tileset;
	
	public TilesetContainer() {
		tileset = new Tileset();
		
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
	
	// renders tileset to panel
	@Override
	public void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		
		tileset.render(graphics);
	}
	
//	public void openNewTileset() {
//		FileUtil.openFile();
//		tileset.setTileset(ImageLoader.loadFromDrive(FileUtil.getFilePath()));
//		repaint();
//	}
	
	// basically handles mouse events, mainly just the dragging and dropping feature
	private class MouseHandler extends MouseAdapter {
		private Point offset = new Point();
		
		@Override
		public void mousePressed(MouseEvent e) {
			// when the mouse is pressed, then a tile must've been selected
			if (SwingUtilities.isLeftMouseButton(e)) {
				tileset.setSelectedTileIndex(e.getX(), e.getY());
				repaint();
			}
			
			// user can only drag with the middle mouse button
			else if (SwingUtilities.isMiddleMouseButton(e)) {
				Point location = tileset.getTilesetLocation();
				
				offset.x = e.getX() - location.x;
				offset.y = e.getY() - location.y;
				
				repaint();
			}
		}
		
		@Override
		public void mouseDragged(MouseEvent e) {
			// a dragging-selection feature
			if (SwingUtilities.isLeftMouseButton(e)) {
				tileset.setSelectedTileIndex(e.getX(), e.getY());
				repaint();
			}
			
			// user can only drag with the middle mouse button
			else if (SwingUtilities.isMiddleMouseButton(e)) {
				Point location = tileset.getTilesetLocation();
				
				location.x = e.getX() - offset.x;
				location.y = e.getY() - offset.y;
				
				repaint();
			}
		}
	}
}
