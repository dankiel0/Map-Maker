package file;

import javax.swing.JFileChooser;

import resource_loaders.ImageLoader;
import ui.Editor;

public class FileUtil {
	private static JFileChooser fileChooser = new JFileChooser();
	
	public static void openMap() {
		fileChooser.setDialogTitle("Open Existing Map");
		fileChooser.showOpenDialog(null);
	}
	
	public static void openTileset() {
		fileChooser.setDialogTitle("Open Tileset");
		fileChooser.showOpenDialog(null);
		
		Editor.getCurrentEditor().getTilesetContainer().setTileset(ImageLoader.loadFromDrive(getFilePath()));
	}
	
	public static void saveAs() {
		fileChooser.setDialogTitle("Save As...");
		fileChooser.showSaveDialog(null);
	}
	
	public static void save() {
		fileChooser.setDialogTitle("Save");
		fileChooser.showSaveDialog(null);
	}
	
	private static String getFilePath() {
		return fileChooser.getSelectedFile().getAbsolutePath();
	}
}
