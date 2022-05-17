package views;

import engine.Game;
import model.world.AntiHero;
import model.world.Champion;
import model.world.Condition;
import model.world.Damageable;
import model.world.Hero;
import model.world.Villain;

public class Questions {
  
	/* questions

	FAILURES : 
		
		1- logic root inactive
		2- apply shouldn't change appliedEffects ...how on earth !
        3-  should endTurn reconstruct the turnOrder? (because speeds might have changed) ( related to 8)
		4-  array OutofBound (teamTarget)		
		5- abilityUse or invalidTarget		
    
    6- if a champion died ,must he be removed from his team and from turnOrder ? 
    
    7-  what to do in prepareChampions turn if one of teams was empty ?
    
    8- should endTurn method check also that the next champion in not KnockedOUt ? ( he might have just died).
       the Milestone requires only to check if the next champion is INACTIVE? shouldn't knoucked out also be checked?
    
     
    8 - how to stop the game and return the winning player if a player got all his champinos died?
        must I checked gameOver after every action method in game ? and if gameOver, what to do ?
    
    
    */
	
	
	
	
	
	
	
	public static void main(String[] args) {
		Champion attaker = new Hero (null, 0, 0, 0, 0, 0, 0);
		Damageable target = new Hero(null, 0, 0, 0, 0, 0, 0);

		Condition c = Condition.KNOCKEDOUT;
		Condition d = Condition.INACTIVE;
		
		System.out.println(c.equals(d));
	}
	
	
}
	
	
	
	
	
	

