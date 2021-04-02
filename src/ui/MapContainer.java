package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import elements.Map;

public class MapContainer extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private Dimension containerSize = new Dimension(640, 640);
	
	private Map map;
	
	public MapContainer() {
		map = new Map();
		
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
		
		map.render(graphics);
	}
	
	private class MouseHandler extends MouseAdapter {
		@Override
		public void mousePressed(MouseEvent e) {
			if (SwingUtilities.isLeftMouseButton(e)) {
				
			}
			
			else if (SwingUtilities.isMiddleMouseButton(e)) {
				
			}
			
			else if (SwingUtilities.isRightMouseButton(e)) {
				
			}
		}
		
		@Override
		public void mouseDragged(MouseEvent e) {
			if (SwingUtilities.isLeftMouseButton(e)) {
				
			}
			
			else if (SwingUtilities.isMiddleMouseButton(e)) {
				
			}
			
			else if (SwingUtilities.isRightMouseButton(e)) {
				
			}
		}
	}
}
