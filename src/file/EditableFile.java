package file;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

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
	}
	
	public void save() {
		if (!hasUnsavedChanges)
			return;
		
		hasUnsavedChanges = false;
	}
	
	public void close() {
		if (writer != null)
			writer.close();
	}
}
