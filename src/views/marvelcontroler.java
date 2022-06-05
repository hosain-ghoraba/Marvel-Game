package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import engine.Game;
import exceptions.AbilityUseException;
import exceptions.ChampionDisarmedException;
import exceptions.LeaderAbilityAlreadyUsedException;
import exceptions.LeaderNotCurrentException;
import exceptions.NotEnoughResourcesException;
import exceptions.UnallowedMovementException;
import model.abilities.Ability;
import model.abilities.AreaOfEffect;
import model.world.Champion;
import model.world.Cover;
import model.world.Direction;

public class marvelcontroler implements GameListener,ActionListener ,KeyListener{

	private gamewindow w ;
	private JButton [][] boardJbutton ;
	private Game game;
	Boolean isabilitycasted ;
	public marvelcontroler (Game game)
	{
		isabilitycasted =false ;
		this.game = game;		
		boardJbutton = new JButton [5][5] ;
		w =new gamewindow(game);
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
	/*	JButton zero_zero = new JButton ("1");
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
*/

	}
	 
	

	@Override
	public void actionPerformed(ActionEvent e) {
	if(isabilitycasted==false) return ;
	JButton z = (JButton) e.getSource();
	//remaining code goes here 
		
		
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
			  w.updateplayersdata();
	            updateboard(); 
		}
	catch(LeaderAbilityAlreadyUsedException x) {
		JOptionPane.showMessageDialog(null, "  LeaderAbilityAlreadyUsed","", JOptionPane.ERROR_MESSAGE);

		
	} catch (LeaderNotCurrentException e1) {
		// TODO Auto-generated catch block
		JOptionPane.showMessageDialog(null, "  LeaderNotCurrent","", JOptionPane.ERROR_MESSAGE);
	}
    	break;
    	case('a')://attack
    		String[] options = new String[] {"Right", "left", "up", "down"};
        int response = JOptionPane.showOptionDialog(null, "choose the direction of the attack", "attack",
            JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
            null, options, options[0]);
         attack(response);  
         w.updateplayersdata();
            updateboard();  //need to update the board 
         //need to check if game is over
         break ;
    	case('e'): //end turn 
    	
