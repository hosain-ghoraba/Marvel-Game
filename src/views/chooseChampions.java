package views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import engine.Game;
import engine.Player;
import model.world.Champion;

public class chooseChampions extends JFrame  implements ActionListener{

private ArrayList<JButton> champsbuttons;
private Game game;
private Player pl1 ;
private Player pl2 ;
private JTextArea txtchamp;


    public chooseChampions (Game game, Player pl1, Player pl2)
	{
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500,500);
		ImageIcon logo = new ImageIcon("logo.png");
		setIconImage(logo.getImage());
		setExtendedState(JFrame.MAXIMIZED_BOTH);

		this.game = game;
		this.pl1 = pl1 ; 
		this.pl2 = pl2 ;
		setTitle(pl1.getName() + "   choose your champions");
		
		txtchamp = new JTextArea();
		txtchamp.setPreferredSize(new Dimension(150, getHeight()));
		txtchamp.setEditable(false);
		txtchamp.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
		
		this.add(txtchamp, BorderLayout.EAST);
        updatetxt();
		
		
		JPanel champs = new JPanel();
		// set it to use the GridLayout with 3 columns in width
		champs.setSize(new Dimension(350,500));
		champs.setLayout(new GridLayout(0, 1));
		// THE DIFFERENCE BETWEN (5,5) AND (1,5) !!!?
		//champs.setLayout(new GridLayout(0, 5));
		// add it in the center of the JFrame
		champsbuttons = new ArrayList<>() ;
		this.add(champs, BorderLayout.CENTER);

		for ( Champion champ : game.getAvailableChampions())
		{
			JButton btnchamp = new JButton();
			btnchamp.addActionListener(this);
            btnchamp.setText(champ.getName()+  "\n" +"max health points : "+ champ.getMaxHP()+ "\n"+ "mana : "+ champ.getMana()+"\n"+"max action points per turn " + champ.getMaxActionPointsPerTurn()+"\n"+ "speed : "+ champ.getSpeed()+"\n"+"attack range :"+ champ.getAttackRange()+"\n"+ "attack damage: "+champ.getAttackDamage()+" Abilities:  "+champ.getAbilities().get(0).getName()+champ.getAbilities().get(1).getName()+champ.getAbilities().get(2).getName());	
	        champs.add(btnchamp) ;
	        
			// and also add it to the ArrayList for later use
			champsbuttons.add(btnchamp);	
	    }

		JOptionPane.showMessageDialog(null, pl1.getName()+ " : choose your 3 champions","", JOptionPane.INFORMATION_MESSAGE);

		validate();;
		repaint();

	}

public void actionPerformed(ActionEvent e) {
		JButton clicked = (JButton)e.getSource() ;
		int i = champsbuttons.indexOf(clicked);
		if(pl1.getTeam().size() < 3) 
		{
			pl1.getTeam().add(Game.getAvailableChampions().get(i));
			clicked.setEnabled(false) ;
			updatetxt();
					
			if(pl1.getTeam().size() == 3) 
			{
				this.setTitle(pl2.getName()+"   choose your champions");
				JOptionPane.showMessageDialog(null, pl2.getName()+ " : choose your 3 champions","", JOptionPane.INFORMATION_MESSAGE);			
		    }
		}
			
		else {				
				pl2.getTeam().add(Game.getAvailableChampions().get(i));
				clicked.setEnabled(false) ;		
				updatetxt();
		
				if(pl2.getTeam().size() == 3) 
				{
					dispose();		
				    new chooseleaderPlayer1(pl1,pl2) ;
                }
			}
			
	}

	public void  updatetxt () {
		
		String pl1_champs = pl1.getName() + "'s"+ " champions :" + "\n" ;
		for(int i = 0 ; i < pl1.getTeam().size() ; i++) 
			pl1_champs += pl1.getTeam().get(i).getName() + "\n" ;
					
		String pl2_champs = pl2.getName() + "'s" + " champions :" + "\n" ;
		for(int i = 0 ; i < pl2.getTeam().size() ; i++) 
			pl2_champs += pl2.getTeam().get(i).getName() + "\n" ;
			
		
		
		txtchamp.setText(pl1_champs+ "\n" + pl2_champs);

	
		
	}
	
	
}
