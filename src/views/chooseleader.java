package views;

import javax.swing.JFrame;

import engine.Player;

public class chooseleader extends JFrame {
	
	public chooseleader(Player pl, Player p2) {
		
		this.setTitle(pl.getName()+"   choose your Leader");
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500,500);
	}



}
