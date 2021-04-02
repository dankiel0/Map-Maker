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
				makeMenuItem("Edit Collisions"),
				null,
				makeMenuItem("Edit Background"),
				makeMenuItem("Edit Foreground")));
		
		navBar.add(makeMenu("View",
				makeMenuItem("Explore Map"),
				makeMenuItem("Display Full Map")));
		
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
			new Thread(new Editor()).start();
			break;
		case "Open Existing Map":
			FileUtil.getInstance().openMap();
			break;
		case "Open Tileset":
			FileUtil.getInstance().openTileset();
			break;
		case "Save":
			FileUtil.getInstance().save();
			break;
		case "Save As...":
			FileUtil.getInstance().saveAs();
			break;
		case "Exit":
			Editor.getCurrentEditor().dispose();
			break;
		case "Edit Collisions":
			System.out.println("Edit Collisions");
			break;
		case "Set Tiles":
			System.out.println("Set Tiles");
			break;
		case "Edit Background":
			System.out.println("Edit Background");
			break;
		case "Edit Foreground":
			System.out.println("Edit Foreground");
			break;
		case "Explore Map":
			System.out.println("Explore Map");
			break;
		case "Display Full Map":
			System.out.println("Display Full Map");
			break;
		case "About Map Maker":
			Help.getInstance().display();
			break;
		default:
			break;
		}
	}
}
