package file;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class MapFile {
	private boolean isSaved;
	
	private PrintWriter writer;
	
	public void setFile(String path) {
		try {
			writer = new PrintWriter(path);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public boolean isSaved() {
		return isSaved;
	}
	
	public boolean exists() {
		return writer != null;
	}
}
