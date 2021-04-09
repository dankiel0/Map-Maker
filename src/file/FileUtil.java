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
		
		if (fileChooser.getSelectedFile() == null)
			return;
		
	}
	
	public void openTileset() {
		fileChooser.setDialogTitle("Open Tileset");
		fileChooser.showOpenDialog(null);
		
		if (fileChooser.getSelectedFile() == null)
			return;
		
		Editor.setTileset(getFilePath());
		Editor.repaintTileset();
		Editor.writeToFile(getFilePath());
	}
	
	public void saveAs() {
		fileChooser.setDialogTitle("Save As...");
		fileChooser.showSaveDialog(null);
		
		if (fileChooser.getSelectedFile() == null)
			return;
		
		Editor.setFile(getFilePath());
		
		save();
	}
	
	public void save() {
		if (!Editor.fileExists()) {
			saveAs();
			return;
		}
		
		if (!Editor.fileHasUnsavedChanges())
			return;
	}
	
	private String getFilePath() {
		return fileChooser.getSelectedFile().getAbsolutePath();
	}
}
