package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.swing.JButton;

import engine.Game;
import engine.Player;
import model.world.Champion;
import model.world.Cover;

public class marvelcontroler implements ActionListener {
private gamewindow w ;
	private JButton [][] boardJbutton ;
	private Game game;
	public marvelcontroler (Game game , Player pl1 , Player pl2)
	{
		this.game=new Game(pl1, pl2);
		
		boardJbutton = new JButton [5][5] ;
		w =new gamewindow(game,game.getFirstPlayer(),game.getSecondPlayer());
		for (int i=0;i<5;i++)
		{
			for (int j=0;j<5;j++)
			{
			
				JButton x = new JButton ();
			x.addActionListener(this);
			boardJbutton[i][j]=x;
			if(game.getBoard()[i][j]!=null) {
				Object z =game.getBoard()[i][j];
				if(z instanceof Cover) {
				Cover c = (Cover)z ;
					
					x.setText("cover "+ " current healthpoints: "+c.getCurrentHP() );	
				
				}
				else {
				Champion c =(Champion) z;
				x.setText(c.getName());
					
				}
			
			}
			w.add_button(x);
		
			
			}
		}
		JButton zero_zero = new JButton ("1");
		JButton zero_one = new JButton ("2");
		JButton zero_two = new JButton ("3");
		JButton zero_three = new JButton ("4");
		JButton zero_four = new JButton ("5");
		JButton one_zero = new JButton ();
		JButton one_one = new JButton ();
		JButton one_two = new JButton ();
		JButton one_three = new JButton ();
		JButton one_four = new JButton ();
		JButton two_zero = new JButton ();
		JButton two_one = new JButton ();
		JButton two_two = new JButton ();
		JButton two_three = new JButton ();
		JButton two_four = new JButton ();
		JButton three_zero = new JButton() ;
		JButton three_one =new JButton() ;
		JButton three_two = new JButton() ;
		JButton three_three = new JButton();
		JButton three_four = new JButton();		
		JButton four_zero = new JButton() ;
		JButton four_one =new JButton() ;
		JButton four_two = new JButton() ;
		JButton four_three = new JButton();
		JButton four_four = new JButton();


	}
	 
	public static void main(String[] args) {

	}

	@Override
	public void actionPerformed(ActionEvent e) {
	
		
		
	}

}
