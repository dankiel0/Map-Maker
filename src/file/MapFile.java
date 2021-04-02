package file;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class MapFile {
	private boolean hasUnsavedChanges;
	
	private String fileName;
	
	private String filePath;
	
	private PrintWriter writer;
	
	public void setFile(String path) {
		try {
			writer = new PrintWriter(path);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public boolean hasUnsavedChanges() {
		return hasUnsavedChanges;
	}
	
	public boolean exists() {
		return writer != null;
	}
	
	public String getFileName() {
		return fileName;
	}
	
	public void save() {
		if (!hasUnsavedChanges)
			return;
		
		hasUnsavedChanges = false;
		
	}
	
	public void close() {
		writer.close();
	}
}
