package file;

import javax.swing.JFileChooser;

public class FileUtil {
	private static JFileChooser fileChooser = new JFileChooser();
	
	public static void openFile() {
		fileChooser.showOpenDialog(null);
		
	}
	
	public static void openMap() {
		fileChooser.setDialogTitle("Open Existing Map");
		fileChooser.showOpenDialog(null);
	}
	
	public static void openTileset() {
		fileChooser.setDialogTitle("Open Tileset");
		fileChooser.showOpenDialog(null);
	}
	
	public static void saveAs() {
		fileChooser.setDialogTitle("Save As...");
		fileChooser.showSaveDialog(null);
	}
	
	public static void save() {
		fileChooser.setDialogTitle("Save");
		fileChooser.showSaveDialog(null);
	}
	
	public static String getFilePath() {
		return fileChooser.getSelectedFile().getAbsolutePath();
	}
}
