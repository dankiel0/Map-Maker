package src;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import file.FileUtil;
import ui.Editor;

public class MapMaker {
	public static void main(String[] args) {
		initLookAndFeel();
		FileUtil.init();
		
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
