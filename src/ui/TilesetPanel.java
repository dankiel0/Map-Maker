package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import elements.Tileset;
import resource_loaders.ImageLoader;

public class TilesetPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private Tileset tileset = new Tileset(ImageLoader.loadFromDrive("C:\\Users\\khrap\\Desktop\\items.png"));
	
	private TilesetML ml = new TilesetML();
	
	public TilesetPanel() {
		super.setPreferredSize(new Dimension(640, 640));
		super.setBackground(Color.WHITE);
		
		super.addMouseListener(this.ml);
		super.addMouseMotionListener(this.ml);
	}
	
	@Override
	public void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		
		tileset.render(graphics, 0, 0);
	}
	
	private class TilesetML extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent event) {
			
		}
	}
}
