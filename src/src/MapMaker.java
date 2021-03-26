package src;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import ui.Editor;

public class MapMaker {
	public static void main(String[] args) {
		initLookAndFeel();
		
		new Editor();
	}
	
	private static void initLookAndFeel() {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
	}
}

// set collisions
// set tiles
// >> edit background
// >> edit foreground
