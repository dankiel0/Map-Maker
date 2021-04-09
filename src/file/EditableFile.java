package file;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

import tile.Tile;
import ui.Editor;

public class EditableFile {
	private boolean hasUnsavedChanges;
	
	private String filePath = "Untitled";
	
	private PrintWriter writer;
	
	private String fileContents = "";
	
	public void setFile(String path) {
		filePath = path;
		
		try {
			writer = new PrintWriter(path);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		Editor.addFileNameToTitle(path);
	}
	
	public boolean hasUnsavedChanges() {
		return hasUnsavedChanges;
	}
	
	public boolean exists() {
		return writer != null;
	}
	
	public String getFilePath() {
		return filePath;
	}
	
	public void write(String str) {
		fileContents += str + "\n";
		
		hasUnsavedChanges = true;
	}
	
	public void finalSave() {
		if (!hasUnsavedChanges)
			return;
		
		writer.write(Editor.getTilesetPath() + "\n");
		writer.write(Editor.getMapX() + "\n");
		writer.write(Editor.getMapY() + "\n");
		writer.write(Editor.getMapWidth() + "\n");
		writer.write(Editor.getMapHeight() + "\n");
		
		writer.write("BACKGROUND\n");
		
		for (Tile tile : Editor.getBackground()) {
			writer.write(tile.getIndex() + "\n");
		}
		
		writer.write("FOREGROUND\n");
		
		// put tiles in order
		
		for (Tile tile : Editor.getForeground()) {
			writer.write(tile.getIndex() + "\n");
			writer.write(".\n");
		}
		
		writer.write("COLLISIONS\n");
		
		for (Tile tile : Editor.getBackground()) {
			writer.write((tile.isSolid() ? 1 : 0) + "\n");
		}
		
		fileContents = "";
		
		hasUnsavedChanges = false;
	}
	
	public void close() {
		if (writer != null)
			writer.close();
	}
}
