package ui;

import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class Editor extends WindowAdapter {
	private static Editor activeEditor;
	
	private JFrame frame;
	private String title;
	
	private NavBar navBar;
	private MapContainer mapContainer;
	private TilesetContainer tilesetContainer;
	
	public Editor() {
		initUI();
		addFileNameToTitle("Untitled");
	}
	
	public Editor(String fileName) {
		this();
		addFileNameToTitle(fileName);
	}
	
	public static Editor getActiveEditor() {
		return activeEditor;
	}
	
	public MapContainer getMapContainer() {
		return mapContainer;
	}
	
	public TilesetContainer getTilesetContainer() {
		return tilesetContainer;
	}
	
	public void dispose() {
		frame.dispose();
	}
	
	private void initUI() {
		navBar = new NavBar();
		mapContainer = new MapContainer();
		tilesetContainer = new TilesetContainer();
		
		frame = new JFrame();
		frame.setLayout(new FlowLayout());
		
		frame.setJMenuBar(navBar.getBar());
		
		frame.add(mapContainer);
		frame.add(tilesetContainer);
		
		frame.addWindowFocusListener(this);
		frame.addWindowListener(this);
		
		title = "Map Maker - ";
		
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setResizable(false);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	public void addFileNameToTitle(String fileName) {
		frame.setTitle(title + fileName);
	}
	
	@Override
	public void windowGainedFocus(WindowEvent e) {
		activeEditor = Editor.this;
	}
	
	@Override
	public void windowClosing(WindowEvent e) {
		// show "are you sure???" screen, if work is not saved
	}
	
	@Override
	public void windowClosed(WindowEvent e) {
		if (!activeEditor.frame.isActive())
			System.exit(0);
	}
}
