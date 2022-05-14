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
       
    
    2 - must leader ability of Villain setLift to zero ? or only setCondition to KNOCKEDOUT ?
    
    3 - will a player team ever be empty ( will knockedout champions be removed from it, or will stay in 
       it but with knockedout condition ?
    
    4- must the useLeaderAbility in game determine passed Champions by looping over the players teams or the turnOrder ? 
    
    5- should endTurn method check also that the next champion in not KnockedOUt ? ( he might have just died).
       the Milestone requires only to check if the next champion is INACTIVE? shouldn't knoucked out also be checked?
    
    6 - VERY IMPORTANT !!!! if a champion died, I understand he should be immediately removed of the board,
       however, should he be immidiately removed from his player's team Array and from the turnOrder PQ?
       or this will happen in prepareChampionsTurns method ?
    7 - how to stop the game and return the winning player if a player got all his champinos died?
        must I checked gameOver after every action method in game ? and if gameOver, what to do ?
    
    8-  what to do in prepareChampions turn if one of teams was empty ? 
    
    9- must checkGameOver check if the team was empty ? or check if all its members are knockedout ?
    
    10- in endTurn method, if the current Champion was INACTIVE, shouldn't its timers also be updated ?
    
    11- set condition = knocked out in setCurrentHp if currentHp readed 0 ?
    
    12- IMPORTANT : should endTurn reconstruct the turnOrder? (because speeds might have changed)
    failues : 
    
    1- apply shouldn't change appliedEffects ...how on earth !
    2- if INACTIVE and setting to Root, should remain INACTIVE (we should search for the causing effect,
    not the effect itself !)
    
    
 
 
       
        
    */
	
	
	
	
	
	
	
	public static void main(String[] args) {
		Champion attaker = new Hero (null, 0, 0, 0, 0, 0, 0);
		Damageable target = new Hero(null, 0, 0, 0, 0, 0, 0);

		Condition c = Condition.KNOCKEDOUT;
		Condition d = Condition.INACTIVE;
		
		System.out.println(c.equals(d));
	}
	
	
}
	
	
	
	
	
	

