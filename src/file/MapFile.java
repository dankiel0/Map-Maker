package file;

public class MapFile {
	private String filePath;
	
	private boolean isSaved;
	
	public void setFile(String path) {
		filePath = path;
	}
	
	public boolean isSaved() {
		return isSaved;
	}
	
	public boolean exists() {
		return filePath != null;
	}
}
