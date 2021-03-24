package file;

import javax.swing.JFileChooser;

public class FileUtil {
	private static JFileChooser fileChooser;
	
	public static void init() {
		if(fileChooser == null)
			fileChooser = new JFileChooser();
	}
	
	public static void openFile() {
		fileChooser.showOpenDialog(null);
		
	}
	
	public static void saveFile() {
		fileChooser.showSaveDialog(null);
		
	}
	
	public static String getFilePath() {
		return fileChooser.getSelectedFile().getAbsolutePath();
	}
}
