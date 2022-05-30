package views;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class gamewindow extends JFrame {
	
	public gamewindow() {
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500,500);
	//	this.setResizable(false);
		ImageIcon logo = new ImageIcon("logo.png");
		this.setIconImage(logo.getImage());
	}
	
}
