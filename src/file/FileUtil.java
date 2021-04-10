package file;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFileChooser;

import tile.Tile;
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
		
		Scanner in = null;
		
		try {
			in = new Scanner(new FileReader(getFilePath()));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		Editor.setTileset(in.nextLine());
		Editor.setMapX(in.nextInt());
		Editor.setMapY(in.nextInt());
		
		int width = in.nextInt();
		int height = in.nextInt();
		in.nextLine();
		in.nextLine();
		
		ArrayList<Tile> tempBackground = new ArrayList<Tile>();
		
		a: for (int j = 0; j < height * 32; j += 32) {
			for (int i = 0; i < width * 32; i += 32) {
				String stuff = in.nextLine();
				if (!stuff.equals("FOREGROUND")) {
					tempBackground.add(new Tile(i, j, Integer.parseInt(stuff)));
					System.out.println(stuff);
				} else {
					break a;
				}
			}
		}
		
		ArrayList<Tile> tempForeground = new ArrayList<Tile>();
		
		in.nextLine();
		
		b: for (int j = 0; j < height * 32; j += 32) {
			for (int i = 0; i < width * 32; i += 32) {
				String stuff = in.nextLine();
				if (!stuff.equals("COLLISIONS")) {
					tempForeground.add(new Tile(i, j, Integer.parseInt(stuff)));
					System.out.println(stuff);
				} else {
					break b;
				}
			}
		}
		
		for (int i = 0; i < tempBackground.size(); i++) {
			if (in.hasNext()) {
				tempBackground.get(i).setSolid(in.nextInt() == 1 ? true : false);
			}
		}
		
		in.close();
		
		Editor.setBackground(tempBackground);
		Editor.setForeground(tempForeground);
		
		Editor.repaintMap();
		Editor.repaintTileset();
		
		Editor.setFile(getFilePath());
	}
	
	public void openTileset() {
		fileChooser.setDialogTitle("Open Tileset");
		fileChooser.showOpenDialog(null);
		
		if (fileChooser.getSelectedFile() == null)
			return;
		
		Editor.setTileset(getFilePath());
		Editor.repaintTileset();
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
