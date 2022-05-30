package views;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import engine.Game;
import engine.Player;
import model.world.Champion;

public class chooseleaderPlayer1 extends JFrame implements ActionListener {

	private Player pl1 ;
	private Player pl2 ;
	private Game game;
	public chooseleaderPlayer1(Game game , Player pl1, Player pl2) {
		this.game = game ;
		this.pl1 = pl1 ; 
		this.pl2 = pl2 ;
		this.setTitle(pl1.getName()+"   choose your Leader");
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500,500);
		
		JPanel panelChooseLeader1 = new JPanel ();
		panelChooseLeader1.setSize(500,500);
		panelChooseLeader1.setLayout(new GridLayout(3, 3));


		for (Champion champ : pl1.getTeam())
		{
			JButton btnchampion = new JButton();
			btnchampion.addActionListener(this);
			btnchampion.setText(champ.getName());
			panelChooseLeader1.add(btnchampion);
			
			
		}
		
		this.add(panelChooseLeader1);
		JOptionPane.showMessageDialog(null, pl1.getName()+ " : choose your leader","", JOptionPane.INFORMATION_MESSAGE);


	
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton x = 	(JButton)e.getSource() ;
        String nameX = x.getName();
        for (Champion champ : pl1.getTeam())
        {
        	if (champ.getName().equals(nameX))
        	{
        		pl1.setLeader(champ);
        		
        			
        	}
        	
        }
        this.dispose();
        new chooseleaderPlayer2(game, game.getFirstPlayer(),game.getSecondPlayer());
        
        
	}



}
