package ui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import tile.Tile;

public class DataProcessor {
	private JFrame openMap;
	private JFrame saveMap;
	
	private JTextArea textBox;
	private JTextArea textBox2;
	
	private static DataProcessor data = new DataProcessor();
	
	private DataProcessor() {
		textBox2 = new JTextArea();
		
		JScrollPane scroll1 = new JScrollPane(textBox2, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scroll1.setPreferredSize(new Dimension(320, 320));
		
		openMap = new JFrame("Map Maker");
		openMap.setLayout(new FlowLayout());
		
		openMap.getContentPane().add(scroll1);
		
		JButton ok = new JButton("OK");
		
		ok.addActionListener((ActionEvent e) -> {
			ArrayList<Tile> tiles = new ArrayList<Tile>();
			String text = textBox2.getText();
			
			BufferedReader bufReader = new BufferedReader(new StringReader(text));
			
			try {
				Editor.getCurrentEditor().setTileset(bufReader.readLine());
				bufReader.readLine();
				bufReader.readLine();
				
				int width = Integer.parseInt(bufReader.readLine());
				int height = Integer.parseInt(bufReader.readLine());
				
				for (int i = 0; i < height * 32; i += 32)
					for (int j = 0; j < width * 32; j += 32)
						tiles.add(new Tile(j, i));
				
				int counter = 0;
				
				while (true) {
					int bIndex = Integer.parseInt(bufReader.readLine());
					int fIndex = Integer.parseInt(bufReader.readLine());
					boolean solid = Integer.parseInt(bufReader.readLine()) == 1 ? true : false;
					
					tiles.get(counter).addBackground(bIndex);
					tiles.get(counter).addForeground(fIndex);
					tiles.get(counter).setSolid(solid);
					
					counter++;
					
					if (counter >= tiles.size())
						break;
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			Editor.getCurrentEditor().setTiles(tiles);
		});
		
		openMap.getContentPane().add(ok);
		
		openMap.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		openMap.setResizable(false);
		openMap.pack();
		openMap.setLocationByPlatform(true);
		
		textBox = new JTextArea();
		
		JScrollPane scroll2 = new JScrollPane(textBox, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scroll2.setPreferredSize(new Dimension(320, 320));
		
		saveMap = new JFrame("Map Maker");
		saveMap.setLayout(new FlowLayout());
		
		saveMap.getContentPane().add(scroll2);
		
		saveMap.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		saveMap.setResizable(false);
		saveMap.pack();
		saveMap.setLocationByPlatform(true);
	}
	
	public static DataProcessor getInstance() {
		return data;
	}
	
	public void displayOpenMap() {
		openMap.setVisible(true);
	}
	
	public void displaySaveMap() {
		saveMap.setVisible(true);
	}
	
	public String retrieveData() {
		return textBox2.getText();
	}
	
	public void setSaveData(String data) {
		textBox.setText(data);
	}
}
