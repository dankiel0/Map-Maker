package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import file.FileUtil;
import help.Help;
import map.Map.State;

public class NavBar {
	private JMenuBar navBar = new JMenuBar();
	
	public NavBar() {
		navBar.add(makeMenu("File",
				makeMenuItem("Open Map", (ActionEvent e) -> {
					DataProcessor.getInstance().displayOpenMap();
				}),
				makeMenuItem("Retrieve Map Data", (ActionEvent e) -> {
					DataProcessor.getInstance().setSaveData(Editor.getCurrentEditor().getMapData());
					DataProcessor.getInstance().displaySaveMap();
				}),
				null,
				makeMenuItem("Open Tileset", (ActionEvent e) -> {
					FileUtil.getInstance().openTileset();
				}),
				null,
				makeMenuItem("Exit", (ActionEvent e) -> {
					Editor.getCurrentEditor().dispose();
				})));
		
		navBar.add(makeMenu("Edit",
				makeMenuItem("Edit Tiles", (ActionEvent e) -> {
					Editor.getCurrentEditor().setState(State.TILES);
				}),
				makeMenuItem("Edit Collisions", (ActionEvent e) -> {
					Editor.getCurrentEditor().setState(State.COLLISIONS);
				})));
		
		navBar.add(makeMenu("View",
				makeMenuItem("Display Full Map", (ActionEvent e) -> {
					System.out.println("Display Full Map");
				}),
				makeMenuItem("Explore Map", (ActionEvent e) -> {
					System.out.println("Explore Map");
				}),
				null,
				makeMenuItem("Show/Hide Debug", (ActionEvent e) -> {
					Editor.getCurrentEditor().toggleDebugMode();
				})));
		
		navBar.add(makeMenu("Help",
				makeMenuItem("About Map Maker", (ActionEvent e) -> {
					Help.getInstance().display();
				})));
	}
	
	private JMenu makeMenu(String name, JMenuItem... items) {
		JMenu menu = new JMenu(name);
		
		for (JMenuItem item : items)
			if (item != null)
				menu.add(item);
			else menu.addSeparator();
		
		return menu;
	}
	
	private JMenuItem makeMenuItem(String name, ActionListener actionListener) {
		JMenuItem item = new JMenuItem(name);
		
		item.addActionListener(actionListener);
		
		return item;
	}
	
	public JMenuBar getBar() {
		return navBar;
	}
}
