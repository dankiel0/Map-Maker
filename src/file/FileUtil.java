package file;

import javax.swing.JFileChooser;

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
	
	public void openTileset() {
		fileChooser.setDialogTitle("Open Tileset");
		fileChooser.showOpenDialog(null);
		
		if (fileChooser.getSelectedFile() == null)
			return;
		
		Editor.getCurrentEditor().setTileset(fileChooser.getSelectedFile().getAbsolutePath());
	}
}
