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
private Player pl1 ;
private Player pl2 ;
private JTextArea txtchamp;

	public chooseChampions (Game game , Player pl1, Player pl2)
	{
		this.pl1 = pl1 ; 
		this.pl2 = pl2 ;
		this.setTitle(pl1.getName()+"   choose your champions");
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500,500);
		ImageIcon logo = new ImageIcon("logo.png");
		this.setIconImage(logo.getImage());
		txtchamp = new JTextArea();
		txtchamp.setPreferredSize(new Dimension(150, getHeight()));

		txtchamp.setEditable(false);
		txtchamp.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
		add(txtchamp, BorderLayout.EAST);
        updatetxt();
				
		JPanel champs = new JPanel();
		champs.setSize(new Dimension(350,500));
		champs.setLayout(new GridLayout(0, 3));
		champsbuttons = new ArrayList<>() ;
		this.add(champs, BorderLayout.CENTER);
		for ( Champion champ : Game.getAvailableChampions()) 
		{
			JButton btnchamp = new JButton();
			btnchamp.addActionListener(this);
			btnchamp.setText(champ.getName());
	    	btnchamp.setVisible(true);
		    champs.add(btnchamp) ;
			champsbuttons.add(btnchamp);	
		}

		JOptionPane.showMessageDialog(null, pl1.getName()+ " : choose your 3 champions","", JOptionPane.INFORMATION_MESSAGE);

		this.validate();;
		this.repaint();
		
	}


	
@Override	
public void actionPerformed(ActionEvent e) {
		
	JButton x = (JButton)e.getSource() ;
	int i = champsbuttons.indexOf(x);
	
	if(pl1.getTeam().size() < 3)
	{
		pl1.getTeam().add(Game.getAvailableChampions().get(i));
		updatetxt();
		x.setEnabled(false);
		
		if(pl1.getTeam().size() == 3) 
		{
			this.setTitle(pl2.getName()+"   choose your champions");
			JOptionPane.showMessageDialog(null, pl2.getName()+ " : choose your 3 champions","", JOptionPane.INFORMATION_MESSAGE);		
		}
	}
		
	else 
	   {
			pl2.getTeam().add(Game.getAvailableChampions().get(i));
			updatetxt();
			x.setEnabled(false);	
	
			if(pl2.getTeam().size() == 3) 
			{
			this.dispose();	
		    new gamewindow() ;
	        }
		}
	
}

	public void  updatetxt () {
		
		String pl1_Champs = pl1.getName() + "'s"+ " champions :" + "\n" ;		
		for(int i = 0 ; i < pl1.getTeam().size() ; i++) 		
			pl1_Champs += pl1.getTeam().get(i).getName() + "\n" ;	
				
		String pl2_Champs = pl2.getName()+ "'s"+ " champions :"+ "\n" ;		
		for(int i = 0 ; i < pl2.getTeam().size() ; i++) 
			pl2_Champs += pl2.getTeam().get(i).getName() + "\n" ;
					
		txtchamp.setText(pl1_Champs + "\n" + pl2_Champs);

	
	}
	
	
	
	
	
	
	
	
	
	
}
