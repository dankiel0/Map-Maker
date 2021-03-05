package ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import elements.Tileset;
import resource_loaders.ImageLoader;

public class Ptileset extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private static Dimension preferredSize = new Dimension(640, 640);
	private MouseHandler mouseHandler = new MouseHandler();
	
	private Tileset tileset = new Tileset(ImageLoader.loadFromDrive("C:\\Users\\khrap\\Desktop\\items.png"));
	
	public Ptileset() {
		super.addMouseListener(this.mouseHandler);
		super.addMouseMotionListener(this.mouseHandler);
	}
	
	@Override
	public Dimension getPreferredSize() {
		return Ptileset.preferredSize;
	}
	
	@Override
	public Color getBackground() {
		return Color.WHITE;
	}
	
	@Override
	public void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		this.tileset.render(graphics, 0, 0);
	}
	
	public static int getW() {
		return Ptileset.preferredSize.width;
	}
	
	public static int getH() {
		return Ptileset.preferredSize.height;
	}
	
	private class MouseHandler extends MouseAdapter {
		private Point offset;
		
		@Override
		public void mouseClicked(MouseEvent e) {
			if (SwingUtilities.isMiddleMouseButton(e)) {
				offset = e.getPoint();
				Ptileset.this.tileset.setSelectedTileIndex(e.getX(), e.getY());
				Ptileset.this.repaint();
			}
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
			if (SwingUtilities.isMiddleMouseButton(e)) {
				int x = e.getPoint().x - offset.x;
				int y = e.getPoint().y - offset.y;
				
				Point location = Ptileset.this.tileset.getTilesetLocation();
				
				location.x += x;
				location.y += y;
				
				Ptileset.this.tileset.setTilesetLocation(location);
			}
			
			System.out.println("press");
		}
		
		@Override
		public void mouseDragged(MouseEvent e) {
			if (SwingUtilities.isMiddleMouseButton(e)) {
//				tileset.updatePosition(e.getX(), e.getY(), mousePressedX, mousePressedY);
				Ptileset.this.repaint();
			}
		}
	}
}
