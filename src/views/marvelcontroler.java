package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import engine.Game;
import engine.Player;
import exceptions.LeaderAbilityAlreadyUsedException;
import exceptions.LeaderNotCurrentException;
import exceptions.NotEnoughResourcesException;
import exceptions.UnallowedMovementException;
import model.world.Champion;
import model.world.Cover;
import model.world.Direction;

public class marvelcontroler implements ActionListener ,KeyListener{
private gamewindow w ;
	private JButton [][] boardJbutton ;
	private Game game;
	public marvelcontroler (Game game , Player pl1 , Player pl2)
	{
		this.game=new Game(pl1, pl2);
		
		boardJbutton = new JButton [5][5] ;
		w =new gamewindow(game,game.getFirstPlayer(),game.getSecondPlayer());
		w.addKeyListener(this);
		w.gamePanel.addKeyListener(this);
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

	@Override
	public void keyTyped(KeyEvent e) {
System.out.println(e.getKeyChar());
		switch(e.getKeyChar()) {
	case(KeyEvent.VK_RIGHT) :
		try {
			game.move(Direction.RIGHT);
	       		boardJbutton[game.getCurrentChampion().getLocation().x][game.getCurrentChampion().getLocation().y].setText(game.getCurrentChampion().getName());
	       		boardJbutton[game.getCurrentChampion().getLocation().x+1][game.getCurrentChampion().getLocation().y].setText("");
		
		}
	   catch (UnallowedMovementException z) {
		   
			JOptionPane.showMessageDialog(null, "  cannot to move to a non empty place","", JOptionPane.ERROR_MESSAGE);

		   
	   } catch (NotEnoughResourcesException e1) {
			JOptionPane.showMessageDialog(null, "  NotEnoughResources","", JOptionPane.ERROR_MESSAGE);

	} 
	break;
	case(KeyEvent.VK_LEFT) :
		try {
			game.move(Direction.LEFT);
	       		boardJbutton[game.getCurrentChampion().getLocation().x][game.getCurrentChampion().getLocation().y].setText(game.getCurrentChampion().getName());
	       		boardJbutton[game.getCurrentChampion().getLocation().x-1][game.getCurrentChampion().getLocation().y].setText("");
		
		}
	   catch (UnallowedMovementException z) {
		   
			JOptionPane.showMessageDialog(null, "  cannot to move to a non empty place","", JOptionPane.ERROR_MESSAGE);

		   
	   } catch (NotEnoughResourcesException e1) {
			JOptionPane.showMessageDialog(null, "  NotEnoughResources","", JOptionPane.ERROR_MESSAGE);

	} 
	break;
	case(KeyEvent.VK_UP) :
		try {
			game.move(Direction.UP);
	       		boardJbutton[game.getCurrentChampion().getLocation().x][game.getCurrentChampion().getLocation().y].setText(game.getCurrentChampion().getName());
	       		boardJbutton[game.getCurrentChampion().getLocation().x][game.getCurrentChampion().getLocation().y+1].setText("");
		
		}
	   catch (UnallowedMovementException z) {
		   
			JOptionPane.showMessageDialog(null, "  cannot to move to a non empty place","", JOptionPane.ERROR_MESSAGE);

		   
	   } catch (NotEnoughResourcesException e1) {
			JOptionPane.showMessageDialog(null, "  NotEnoughResources","", JOptionPane.ERROR_MESSAGE);

	} 
	break;
	case(KeyEvent.VK_DOWN) :
		try {
			game.move(Direction.DOWN);
	       		boardJbutton[game.getCurrentChampion().getLocation().x][game.getCurrentChampion().getLocation().y].setText(game.getCurrentChampion().getName());
	       		boardJbutton[game.getCurrentChampion().getLocation().x][game.getCurrentChampion().getLocation().y-1].setText("");
		
		}
	   catch (UnallowedMovementException z) {
		   
			JOptionPane.showMessageDialog(null, "  cannot to move to a non empty place","", JOptionPane.ERROR_MESSAGE);

		   
	   } catch (NotEnoughResourcesException e1) {
			JOptionPane.showMessageDialog(null, "  NotEnoughResources","", JOptionPane.ERROR_MESSAGE);

	} 
	break;
    	case('w'):   //leader ability 
		try {
			game.useLeaderAbility();
		}
	catch(LeaderAbilityAlreadyUsedException x) {
		JOptionPane.showMessageDialog(null, "  LeaderAbilityAlreadyUsed","", JOptionPane.ERROR_MESSAGE);

		
	} catch (LeaderNotCurrentException e1) {
		// TODO Auto-generated catch block
		JOptionPane.showMessageDialog(null, "  LeaderNotCurrent","", JOptionPane.ERROR_MESSAGE);
	}
default: break ;
}
	
}
	

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
