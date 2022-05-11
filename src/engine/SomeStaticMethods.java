package engine;

import java.awt.Point;
import java.util.ArrayList;

import model.abilities.Ability;
import model.effects.Effect;
import model.world.Champion;
import model.world.Condition;
import model.world.Cover;
import model.world.Damageable;

public class SomeStaticMethods {

	        public static boolean doesEffectExist(ArrayList<Effect> list,String EffectName) {
				for(int i = 0 ; i < list.size() ; i++)
				{
					if(list.get(i).getName().equals(EffectName))
						return true;
				    i++;
				}
				return false;
			}
           
	        public static void checkIfDeadAndActAccordingly(Damageable d) {
	        	  if(d.getCurrentHP() != 0)
	        		  return;	
	       		  if(d instanceof Cover)
	       			  board[((Cover)d).getLocation().x][((Cover)d).getLocation().y] = null;// will think how to slove this compilation error
	       		  else 
	       		  {
	       			  Champion c = (Champion)d;
	       			  board[c.getLocation().x][c.getLocation().y] = null;// will think how to slove this compilation error
	       		      c.setCondition(Condition.KNOCKEDOUT);
	       		  //  remove the Champion from the PlayerTeam, and if Team became Empty,
	       		  //     then gave over! (dont know how to over a game yet) 
	       		  // checkGameOver and act accordingaly method    
	       		  }
            	
            }
	        // Code to calculate Manhattan distance(from geeks to geeks)

	        static int calcDistance(Damageable d1, Damageable d2) {
	        	
	        	Point P1 = d1.getLocation();
	        	Point P2 = d2.getLocation();
	        	int x1 = P1.x;
	        	int y1 = P1.y;
	        	int x2 = P2.x;
	        	int y2 = P2.y;
	        	return Math.abs(x2 - x1) + Math.abs(y2 - y1);
	        	         
	        }
}
