package ui;

import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import map.Map.State;
import map.MapContainer;
import tile.Tile;
import tileset.TilesetContainer;

// class Editor represents a workspace for developing tile based maps.
// WindowAdapter is needed for checking window events.
public class Editor extends WindowAdapter {
	private static Editor editor = new Editor();
	
	private static JFrame frame;
	
	private MapContainer mapContainer;
	private TilesetContainer tilesetContainer;
	
	private boolean isDebugModeOn;
	
	// creates editor without open map file.
	private Editor() {
		initUI();
	}
	
	public static Editor getCurrentEditor() {
		return editor;
	}
	
	public String getMapData() {
		String data = "";
		
		data += tilesetContainer.getTileset().getPath() + "\n";
		
		data += mapContainer.getMap().getX() + "\n";
		data += mapContainer.getMap().getY() + "\n";
		
		data += mapContainer.getMap().getWidth() + "\n";
		data += mapContainer.getMap().getHeight() + "\n";
		
		ArrayList<Tile> temp = new ArrayList<Tile>();
		
		for(int i = mapContainer.getMap().getSmallestY(); i < mapContainer.getMap().getBiggestY(); i += 32)
			for(int j = mapContainer.getMap().getSmallestX(); j < mapContainer.getMap().getBiggestX(); j += 32) {
				a: for(Tile tile : mapContainer.getMap().getTiles()) {
					if(tile.getX() == j && tile.getY() == i) {
						temp.add(tile);
						break a;
					}
				}
			}
		
		for (Tile tile : temp) {
			data += tile.getBackgroundIndex() + "\n";
			data += tile.getForegroundIndex() + "\n";
			data += (tile.isSolid() ? 1 : 0) + "\n";
		}
		
		return data;
	}
	
	public void setTiles(ArrayList<Tile> tiles) {
		mapContainer.getMap().setTiles(tiles);
		mapContainer.repaint();
	}
	
	public void setState(State state) {
		mapContainer.getMap().setState(state);
		mapContainer.repaint();
	}
	
	public void addTile(int mouseX, int mouseY) {
		mapContainer.getMap().add(getIndex(), mouseX, mouseY);
	}
	
	public void removeTile(int mouseX, int mouseY) {
		mapContainer.getMap().remove(mouseX, mouseY);
	}
	
	// tileset stuff.
	public void setTileset(String path) {
		tilesetContainer.getTileset().setTileset(path);
		tilesetContainer.repaint();
	}
	
	public BufferedImage getTile(int index) {
		return tilesetContainer.getTileset().getTile(index);
	}
	
	public int getIndex() {
		return tilesetContainer.getTileset().getSelectedTileIndex();
	}
	
	// debug stuff.
	public void toggleDebugMode() {
		isDebugModeOn = !isDebugModeOn;
		mapContainer.repaint();
		tilesetContainer.repaint();
	}
	
	public boolean isDebugModeOn() {
		return isDebugModeOn;
	}
	
	public void dispose() {
		frame.dispose();
		System.exit(0);
	}
	
	private void initUI() {
		mapContainer = new MapContainer();
		tilesetContainer = new TilesetContainer();
		
		frame = new JFrame("Map Maker");
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
	
	// show "are you sure???" screen, if work is not saved.
	@Override
	public void windowClosing(WindowEvent e) {
		int result =
				JOptionPane.showOptionDialog(null,
				"Do you want to retrieve map data before closing?",
				"Map Maker",
				JOptionPane.YES_NO_CANCEL_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, null, null);
		
		if (result == JOptionPane.YES_OPTION) {
			System.out.println("Get Map Data");
			frame.dispose();
		} else if (result == JOptionPane.NO_OPTION) {
			dispose();
		}
	}
}
