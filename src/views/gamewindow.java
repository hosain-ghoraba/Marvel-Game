package views;
import model.world.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.util.concurrent.locks.Condition;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JTextArea;

import engine.Game;
import engine.Player;

import model.world.Champion;
import model.*;

public class gamewindow extends JFrame {

	JTextArea txtchamp ;
	Player pl1 ;
	Player pl2 ;
	 Game game ;
	
	public gamewindow(Game game , Player pl1, Player pl2) {
		this.game =game ;
		this.setVisible(true);
		this.pl1=pl1;
		this.pl2=pl2 ;
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500,500);
	//	this.setResizable(false);
		ImageIcon logo = new ImageIcon("logo.png");

		this.setIconImage(logo.getImage());
		txtchamp = new JTextArea();
		txtchamp.setPreferredSize(new Dimension(150, getHeight()));

		txtchamp.setEditable(false);
		txtchamp.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
		add(txtchamp, BorderLayout.NORTH);
updatetxt();

	}
	//eee
	
public void updatetxt(){
		String s = pl1.getName() +" : "+"\n" + "remaining champions :";
		for(Champion x : pl1.getTeam()) {
			if(x.getCondition()!=(model.world.Condition.KNOCKEDOUT)) {
				s+= x.getName() + "  ";
			}
		}
		if(game.isFirstLeaderAbilityUsed()) {
			s+="\n"+"  LeaderAbility not used";
		}
		else
			s+="\n"+"  LeaderAbility  used";
		String z = pl2.getName() +" : "+"\n" + "remaining champions :";
		for(Champion x : pl2.getTeam()) {
			if(x.getCondition()!=(model.world.Condition.KNOCKEDOUT)) {
				z+= x.getName() + "  ";
			}
		}
		if(game.isSecondLeaderAbilityUsed()) {
			z+="\n"+"  LeaderAbility not used";
		}
		else
			z+="\n"+"  LeaderAbility  used";
txtchamp.setText(s +"\n"+z);
}
	
}
