package help;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Help {
	private static Help help;
	
	private JFrame frame;
	
	private Help() {
		frame = new JFrame();
		
		frame.setPreferredSize(new Dimension(200, 125));
		
		frame.add(new JLabel("<html>Map Maker is a utility program, its purpose being to design tile based maps for role playing video games.</html>"));
		
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setResizable(false);
		frame.pack();
		frame.setLocationRelativeTo(null);
	}
	
	public static Help getInstance() {
		if (help == null)
			help = new Help();
		
		return help;
	}
	
	public void display() {
		frame.setVisible(true);
	}
}
