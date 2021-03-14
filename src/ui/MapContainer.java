package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class MapContainer extends JPanel {
	private static final long serialVersionUID = 1L;
	private static MapContainer mapContainerInstance = new MapContainer();
	
	private MapContainer() {
		super.setPreferredSize(new Dimension(640, 640));
		super.setBackground(Color.WHITE);
	}
	
	@Override
	public void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
	}
	
	public static MapContainer getInstance() {
		return mapContainerInstance;
	}
}
