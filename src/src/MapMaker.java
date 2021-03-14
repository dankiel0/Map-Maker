package src;

import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import ui.MapContainer;
import ui.NavBar;
import ui.TilesetContainer;

public class MapMaker {
	public static void main(String[] args) {
		new MapMaker().start();
	}
	
	private void start() {
		initLookAndFeel();
		initUI();
	}
	
	private void initLookAndFeel() {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
	}
	
	private void initUI() {
		JFrame frame = new JFrame();
		frame.setLayout(new FlowLayout());
		
		frame.setJMenuBar(NavBar.getInstance());
		
		frame.add(MapContainer.getInstance());
		frame.add(TilesetContainer.getInstance());
		
		frame.setTitle("Map Maker");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
