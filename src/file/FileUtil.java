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
		
		Editor.getCurrentEditor().getTilesetContainer().setTileset(ImageLoader.loadFromDrive(getFilePath()));
	}
	
	public void saveAs() {
		if (Editor.getCurrentEditor().getMapFile().isSaved())
			return;
		
		fileChooser.setDialogTitle("Save As...");
		fileChooser.showSaveDialog(null);
		
		if (fileChooser.getSelectedFile() == null)
			return;
		
		Editor.getCurrentEditor().getMapFile().setFile(getFilePath());
	}
	
	public void save() {
		if (!Editor.getCurrentEditor().getMapFile().exists()) {
			saveAs();
			return;
		}
		
		if (Editor.getCurrentEditor().getMapFile().isSaved())
			return;
		
		fileChooser.setDialogTitle("Save");
		fileChooser.showSaveDialog(null);
	}
	
	private String getFilePath() {
		return fileChooser.getSelectedFile().getAbsolutePath();
	}
}
