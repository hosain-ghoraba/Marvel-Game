package views;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import engine.Game;
import engine.Player;

public class gamewindow extends JFrame {
	
	public gamewindow(Game game , Player pl1, Player pl2) {
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500,500);
	//	this.setResizable(false);
		ImageIcon logo = new ImageIcon("logo.png");
		this.setIconImage(logo.getImage());
	}
	
}
