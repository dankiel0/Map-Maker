package file;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

import tile.Tile;
import ui.Editor;

public class EditableFile {
	private boolean hasUnsavedChanges;
	
	private String filePath = "Untitled";
	
	private PrintWriter writer;
	
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
	
	public void setTrue() {
		hasUnsavedChanges = true;
	}
	
	public boolean exists() {
		return writer != null;
	}
	
	public String getFilePath() {
		return filePath;
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
		
		ArrayList<Tile> temp1 = new ArrayList<Tile>();
		
		System.out.println(Editor.getMapX());
		System.out.println(Editor.getMapY());
		
		System.out.println(Editor.getMapWidth());
		System.out.println(Editor.getMapHeight());
		
		for(int i = Editor.getSmallestY(); i < Editor.getBiggestY(); i += 32)
			for(int j = Editor.getSmallestX(); j < Editor.getBiggestX(); j += 32) {
				a: for(Tile tile : Editor.getBackground()) {
					if(tile.getX() == j && tile.getY() == i) {
						System.out.println("loop:" + j + ", " + i);
						System.out.println("actual:" + tile.getX() + ", " + tile.getY());
						
						temp1.add(tile);
						break a;
					}
				}
			}
		
		System.out.println("REALREALREALREALREAL");
		
		for (Tile tile : Editor.getBackground()) {
			System.out.println(tile.getX() + ", " + tile.getY());
		}
		
		for(Tile tile : temp1)
			writer.write(String.valueOf(tile.getIndex()) + "\n");
		
		writer.write("FOREGROUND\n");
		
		ArrayList<Tile> temp2 = new ArrayList<Tile>();
		
		for(int i = Editor.getSmallestY(); i < Editor.getBiggestY(); i += 32)
			for(int j = Editor.getSmallestX(); j < Editor.getBiggestX(); j += 32) {
				a: for(Tile tile : Editor.getForeground())
					if(tile.getX() == j && tile.getY() == i) {
						temp2.add(tile);
						break a;
					}
			}
		
		for(Tile tile : temp2)
			writer.write(String.valueOf(tile.getIndex()) + "\n");
		
		for (Tile tile : Editor.getForeground()) {
			writer.write(tile.getIndex() + "\n");
			writer.write(".\n");
		}
		
		writer.write("COLLISIONS\n");
		
		for (Tile tile : Editor.getBackground()) {
			writer.write((tile.isSolid() ? 1 : 0) + "\n");
		}
		
		hasUnsavedChanges = false;
	}
	
	public void close() {
		if (writer != null)
			writer.close();
	}
}
