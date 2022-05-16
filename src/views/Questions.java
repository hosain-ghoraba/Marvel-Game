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
		
		1- apply shouldn't change appliedEffects ...how on earth !
		2- if INACTIVE and setting to Root, should remain INACTIVE (we should search for the causing effect,
				not the effect itself !)
 		3- chamapion must be removed from turnOrder after being attaked if currentHp reached 0 "does he mean that it should be removed in endTurn ?"		
				
    
    
    1- if a champion died ,must he be removed from his team and from turnOrder ? 
    
    2 -  should endTurn reconstruct the turnOrder? (because speeds might have changed)
    
    5- should endTurn method check also that the next champion in not KnockedOUt ? ( he might have just died).
       the Milestone requires only to check if the next champion is INACTIVE? shouldn't knoucked out also be checked?
    
    7 - how to stop the game and return the winning player if a player got all his champinos died?
        must I checked gameOver after every action method in game ? and if gameOver, what to do ?
    
    8-  what to do in prepareChampions turn if one of teams was empty ? 
    
    
    4- must the useLeaderAbility in game determine passed Champions by looping over the players teams or the turnOrder ? 
    2 - must leader ability of Villain setLift to zero ? or only setCondition to KNOCKEDOUT ?
    11- set condition = knocked out in setCurrentHp if currentHp readed 0 ?
    
 
 
       
        
    */
	
	
	
	
	
	
	
	public static void main(String[] args) {
		Champion attaker = new Hero (null, 0, 0, 0, 0, 0, 0);
		Damageable target = new Hero(null, 0, 0, 0, 0, 0, 0);

		Condition c = Condition.KNOCKEDOUT;
		Condition d = Condition.INACTIVE;
		
		System.out.println(c.equals(d));
	}
	
	
}
	
	
	
	
	
	