    			game.endTurn();
    			w.update_curchamp_datails();
    			w.updateplayersdata();
    		     updateboard();   //need to update the board
    	         //need to check if game is over
break ;
    	case ('c'): //casting an ability 
             ArrayList<Ability> opt = new ArrayList<>() ;
    	    for(Ability z : game.getCurrentChampion().getAbilities()) {
    	    	if (z.getCurrentCooldown()==0)
    	    		opt.add(z);
    	    	
    	    }
    		String[] options2 = new String[opt.size()];
    		 for(int i =0;i<opt.size();i++) {
    			 
    			 options2[i]= opt.get(i).getName();
    			 
    		 }
    		  int response2 = JOptionPane.showOptionDialog(null, "which ability do you want to cast", "ability",
    		            JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
    		            null, options2, options2[0]);
    			castabilt(opt.get(response2));
    			w.updateplayersdata();
   		     updateboard();
    	    
    	    
    	    
    	    
    	    
    		          		
    	
default: break ;
}
	
}
	private void castabilt(Ability a)  {
		// TODO Auto-generated method stub
		if(a.getCastArea()==AreaOfEffect.SELFTARGET ||a.getCastArea()==AreaOfEffect.TEAMTARGET   ||a.getCastArea()==AreaOfEffect.TEAMTARGET) {
			try
		{game.castAbility(a);}
		catch(NotEnoughResourcesException e) {
			JOptionPane.showMessageDialog(null, "  champion does not have enough resources","", JOptionPane.ERROR_MESSAGE);

			
		} catch (AbilityUseException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, " you have an effect that doesn't let you use ability","", JOptionPane.ERROR_MESSAGE);
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}}
		
		else if(a.getCastArea()==AreaOfEffect.DIRECTIONAL) {
    		String[] options = new String[] {"Right", "left", "up", "down"};

			int response = JOptionPane.showOptionDialog(null, "choose the direction of the ability", "ability",
			            JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
			            null, options, options[0]);
	while(response==-1) {
			switch(response) {
		case(0):
			try{game.castAbility(a, Direction.RIGHT);}
		catch(NotEnoughResourcesException e)
		{
			JOptionPane.showMessageDialog(null, "  champion does not have enough resources","", JOptionPane.ERROR_MESSAGE);

			
		} catch (AbilityUseException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, " you have an effect that doesn't let you use ability","", JOptionPane.ERROR_MESSAGE);
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		break ;
		case(1):
			try{game.castAbility(a, Direction.LEFT);}
		catch(NotEnoughResourcesException e)
		{
			JOptionPane.showMessageDialog(null, "  champion does not have enough resources","", JOptionPane.ERROR_MESSAGE);

			
		} catch (AbilityUseException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, " you have an effect that doesn't let you use ability","", JOptionPane.ERROR_MESSAGE);
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		break ;
		case(2):
			try{game.castAbility(a, Direction.UP);}
		catch(NotEnoughResourcesException e)
		{
			JOptionPane.showMessageDialog(null, "  champion does not have enough resources","", JOptionPane.ERROR_MESSAGE);

			
		} catch (AbilityUseException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, " you have an effect that doesn't let you use ability","", JOptionPane.ERROR_MESSAGE);
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		break ;
		case(3):
			try{game.castAbility(a, Direction.DOWN);}
		catch(NotEnoughResourcesException e)
		{
			JOptionPane.showMessageDialog(null, "  champion does not have enough resources","", JOptionPane.ERROR_MESSAGE);

			
		} catch (AbilityUseException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, " you have an effect that doesn't let you use ability","", JOptionPane.ERROR_MESSAGE);
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		break ;
		 default: //user closed the window without choosing
			  while(response==-1) {
		    		

				  response = JOptionPane.showOptionDialog(null, "you didn't choose the direction of the ability, please choose the direction of the ability", "ability",
				            JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
				            null, options, options[0]);
				  break ;
			  }}
			
	
	
	}}
		
		else {
			isabilitycasted =true ;
			JOptionPane.showMessageDialog(null, " please choose who do you want to cast this ability on","", JOptionPane.INFORMATION_MESSAGE);
			
			
		}
		
		
		
	}



	public void updateboard() {
		for(int i=0 ;i<5;i++) {
			for(int j=0;j<5;j++) {
				if(game.getBoard()[i][j]==null)
					boardJbutton[i][j].setText("")
					;
				else {
					if(game.getBoard()[i][j] instanceof Champion) {
						boardJbutton[i][j].setText(((Champion)game.getBoard()[i][j]).getName());
						//need to add champion details in hover
						

					}
					else
					{
						
						boardJbutton[i][j].setText("cover , current health pionts:" +((Cover)game.getBoard()[i][j]).getCurrentHP() );
						
					}
					
				}
				
			}
		}
		
	}

	private void attack(int response) {
		// TODO Auto-generated method stub
		  switch(response) {
	       case(0): try {
				game.attack(Direction.RIGHT);
			} catch (NotEnoughResourcesException j) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, " NotEnoughResources","", JOptionPane.ERROR_MESSAGE);

			} catch (ChampionDisarmedException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, " ChampionDisarmed","", JOptionPane.ERROR_MESSAGE);
			}
	       break ;
	       case(1): try {
				game.attack(Direction.LEFT);
			} catch (NotEnoughResourcesException j) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, " NotEnoughResources","", JOptionPane.ERROR_MESSAGE);

			} catch (ChampionDisarmedException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, " ChampionDisarmed","", JOptionPane.ERROR_MESSAGE);
			}	       break ;

	       case(2): try {
				game.attack(Direction.UP);
			} catch (NotEnoughResourcesException j) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, " NotEnoughResources","", JOptionPane.ERROR_MESSAGE);

			} catch (ChampionDisarmedException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, " ChampionDisarmed","", JOptionPane.ERROR_MESSAGE);
			}	       break ;

	       case(3): try {
				game.attack(Direction.DOWN);
			} catch (NotEnoughResourcesException j) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, " NotEnoughResources","", JOptionPane.ERROR_MESSAGE);

			} catch (ChampionDisarmedException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, " ChampionDisarmed","", JOptionPane.ERROR_MESSAGE);
			}	       break ;

		  default: //user closed the window without choosing
			  while(response==-1) {
		    		String[] options = new String[] {"Right", "left", "up", "down"};
 
				  response = JOptionPane.showOptionDialog(null, "you didn't choose the direction of the attack, please choose the direction of the attack", "attack",
				            JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
				            null, options, options[0]);
			  }
		         attack(response);  
                 break ;
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



	
	
	
	
	
	
	public void onMove(Direction d) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onAttake(Direction d) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUseLeaderAbility() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onCastAbility(Ability a, Direction d) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onCastAbility(Ability a, int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onCastAbility(Ability a) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onEndTurn() {
		// TODO Auto-generated method stub
		
	}
   public static void main(String[] args) {
//	while(true) {
	String[] options = new String[] {"Right", "left", "up"};
	ArrayList<String> o = new ArrayList<>() ;
	o.add("r");
	o.add("l");
	o.add("u");
	o.add("d");

    int response = JOptionPane.showOptionDialog(null,  "you didn't choose the direction of the attack, please choose the direction of the attack", "Title",
        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
        null, options, options[0]);
System.out.println(response);}






}
