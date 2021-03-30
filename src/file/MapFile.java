package file;

public class MapFile {
	private String filePath;
	
	private boolean isSaved;
	
	public MapFile(String path) {
		filePath = path;
		
	}
	
	public boolean isSaved() {
		return isSaved;
	}
}
