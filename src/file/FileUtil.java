package file;

import javax.swing.JFileChooser;

import resource_loaders.ImageLoader;
import ui.Editor;

public class FileUtil {
	private static FileUtil fileUtil;
	
	private JFileChooser fileChooser = new JFileChooser();
	
	private FileUtil() {}
	
	public static FileUtil getInstance() {
		if (fileUtil == null)
			fileUtil = new FileUtil();
		
		return fileUtil;
	}
	
	public void openMap() {
		fileChooser.setDialogTitle("Open Existing Map");
		fileChooser.showOpenDialog(null);
	}
	
	public void openTileset() {
		fileChooser.setDialogTitle("Open Tileset");
		fileChooser.showOpenDialog(null);
		
		Editor.getCurrentEditor().getTilesetContainer().setTileset(ImageLoader.loadFromDrive(getFilePath()));
	}
	
	public void saveAs() {
		fileChooser.setDialogTitle("Save As...");
		fileChooser.showSaveDialog(null);
	}
	
	public void save() {
		fileChooser.setDialogTitle("Save");
		fileChooser.showSaveDialog(null);
	}
	
	private String getFilePath() {
		return fileChooser.getSelectedFile().getAbsolutePath();
	}
}
