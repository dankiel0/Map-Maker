package ui;

import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import map.MapContainer;
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
	
//	private EditableFile file = new EditableFile();
	
	// creates editor without open map file.
	public Editor() {
		initUI();
		
		// "Untitled" is the default name for a new editor without an open map file.
		addFileNameToTitle("Untitled");
	}
	
	// creates editor with open map file.
	public Editor(String fileName) {
		this();
		addFileNameToTitle(fileName);
	}
	
	public static Editor getCurrentEditor() {
		return currentEditor;
	}
	
	public MapContainer getMapContainer() {
		return mapContainer;
	}
	
	public TilesetContainer getTilesetContainer() {
		return tilesetContainer;
	}
	
//	public EditableFile getFile() {
//		return file;
//	}
	
	// exits the editor.
	public void dispose() {
		frame.dispose();
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
	
	// adds the open file to the frame title.
	public void addFileNameToTitle(String fileName) {
		frame.setTitle(title + fileName);
	}
	
	static class Plot {
		String cropType;
		
		public Plot(String cropType) {
			this.cropType = cropType;
		}
		
		public String getCropType() {
			return cropType;
		}
	}
	
	// sets the current editor to the one that's active.
	@Override
	public void windowGainedFocus(WindowEvent e) {
		currentEditor = this;
	}
	
	public static Object[] options = {"Save", "Don't Save", "Cancel"};
	// show "are you sure???" screen, if work is not saved.
	@Override
	public void windowClosing(WindowEvent e) {
//		if (file.hasUnsavedChanges()) {
//			int result =
//					JOptionPane.showOptionDialog(null,
//					"Do you want to save changes to " + file.getFilePath(),
//					"Map Maker",
//					JOptionPane.YES_NO_CANCEL_OPTION,
//					JOptionPane.WARNING_MESSAGE,
//					null,
//					options,
//					null);
//			
//			if (result == JOptionPane.YES_OPTION)
//				file.save();
//			
//			else if(result == JOptionPane.NO_OPTION)
//				frame.dispose();
//		}
//		
//		else frame.dispose();
		frame.dispose();
	}
	
	// edge case: when the last editor is closed, the program must end.
	// otherwise the program will not terminate.
	@Override
	public void windowClosed(WindowEvent e) {
//		file.close();
		
		// if there is no current active editor, end the program.
		if (!Editor.getCurrentEditor().frame.isActive())
			System.exit(0);
	}
}






