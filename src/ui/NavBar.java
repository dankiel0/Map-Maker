package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import elements.Tileset;
import file.FileUtil;
import resource_loaders.ImageLoader;

public class NavBar implements ActionListener {
	private static NavBar navBarInstance = new NavBar();
	
	private JMenuBar navBar = new JMenuBar();
	
	private NavBar() {
		navBar.add(makeMenu("File",
				makeMenuItem("Create New Map"),
				makeMenuItem("Open Existing Map"),
				null,
				makeMenuItem("Open Tileset"),
				null,
				makeMenuItem("Save"),
				makeMenuItem("Save As..."),
				null,
				makeMenuItem("Exit")));
		
		navBar.add(makeMenu("Edit",
				makeMenuItem("Undo"),
				makeMenuItem("Redo"),
				null,
				makeMenuItem("Set Solids"),
				makeMenuItem("Set Tiles"),
				null,
				makeMenuItem("Reset Map Position"),
				makeMenuItem("Reset Tileset Position")));
		
		navBar.add(makeMenu("View",
				makeMenuItem("Explore Map"),
				null,
				makeMenuItem("Display Full Map"),
				makeMenuItem("Display Full Tileset")));
		
		navBar.add(makeMenu("Help",
				makeMenuItem("About Map Maker")));
	}
	
	private JMenu makeMenu(String name, JMenuItem... items) {
		JMenu menu = new JMenu(name);
		
		for (JMenuItem item : items)
			if (item != null)
				menu.add(item);
			else menu.addSeparator();
		
		return menu;
	}
	
	private JMenuItem makeMenuItem(String name) {
		JMenuItem item = new JMenuItem(name);
		
		item.addActionListener(this);
		
		return item;
	}
	
	public static JMenuBar getInstance() {
		return NavBar.navBarInstance.navBar;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "Create New Map":
			
			break;
		case "Open Existing Map":
			FileUtil.openFile();
			break;
		case "Open Tileset":
			FileUtil.openFile();
			Tileset.getInstance().setTileset(ImageLoader.loadFromDrive(FileUtil.getFilePath()));
			TilesetContainer.getInstance().repaint();
			break;
		case "Save":
			FileUtil.saveFile();
			break;
		case "Save As...":
			FileUtil.saveFile();
			break;
		case "Exit":
			System.exit(0);
			break;
		case "Undo":
			
			break;
		case "Redo":
			
			break;
		case "Set Solids":
			
			break;
		case "Set Tiles":
			
			break;
		case "Reset Map Position":
			
			break;
		case "Reset Tileset Position":
			
			break;
		case "Explore Map":
			
			break;
		case "Display Full Map":
			
			break;
		case "Display Full Tileset":
			
			break;
		case "About Map Maker":
			
			break;
		default:
			break;
		}
	}
}
