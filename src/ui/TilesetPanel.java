package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JPanel;

import elements.Tileset;
import resource_loaders.ImageLoader;

public class TilesetPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private Tileset tileset = new Tileset(ImageLoader.loadFromDrive("C:\\Users\\khrap\\Desktop\\items.png"));
	
	public TilesetPanel() {
		super.setPreferredSize(new Dimension(800, 800));
		super.setBackground(Color.WHITE);
		
		super.add(new JButton("add"));
		super.add(new JButton("remove"));
	}
	
	@Override
	public void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		
		tileset.render(graphics, 0, 0);
	}
}
