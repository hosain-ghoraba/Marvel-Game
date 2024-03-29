package views;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import engine.Game;
import engine.Player;
import exceptions.AbilityUseException;
import exceptions.ChampionDisarmedException;
import exceptions.GameActionException;
import exceptions.NotEnoughResourcesException;
import model.abilities.Ability;
import model.abilities.AreaOfEffect;
import model.world.Champion;
import model.world.Cover;
import model.world.Direction;

public class marvelcontroler implements ActionListener {


	private gamewindow view ;
	private Game game;
	private JButton [][] boardJbutton ;
	Boolean isSingleTargerAbilityCasted ;
	Ability singleTargetAbility ;
	
	 public marvelcontroler (Game game)
	{
		 this.game = game;			
		 view = new gamewindow(game);
		 boardJbutton = new JButton [5][5] ;
    	 isSingleTargerAbilityCasted = false ;

		for (int i = 4 ; i >= 0 ; i--)
		{
			for (int j = 0 ; j < 5 ; j++)
			{			
				JButton btn = new JButton ();				
				btn.addActionListener(this);
				boardJbutton[i][j] = btn;
				view.add_button(btn);
		
			}
		}
		updateboard();
		for(Component comp : view.actionsPanel.getComponents())
			((JButton)comp).addActionListener(this);
        

	}
	
        
	 	public void updateboard() {
			for(int i=4 ;i>=0;i--) {
				for(int j=0;j<5;j++) {
					JButton btn = boardJbutton[i][j];

						Object z = game.getBoard()[i][j];
						String btnText = "";
						if(z == null)
						{							
							btn.setBackground(Color.white);
						}
						else if(z instanceof Cover) 
						{
							Cover c = (Cover) z ;
							btnText += "cover \n" + "HP : " + c.getCurrentHP();
							btn.setBackground(Color.orange);
					
						}
						else 
						{
							Champion c = (Champion) z;
							btnText += " Player : ";
							if(game.getFirstPlayer().getTeam().contains(c))
							{
								btnText += game.getFirstPlayer().getName() + '\n';
								btn.setBackground(Color.cyan);
							}
							else
							{
								btnText += game.getSecondPlayer().getName() + '\n';
								btn.setBackground(Color.pink);
							}
							
							btnText += c.getName() + '\n' + "HP : " + c.getCurrentHP() ;
							if(c == game.getCurrentChampion() )
								btn.setBackground(Color.green);
						    
					
						}
						
						btn.setText(Game.StringToHTML(btnText));
					    
				
					
					}
					
				}
			
			
		}
        public void updateInfoPanel() {

        	view.infoPanel = view.give_updated_infoPanel(game);
  
        }
		
		
        private void attack(int response) throws NotEnoughResourcesException, ChampionDisarmedException  {
			
			Direction attakeDirection = null;			
			switch(response) 
			{
			
			case(0): attakeDirection = Direction.RIGHT;  break;
			case(1): attakeDirection = Direction.LEFT ; break;					
			case(2): attakeDirection = Direction.UP ; break;                  			
			case(3): attakeDirection = Direction.DOWN ; break;
			
			default: //user closed the window without choosing
			{							
				
			/*	while(response == -1) 
				{
					String[] options = new String[] {"Right", "left", "up", "down"};					
					response = JOptionPane.showOptionDialog(null, "you didn't choose the direction of the attack, please choose the direction of the attack", "attack",
					JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,null, options, options[0]);
				}
				attack(response);*/
				return;
				
			}
			
			}

			game.attack(attakeDirection);


			
			
}
		
        public void onAttake() throws NotEnoughResourcesException, ChampionDisarmedException
		{			
			String[] options = new String[] {"Right", "left", "up", "down"};
    		int response = JOptionPane.showOptionDialog(null, "choose the direction of the attack", "attack",
            JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
            null, options, options[0]);
    		attack(response);      
            updateboard();
            updateInfoPanel();
    		view.revalidate();
    		view.repaint();
		}
        
