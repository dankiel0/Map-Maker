package ui;

import java.awt.event.ActionEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class NavBar {
	private JMenuBar navBar;
	
	public NavBar() {
		navBar = new JMenuBar();
		
		JMenu menu;
		JMenuItem menuItem;
		
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		menu = new JMenu("File");
		navBar.add(menu);
		
		menuItem = new JMenuItem("Create New Project");
		menu.add(menuItem);
		
		menuItem = new JMenuItem("Open Existing Project");
		menu.add(menuItem);
		
		menu.addSeparator();
		
		menuItem = new JMenuItem("Save");
		menu.add(menuItem);
		
		menuItem = new JMenuItem("Save As...");
		menu.add(menuItem);
		
		menu.addSeparator();
		
		menuItem = new JMenuItem("Exit");
		menuItem.addActionListener((ActionEvent e) -> System.exit(0));
		menu.add(menuItem);
		
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		menu = new JMenu("Edit");
		navBar.add(menu);
		
		menuItem = new JMenuItem("Undo");
		menu.add(menuItem);
		
		menuItem = new JMenuItem("Redo");
		menu.add(menuItem);
		
		menu.addSeparator();
		
		menuItem = new JMenuItem("Set Solids");
		menu.add(menuItem);
		
		menuItem = new JMenuItem("Set Tiles");
		menu.add(menuItem);
		
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		menu = new JMenu("Test");
		navBar.add(menu);
		
		menuItem = new JMenuItem("Explore Map");
		menu.add(menuItem);
		
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		menu = new JMenu("Help");
		navBar.add(menu);
		
		menuItem = new JMenuItem("About Map Maker");
		menu.add(menuItem);
	}
	
	public JMenuBar getInstance() {
		return this.navBar;
	}
}
