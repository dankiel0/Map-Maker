package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class NavBar implements ActionListener {
	private JMenuBar navBar = new JMenuBar();
	
	public NavBar() {
		JMenu menu;
		JMenuItem menuItem;
		
		////////////////////////////////////////////////// creates file menu
		menu = new JMenu("File");
		navBar.add(menu);
		
		menuItem = new JMenuItem("Create New Map");
		menuItem.addActionListener(this);
		menu.add(menuItem);
		
		menuItem = new JMenuItem("Open Existing Map");
		menuItem.addActionListener(this);
		menu.add(menuItem);
		
		menu.addSeparator();
		
		menuItem = new JMenuItem("Open Tileset");
		menuItem.addActionListener(this);
		menu.add(menuItem);
		
		menu.addSeparator();
		
		menuItem = new JMenuItem("Save");
		menuItem.addActionListener(this);
		menu.add(menuItem);
		
		menuItem = new JMenuItem("Save As...");
		menuItem.addActionListener(this);
		menu.add(menuItem);
		
		menu.addSeparator();
		
		menuItem = new JMenuItem("Exit");
		menuItem.addActionListener(this);
		menu.add(menuItem);
		
		////////////////////////////////////////////////// creates edit menu
		menu = new JMenu("Edit");
		navBar.add(menu);
		
		menuItem = new JMenuItem("Undo");
		menuItem.addActionListener(this);
		menu.add(menuItem);
		
		menuItem = new JMenuItem("Redo");
		menuItem.addActionListener(this);
		menu.add(menuItem);
		
		menu.addSeparator();
		
		menuItem = new JMenuItem("Set Solids");
		menuItem.addActionListener(this);
		menu.add(menuItem);
		
		menuItem = new JMenuItem("Set Tiles");
		menuItem.addActionListener(this);
		menu.add(menuItem);
		
		menu.addSeparator();
		
		menuItem = new JMenuItem("Reset Map Position");
		menuItem.addActionListener(this);
		menu.add(menuItem);
		
		menuItem = new JMenuItem("Reset Tileset Position");
		menuItem.addActionListener(this);
		menu.add(menuItem);
		
		////////////////////////////////////////////////// creates test menu
		menu = new JMenu("View");
		navBar.add(menu);
		
		menuItem = new JMenuItem("Explore Map");
		menuItem.addActionListener(this);
		menu.add(menuItem);
		
		menu.addSeparator();
		
		menuItem = new JMenuItem("Display Full Map");
		menuItem.addActionListener(this);
		menu.add(menuItem);
		
		menuItem = new JMenuItem("Display Full Tileset");
		menuItem.addActionListener(this);
		menu.add(menuItem);
		
		////////////////////////////////////////////////// creates help menu
		menu = new JMenu("Help");
		navBar.add(menu);
		
		menuItem = new JMenuItem("About Map Maker");
		menuItem.addActionListener(this);
		menu.add(menuItem);
	}
	
	public JMenuBar getInstance() {
		return this.navBar;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {
		case "Create New Map":
			break;
		case "Open Existing Map":
			break;
		case "Open Tileset":
			break;
		case "Save":
			break;
		case "Save As...":
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
