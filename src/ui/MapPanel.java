package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class MapPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	public MapPanel() {
		super.setPreferredSize(new Dimension(800, 800));
		super.setBackground(Color.WHITE);
	}
	
	@Override
	public void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
	}
}
