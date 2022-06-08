package views;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import engine.Game;
import engine.Player;
import model.world.Champion;

public class chooseleaderPlayer2 extends JFrame implements ActionListener {
	
	private Player pl1 ;
	private Player pl2 ;

	public chooseleaderPlayer2 ( Player pl1, Player pl2)
	{
        this.pl1 = pl1;
		this.pl2 = pl2 ;
		this.setTitle(pl2.getName()+"   choose your Leader");
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500,500);
		
		JPanel panelChooseLeader = new JPanel ();
        
		panelChooseLeader.setSize(500,500);
		panelChooseLeader.setLayout(new GridLayout(3, 3));
		for (Champion champ : pl2.getTeam())
		{
			JButton btnchampion2 = new JButton();
			btnchampion2.addActionListener(this);

			btnchampion2.setText(champ.getName());
			panelChooseLeader.add(btnchampion2);

			
		}
		this.add(panelChooseLeader);
		JOptionPane.showMessageDialog(null, pl2.getName()+ " : choose your leader","", JOptionPane.INFORMATION_MESSAGE);

	}
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton clicked = (JButton)e.getSource() ;
        String nameX = clicked.getText();
        for (Champion champ : pl2.getTeam())       
        	if (champ.getName().equals(nameX))    	
         		pl2.setLeader(champ);
	            
        dispose();
        

        new marvelcontroler(new Game(pl1,pl2));
        //new gamewindow(game,game.getFirstPlayer(),game.getSecondPlayer());
	}
}
