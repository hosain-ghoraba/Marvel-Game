package model.abilities;

import javax.swing.JOptionPane;

import exceptions.GameActionException;
import model.world.Champion;
import model.world.Cover;
import model.world.Direction;

public class habl {
	/* private void attack(int response) {
		// TODO Auto-generated method stub
		  switch(response) {
	       case(0): try {
				game.attack(Direction.RIGHT);
			} catch (GameActionException  j) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, j.getMessage(),"", JOptionPane.ERROR_MESSAGE);

			} 
	       break ;
	       case(1): try {
				game.attack(Direction.LEFT);
			} catch (GameActionException  j) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, j.getMessage(),"", JOptionPane.ERROR_MESSAGE);

			} 	       break ;

	       case(2): try {
				game.attack(Direction.UP);
			}catch (GameActionException  j) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, j.getMessage(),"", JOptionPane.ERROR_MESSAGE);

			} 	       break ;

	       case(3): try {
				game.attack(Direction.DOWN);
			} catch (GameActionException  j) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, j.getMessage(),"", JOptionPane.ERROR_MESSAGE);

			} 	       break ;

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

	public void updateboard() {
		for(int i=4 ;i>=0;i--) {
			for(int j=0;j<5;j++) {
				if(game.getBoard()[i][j]==null)
					boardJbutton[i][j].setText("")
					;
				else {
					if(game.getBoard()[i][j] instanceof Champion) {
						boardJbutton[i][j].setText(((Champion)game.getBoard()[i][j]).getName()+  "  current health points:"+ ((Champion)game.getBoard()[i][j]).getCurrentHP());
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
	private void castabilt(Ability a)  {
		// TODO Auto-generated method stub
		if(a.getCastArea()==AreaOfEffect.SELFTARGET ||a.getCastArea()==AreaOfEffect.TEAMTARGET   ||a.getCastArea()==AreaOfEffect.SURROUND) {
			try
		{game.castAbility(a);}
		catch(GameActionException  e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),"", JOptionPane.ERROR_MESSAGE);

			
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
		catch(GameActionException  e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),"", JOptionPane.ERROR_MESSAGE);

			
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		break ;
		case(1):
			try{game.castAbility(a, Direction.LEFT);}
		catch(GameActionException  e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),"", JOptionPane.ERROR_MESSAGE);

			
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		break ;
		case(2):
			try{game.castAbility(a, Direction.UP);}
		catch(GameActionException  e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),"", JOptionPane.ERROR_MESSAGE);

			
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		break ;
		case(3):
			try{game.castAbility(a, Direction.DOWN);}
		catch(GameActionException  e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),"", JOptionPane.ERROR_MESSAGE);

			
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		break ;
		 default: //user closed the window without choosing
			 
		    		

				  response = JOptionPane.showOptionDialog(null, "you didn't choose the direction of the ability, please choose the direction of the ability", "ability",
				            JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
				            null, options, options[0]);
				  break ;
			  }
			
	
	
	}}
		
		else {
			issingletargerabilitycasted =true ;
			singletargerability = a ;
			JOptionPane.showMessageDialog(null, " please choose who do you want to cast this ability on","", JOptionPane.INFORMATION_MESSAGE);
			
			
		}
		
		
		
	}



	*/

}