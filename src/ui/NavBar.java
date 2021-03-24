package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import file.FileUtil;
import help.Help;

public class NavBar implements ActionListener {
	private JMenuBar navBar = new JMenuBar();
	
	public NavBar() {
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
	
	public JMenuBar getBar() {
		return navBar;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "Create New Map":
			new Editor();
			break;
		case "Open Existing Map":
			FileUtil.openFile();
			break;
		case "Open Tileset":
			Editor.getActiveEditor().getTilesetContainer().openNewTileset();
			break;
		case "Save":
			FileUtil.saveFile();
			break;
		case "Save As...":
			FileUtil.saveFile();
			break;
		case "Exit":
			Editor.getActiveEditor().dispose();
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
			Help.displayMessage();
			break;
		default:
			break;
		}
	}
}
