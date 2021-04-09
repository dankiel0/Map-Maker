package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import file.FileUtil;
import help.Help;
import map.Map;

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
			new Editor();
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
			Editor.dispose();
			break;
		case "Edit Collisions":
			Editor.setMapState(Map.State.COLLISIONS);
			Editor.repaintMap();
			break;
		case "Edit Background":
			Editor.setMapState(Map.State.BACKGROUND);
			Editor.repaintMap();
			break;
		case "Edit Foreground":
			Editor.setMapState(Map.State.FOREGROUND);
			Editor.repaintMap();
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