    	private void castabilt(Ability a) throws AbilityUseException, NotEnoughResourcesException, CloneNotSupportedException  {
			// TODO Auto-generated method stub
			if(a.getCastArea()==AreaOfEffect.SELFTARGET ||a.getCastArea()==AreaOfEffect.TEAMTARGET   ||a.getCastArea()==AreaOfEffect.SURROUND) 			
				game.castAbility(a);

			else if(a.getCastArea()==AreaOfEffect.DIRECTIONAL) 
			{
	    		String[] options = new String[] {"Right", "left", "up", "down"};
				int response = JOptionPane.showOptionDialog(null, "choose the direction of the ability", "ability",JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,null, options, options[0]);
				switch(response) 
				{
				
				case(0): game.castAbility(a, Direction.RIGHT);break;					
				case(1): game.castAbility(a, Direction.LEFT);break;					
				case(2): game.castAbility(a, Direction.UP);break;					
				case(3): game.castAbility(a, Direction.DOWN);break;
			
			    default: //user closed the window without choosing
				   return ; }}
			    	
			/*		while(response == - 1) 
					  {
					  response = JOptionPane.showOptionDialog(null, "you didn't choose the direction of the ability, please choose the direction of the ability", "ability",JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,null, options, options[0]);
					  break ;
					  }
			    	}
			    
				}
				*/
			
		
			else 
			{				
				isSingleTargerAbilityCasted = true ;
				singleTargetAbility = a ;
				JOptionPane.showMessageDialog(null, " please choose who do you want to cast this ability on","", JOptionPane.INFORMATION_MESSAGE);
			}	
		}
        public void onCastAbility() throws AbilityUseException, NotEnoughResourcesException, CloneNotSupportedException {
			ArrayList<Ability> opt = new ArrayList<>() ;
    	    for(Ability h : game.getCurrentChampion().getAbilities())
    	    {
    	    	if (h.getCurrentCooldown() == 0)
    	    		opt.add(h);
    	    	
    	    }
    		String[] options2 = new String[opt.size()];
    		if (opt.size() == 0)
    		{
    			JOptionPane.showMessageDialog(null, "none of your abilities is ready ot be cast now","", JOptionPane.INFORMATION_MESSAGE);
    			return;
    		}
    		 for(int i = 0 ; i < opt.size() ; i++)     			 
    			 options2[i]= opt.get(i).getName();   			   		 
    		 int response2 = JOptionPane.showOptionDialog(null, "which ability do you want to cast", "ability",JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,null, options2, options2[0]);
    		 if(response2 == -1)
    			 return ;
    		 castabilt(opt.get(response2));
             // ability casted ! (see end of upper line)
		}
  		
		public int[] getindex (JButton btn ) {
			 for(int i = 0 ; i < 5 ; i++) 
				 for(int j = 0 ; j < 5 ; j++)
					 if(boardJbutton[i][j] != null ) 
						 if(boardJbutton[i][j] == btn)
							 return new int [] {i,j} ;
	        
			 return null ;
		 
		 }
		
		
		
		public void actionPerformed(ActionEvent e) {
			
		JButton clicked = ((JButton)e.getSource());		
		
		if(isSingleTargerAbilityCasted && ! btnInBoard(clicked) ) // for philosophers #) 
		{
			JOptionPane.showMessageDialog(null, "please focus ! you must click on a target in the board now to cast the ability on !" ,"", JOptionPane.ERROR_MESSAGE);
		    return;
		}
		 
		try
		{
			
		switch (clicked.getText())
		{
		case "move up" : game.move(Direction.UP);break;
		case "move down" : game.move(Direction.DOWN);break;
		case "move right" : game.move(Direction.RIGHT);break;
		case "move left" : game.move(Direction.LEFT);break;
		case "attack" : onAttake();break;
		case "use leader ability" : game.useLeaderAbility();break;
		case "end turn" : game.endTurn();break;
		case "cast ability" : onCastAbility();break; 
		
		default : // it is a click on the board (single target ability) ) 
	      {
			if(isSingleTargerAbilityCasted == false) 
			{
				JOptionPane.showMessageDialog(null, "if you want to cast a single target ability on this square, try to click on the \"cast ability\" button first ! " ,"", JOptionPane.ERROR_MESSAGE);
				return ;
			}
			isSingleTargerAbilityCasted = false ;
			int[] b = getindex(clicked) ;				
			game.castAbility(singleTargetAbility , b[0],b[1]);
	
		  }
		}		
		Player winner = game.checkGameOver();
		if(winner != null)
		{
			JOptionPane.showMessageDialog(null, "Player " + winner.getName() + " is the winner !","Game Over !", JOptionPane.ERROR_MESSAGE);
			System.exit(0); // stop program
			view.dispose(); // close window
		}
		    
		updateboard();
		updateInfoPanel();
		view.revalidate();
		view.repaint();
		
		}
		catch(GameActionException exp)
		{
			JOptionPane.showMessageDialog(null, exp.getMessage(),"", JOptionPane.ERROR_MESSAGE);
		}
		catch(Exception general)
		{
			general.printStackTrace();
		}
		
	
}

 public boolean btnInBoard(JButton btn) {
	 for(int i = 0 ; i < 5 ; i++)
		 for(int j = 0 ; j < 5 ; j++)
			 if(boardJbutton[i][j] == btn)
				 return true;
	 return false;
 }

		public static void main(String[] args) {	
    	new loginWindow();

}
}