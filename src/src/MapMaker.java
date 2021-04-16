package src;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import file.FileUtil;
import help.Help;
import ui.Editor;

public class MapMaker {
	public static void main(String[] args) {
		initLookAndFeel();
		initAll();
	}
	
	private static void initAll() {
		Editor.getCurrentEditor();
		FileUtil.getInstance();
		Help.getInstance();
	}
	
	private static void initLookAndFeel() {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
	}
}
