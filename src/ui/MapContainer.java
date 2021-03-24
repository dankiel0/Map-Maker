package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import elements.Map;

public class MapContainer extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private Map map;
	
	public MapContainer() {
		map = new Map();
		
		super.setPreferredSize(new Dimension(640, 640));
		super.setBackground(Color.WHITE);
	}
	
	@Override
	public void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
	}
}
