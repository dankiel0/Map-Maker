package src;

import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import ui.MapPanel;
import ui.NavBar;
import ui.Ptileset;

// the start of the application.
public class MapMaker {
	public static void main(String[] args) {
		new MapMaker().start();
	}
	
	private void start() {
		this.initLookAndFeel();
		this.initUI();
	}
	
	private void initUI() {
		JFrame frame = new JFrame();
		
		frame.setLayout(new FlowLayout());
		
		frame.setJMenuBar(new NavBar().getInstance());
		
		frame.getContentPane().add(new MapPanel());
		frame.getContentPane().add(new Ptileset());
		
		frame.setTitle("Map Maker");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	private void initLookAndFeel() {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
	}
}
