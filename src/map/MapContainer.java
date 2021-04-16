package map;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import ui.Editor;

public class MapContainer extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private Dimension containerSize = new Dimension(640, 640);
	
	private Map map = new Map();
	
	public MapContainer() {
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
		
		map.renderMap(graphics);
		
		if (Editor.getCurrentEditor().isDebugModeOn())
			map.renderDebug(graphics);
	}
	
	public Map getMap() {
		return map;
	}
	
	private class MouseHandler extends MouseAdapter {
		private Point offset = new Point();
		
		@Override
		public void mousePressed(MouseEvent e) {
			if (SwingUtilities.isLeftMouseButton(e)) {
				Editor.getCurrentEditor().addTile(e.getX(), e.getY());
				repaint();
			}
			
			else if (SwingUtilities.isMiddleMouseButton(e)) {
				Point location = map.mapLocation;
				
				offset.x = e.getX() - location.x;
				offset.y = e.getY() - location.y;
				
				repaint();
			}
			
			else if (SwingUtilities.isRightMouseButton(e)) {
				Editor.getCurrentEditor().removeTile(e.getX(), e.getY());
				repaint();
			}
		}
		
		@Override
		public void mouseDragged(MouseEvent e) {
			if (SwingUtilities.isLeftMouseButton(e)) {
				Editor.getCurrentEditor().addTile(e.getX(), e.getY());
				repaint();
			}
			
			// user can only drag with the middle mouse button
			else if (SwingUtilities.isMiddleMouseButton(e)) {
				Point location = map.mapLocation;
				
				location.x = e.getX() - offset.x;
				location.y = e.getY() - offset.y;
				
				repaint();
			}
			
			else if (SwingUtilities.isRightMouseButton(e)) {
				Editor.getCurrentEditor().removeTile(e.getX(), e.getY());
				repaint();
			}
		}
	}
}
