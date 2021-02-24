package src;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import ui.NavBar;
import ui.TilesetPanel;

// the start of the application.
public class MapMaker {
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
		MapMaker.start();
	}
	
	private static void start() {
		JFrame frame = new JFrame();
		
		frame.setJMenuBar(new NavBar().getInstance());
		
//		frame.getContentPane().add(new MapPanel());
		frame.getContentPane().add(new TilesetPanel());
		
		frame.setTitle("Map Maker");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
