package ui;

import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import file.EditableFile;
import map.Map;
import map.Map.State;
import map.MapContainer;
import tile.Tile;
import tileset.TilesetContainer;

//import file.EditableFile;

// class Editor represents a workspace for developing tile based maps.
// WindowAdapter is needed for checking window events.
public class Editor extends WindowAdapter {
	private static Editor currentEditor;
	
	private JFrame frame;
	private String title = "Map Maker - ";
	
	private MapContainer mapContainer;
	private TilesetContainer tilesetContainer;
	
	private EditableFile file = new EditableFile();
	
	// creates editor without open map file.
	public Editor() {
		currentEditor = this;
		
		initUI();
		
		// "Untitled" is the default name for a new editor without an open map file.
		addFileNameToTitle("Untitled");
	}
	
	// creates editor with open map file.
	public Editor(String fileName) {
		this();
		addFileNameToTitle(fileName);
	}
	
	public static void setMapState(State state) {
		currentEditor.getMapContainer().getMap().setState(state);
	}
	
	public static void setTileset(String tilesetPath) {
		currentEditor.getTilesetContainer().getTileset().setTileset(tilesetPath);
	}
	
	public static int getSelectedTileIndex() {
		return currentEditor.getTilesetContainer().getTileset().getSelectedTileIndex();
	}
	
	public static void repaintMap() {
		currentEditor.getMapContainer().repaint();
	}
	
	public static void repaintTileset() {
		currentEditor.getTilesetContainer().repaint();
	}
	
	public MapContainer getMapContainer() {
		return mapContainer;
	}
	
	public TilesetContainer getTilesetContainer() {
		return tilesetContainer;
	}
	
	public static State getMapState() {
		return currentEditor.getMapContainer().getMap().getState();
	}
	
	public static BufferedImage getTile(int index) {
		return currentEditor.getTilesetContainer().getTileset().getTile(index);
	}
	
	public static void addTile(int mouseX, int mouseY) {
		if (Editor.getMapState() == Map.State.BACKGROUND) {
			currentEditor.getMapContainer().getMap().addBackgroundTile(getSelectedTileIndex(), mouseX, mouseY);
		}
		
		else if (Editor.getMapState() == Map.State.FOREGROUND) {
			currentEditor.getMapContainer().getMap().addForegroundTile(getSelectedTileIndex(), mouseX, mouseY);
		}
		
		else if (Editor.getMapState() == Map.State.COLLISIONS) {
			currentEditor.getMapContainer().getMap().addCollision(mouseX, mouseY);
		}
	}
	
	public static void highlightBackground() {
		currentEditor.getMapContainer().getMap().triggerBackground();
	}
	
	public static void highlightForeground() {
		currentEditor.getMapContainer().getMap().triggerForeground();
	}
	
	public static void removeTile(int mouseX, int mouseY) {
		if (Editor.getMapState() == Map.State.BACKGROUND) {
			currentEditor.getMapContainer().getMap().removeBackgroundTile(mouseX, mouseY);
		}
		
		else if (Editor.getMapState() == Map.State.FOREGROUND) {
			currentEditor.getMapContainer().getMap().removeForegroundTile(mouseX, mouseY);
		}
		
		else if (Editor.getMapState() == Map.State.COLLISIONS) {
			currentEditor.getMapContainer().getMap().removeCollision(mouseX, mouseY);
		}
	}
	
//	public EditableFile getFile() {
//		return file;
//	}
	
	// exits the editor.
	public static void dispose() {
		currentEditor.frame.dispose();
	}
	
	private void initUI() {
		mapContainer = new MapContainer();
		tilesetContainer = new TilesetContainer();
		
		frame = new JFrame();
		frame.setLayout(new FlowLayout());
		
		frame.setJMenuBar(new NavBar().getBar());
		
		frame.add(mapContainer);
		frame.add(tilesetContainer);
		
		// class Editors inherits from WindowAdapter.
		frame.addWindowFocusListener(this);
		frame.addWindowListener(this);
		
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.setResizable(false);
		frame.pack();
		frame.setLocationByPlatform(true);
		frame.setVisible(true);
	}
	
	public static void setFile(String path) {
		currentEditor.file.setFile(path);
	}
	
	public static boolean fileHasUnsavedChanges() {
		return currentEditor.file.hasUnsavedChanges();
	}
	
	public static boolean fileExists() {
		return currentEditor.file.exists();
	}
	
	public static String getTilesetPath() {
		return currentEditor.tilesetContainer.getTileset().getPath();
	}
	
	public static int getMapWidth() {
		return currentEditor.getMapContainer().getMap().getWidth();
	}
	
	public static void setBackground(ArrayList<Tile> tiles) {
		currentEditor.getMapContainer().getMap().setBackground(tiles);
	}
	
	public static void setForeground(ArrayList<Tile> tiles) {
		currentEditor.getMapContainer().getMap().setForeground(tiles);
	}
	
	public static void setMapX(int x) {
		currentEditor.getMapContainer().getMap().setMapX(x);
	}
	
	public static void setMapY(int y) {
		currentEditor.getMapContainer().getMap().setMapX(y);
	}
	
	public static int getMapHeight() {
		return currentEditor.getMapContainer().getMap().getHeight();
	}
	
	public static int getMapX() {
		return currentEditor.getMapContainer().getMap().getX();
	}
	
	public static int getMapY() {
		return currentEditor.getMapContainer().getMap().getY();
	}
	
	public static ArrayList<Tile> getBackground() {
		return currentEditor.getMapContainer().getMap().getBackground();
	}
	
	public static ArrayList<Tile> getForeground() {
		return currentEditor.getMapContainer().getMap().getForeground();
	}
	
	// adds the open file to the frame title.
	public static void addFileNameToTitle(String fileName) {
		currentEditor.frame.setTitle(currentEditor.title + fileName);
	}
	
	// sets the current editor to the one that's active.
	@Override
	public void windowGainedFocus(WindowEvent e) {
		currentEditor = this;
	}
	
	public static void setTrue() {
		currentEditor.file.setTrue();
	}
	
	public static int getSmallestX() {
		return currentEditor.getMapContainer().getMap().getSmallestX();
	}
	
	public static int getSmallestY() {
		return currentEditor.getMapContainer().getMap().getSmallestY();
	}
	
	public static int getBiggestX() {
		return currentEditor.getMapContainer().getMap().getBiggestX();
	}
	
	public static int getBiggestY() {
		return currentEditor.getMapContainer().getMap().getBiggestY();
	}
	
	public static Object[] options = {"Save", "Don't Save", "Cancel"};
	// show "are you sure???" screen, if work is not saved.
	@Override
	public void windowClosing(WindowEvent e) {
		if (file.hasUnsavedChanges()) {
			int result =
					JOptionPane.showOptionDialog(null,
					"Do you want to save changes to " + file.getFilePath(),
					"Map Maker",
					JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.WARNING_MESSAGE,
					null,
					options,
					null);
			
			if (result == JOptionPane.YES_OPTION)
				file.finalSave();
			
			else if(result == JOptionPane.NO_OPTION)
				frame.dispose();
		}
		
		else frame.dispose();
	}
	
	// edge case: when the last editor is closed, the program must end.
	// otherwise the program will not terminate.
	@Override
	public void windowClosed(WindowEvent e) {
		file.close();
		
		// if there is no current active editor, end the program.
		if (!currentEditor.frame.isActive())
			System.exit(0);
	}
}
