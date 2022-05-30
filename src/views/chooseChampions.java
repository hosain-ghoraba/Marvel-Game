package views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import engine.Game;
import engine.Player;
import model.world.Champion;

public class chooseChampions extends JFrame  implements ActionListener{
	private ArrayList<JButton> champsbuttons;
private Player pl1 ;
private Player pl2 ;

	public chooseChampions (Game game , Player pl1, Player pl2)
	{
		this.pl1 = pl1 ; 
		this.pl2 = pl2 ;
		this.setTitle(pl1.getName()+"   choose your champions");
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500,500);
	//	this.setResizable(false);
		ImageIcon logo = new ImageIcon("logo.png");
		this.setIconImage(logo.getImage());
		JPanel champs = new JPanel();
		// set it to use the GridLayout with 3 columns in width
		champs.setSize(new Dimension(400,400));
		champs.setLayout(new GridLayout(0, 4));
		// add it in the center of the JFrame
		champsbuttons = new ArrayList<>() ;
		this.add(champs, BorderLayout.CENTER);
		for ( Champion champ : game.getAvailableChampions()) {
			// create a JButton for each product in the supermarket
			JButton btnchamp = new JButton();
			btnchamp.addActionListener(this);// set its text to the product's info
			btnchamp.setText(champ.getName());
		btnchamp.setVisible(true);
		champs.add(btnchamp) ;
			// add the controller as its ActionListener
		//	btnProduct.addActionListener(this);
			// add it to the products buy buttons panel
		//	supermarketView.addProduct(btnProduct);

			// and also add it to the ArrayList for later use
			champsbuttons.add(btnchamp);	}

		this.validate();;
		this.repaint();
		
		
		
		
		
		
		
		

		
		
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
JButton x = 	(JButton)e.getSource() ;
int i = champsbuttons.indexOf(x);
if(pl1.getTeam().size()<3) {
pl1.getTeam().add(Game.getAvailableChampions().get(i));


if(pl1.getTeam().size()==3) {
	this.setTitle(pl2.getName()+"   choose your champions");

}
}
	
	else {
		if(pl2.getTeam().size()==3) {
			this.dispose();

			new gamewindow() ;


		}
		pl2.getTeam().add(Game.getAvailableChampions().get(i));
   if(pl2.getTeam().size()==3) {
		this.dispose();

	   new gamewindow() ;
   }
	}
	
	}

}
