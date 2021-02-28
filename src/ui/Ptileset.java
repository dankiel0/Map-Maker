package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import elements.Tileset;
import resource_loaders.ImageLoader;

public class Ptileset extends JPanel {
	private static final long serialVersionUID;
	
	private static final int w, h;
	
	private Tileset tileset;
	
	private MouseListener ml;
	
	static {
		serialVersionUID = 1L;
		w = h = 640;
	}
	
	{
		this.ml = new MouseListener();
		this.tileset = new Tileset(ImageLoader.loadFromDrive("C:\\Users\\khrap\\Desktop\\items.png"));
	}
	
	public Ptileset() {
		super.setBackground(Color.WHITE);
		
		super.setPreferredSize(new Dimension(w, h));
		
		super.addMouseListener(ml);
		super.addMouseMotionListener(ml);
	}
	
	@Override
	public void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		tileset.render(graphics, 0, 0);
	}
	
	public static int getW() {
		return Ptileset.w;
	}
	
	public static int getH() {
		return Ptileset.h;
	}
	
	private class MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent event) {
			tileset.setSelectedTileIndex(event.getX(), event.getY());
			repaint();
		}
	}
}
