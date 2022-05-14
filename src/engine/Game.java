

package engine;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import exceptions.AbilityUseException;
import exceptions.ChampionDisarmedException;
import exceptions.InvalidTargetException;
import exceptions.NotEnoughResourcesException;
import exceptions.UnallowedMovementException;
import model.abilities.Ability;
import model.abilities.AreaOfEffect;
import model.abilities.CrowdControlAbility;
import model.abilities.DamagingAbility;
import model.abilities.HealingAbility;
import model.effects.Disarm;
import model.effects.Dodge;
import model.effects.Effect;
import model.effects.EffectType;
import model.effects.Embrace;
import model.effects.PowerUp;
import model.effects.Root;
import model.effects.Shield;
import model.effects.Shock;
import model.effects.Silence;
import model.effects.SpeedUp;
import model.effects.Stun;
import model.world.AntiHero;
import model.world.Champion;
import model.world.Condition;
import model.world.Cover;
import model.world.Damageable;
import model.world.Direction;
import model.world.Hero;
import model.world.Villain;
public class Game {
 private Player firstPlayer;
 private Player secondPlayer;
 private boolean firstLeaderAbilityUsed;
 private boolean secondLeaderAbilityUsed;
 private Object[][] board;
 private static ArrayList<Champion> availableChampions;
 private static ArrayList<Ability> availableAbilities;
 private PriorityQueue turnOrder;
 private static final int BOARDHEIGHT=5;
 private static final int BOARDWIDTH=5;
 public Game(Player first, Player second)
 {
	 this.firstPlayer=first;
	 this.secondPlayer=second;
	 firstLeaderAbilityUsed = false;
	 secondLeaderAbilityUsed = false;
	 availableChampions = new ArrayList<Champion>();
	 availableAbilities = new ArrayList<Ability>();
	 turnOrder = new PriorityQueue(firstPlayer.getTeam().size() + secondPlayer.getTeam().size());
	 for(int i = 0 ; i < firstPlayer.getTeam().size() ; i++)
		 turnOrder.insert(firstPlayer.getTeam().get(i));	     
	 for(int i = 0 ; i < secondPlayer.getTeam().size() ; i++)
		 turnOrder.insert(secondPlayer.getTeam().get(i));	 
	 board = new Object[5][5];
	 placeChampions();placeCovers();
	
/////////// no setters in this class
	 
/////////////////////////////////////////////////////////////////////// getters	 
 }
 public Player getFirstPlayer() {
	return firstPlayer;
}
public Player getSecondPlayer() {
	return secondPlayer;
}
public boolean isFirstLeaderAbilityUsed() {
	return firstLeaderAbilityUsed;
}
public boolean isSecondLeaderAbilityUsed() {
	return secondLeaderAbilityUsed;
}
public Object[][] getBoard() {
	return board;
}
public static ArrayList<Champion> getAvailableChampions() {
	return availableChampions;
}
public static ArrayList<Ability> getAvailableAbilities() {
	return availableAbilities;
}
public PriorityQueue getTurnOrder() {
	return turnOrder;
}

public static int getBoardheight() {
	return BOARDHEIGHT;
}
public static int getBoardwidth() {
	return BOARDWIDTH;
}
//////////////////////////////////////////////////////////////////////// end of getters

private void placeChampions() {
	
int x =1;
for(int i =0 ;i<firstPlayer.getTeam().size();i++) {
	
	if(x<BOARDWIDTH-1)
	{
		Champion a= firstPlayer.getTeam().get(i);
		board[0][x] = a;
		a.setLocation(new Point(0,x));
		x++;
	} 
}
x=1 ;
for(int i =0 ;i<secondPlayer.getTeam().size();i++) {
	
	if(x<BOARDWIDTH-1)
	{
	 Champion a =secondPlayer.getTeam().get(i);
	 board[BOARDHEIGHT-1][x]=  a;
	a.setLocation(new Point(BOARDHEIGHT-1,x));
	x++;
	}
	}
}
private void placeCovers() {
	
	 Random rand = new Random();	
	int counter =5;
	 while (counter>0)
	 {
		 int x= rand.nextInt(5);
		 int y= rand.nextInt(5);
		 
		 if (board[x][y]==null && x >= 1 && x < BOARDHEIGHT-1 )
		 {
				 board[x][y]= new Cover (x,y);
				 counter--;
		 }
		 
	 }
}
     public static Effect generateEffect (String x, int y)
     {
    	 switch (x) {
    		 
    	 case ("Disarm"): return new Disarm (y);
    	 case ("Dodge"): return new Dodge (y);
    	 case ("Embrace"): return new Embrace (y);
    	 case ("PowerUp"): return new PowerUp (y);
    	 case ("Root"): return new Root (y);
    	 case ("Shield"): return new Shield (y);
    	 case ("Shock"): return new Shock (y);
    	 case ("Silence"): return new Silence (y);
    	 case ("SpeedUp"): return new SpeedUp (y);
    	 case ("Stun"): return new Stun (y);
    	 default : return null;
    		 
    	 }
     }
	 public static void loadAbilities(String filePath) throws IOException
	 {
		 BufferedReader br= new BufferedReader(new FileReader(filePath));
		 String x= br.readLine();
		 while (x!=null)
        {
		 
         String arr[]= x.split(",");
       
        	 if (arr[0].equals("CC"))
        	 {
        		 availableAbilities.add(new CrowdControlAbility(arr[1],Integer.parseInt(arr[2]),Integer.parseInt(arr[4]),Integer.parseInt(arr[3]),AreaOfEffect.valueOf(arr[5])
        				 ,Integer.parseInt(arr[6]),generateEffect(arr[7],Integer.parseInt(arr[8]))));
        	 }
        	 else if (arr[0].equals("DMG"))
        	 {
        		 availableAbilities.add(new DamagingAbility(arr[1],Integer.parseInt(arr[2]),Integer.parseInt(arr[4]),Integer.parseInt(arr[3]),AreaOfEffect.valueOf(arr[5])
        				 ,Integer.parseInt(arr[6]),Integer.parseInt(arr[7])));
        	 }
        	 else if (arr[0].equals("HEL"))
        	 {
        		 availableAbilities.add(new HealingAbility(arr[1],Integer.parseInt(arr[2]),Integer.parseInt(arr[4]),Integer.parseInt(arr[3]),AreaOfEffect.valueOf(arr[5])
        				 ,Integer.parseInt(arr[6]),Integer.parseInt(arr[7])));
        	 }
         
         x=br.readLine();
        }
	 }
	 public static Ability generateAbility (String name) throws IOException
	 {	
		 for (int i=0;i<availableAbilities.size();i++)
		 {
			 Ability a = availableAbilities.get(i);
			 if (a.getName().equals(name))
				 return a;
		 }
		 return null;
	 }
	 public static void loadChampions(String filePath) throws IOException {
           
		  BufferedReader br= new BufferedReader(new FileReader(filePath));
		  String x= br.readLine();	  
		  while (x != null)
	      {  
	         String arr[]= x.split(",");	
	         	    	 
	    	 Ability a1 = generateAbility(arr[8]);
	    	 Ability a2 = generateAbility(arr[9]);
	    	 Ability a3 = generateAbility(arr[10]);	
	    	 
	    	 if (arr[0].equals("H")) 
	    	 {
	    	  Hero H = new Hero (arr[1],Integer.parseInt(arr[2]),Integer.parseInt(arr[3]),Integer.parseInt(arr[4]),Integer.parseInt(arr[5])
	    				 ,Integer.parseInt(arr[6]),Integer.parseInt(arr[7]));
	    	  
	    	  H.getAbilities().add(a1);
	    	  H.getAbilities().add(a2);
	    	  H.getAbilities().add(a3);	   	  
	    	  availableChampions.add(H); 	  
	    	 }
	    	 else if (arr[0].equals("A")) 
	    	 {
	    		 AntiHero A = new AntiHero (arr[1],Integer.parseInt(arr[2]),Integer.parseInt(arr[3]),Integer.parseInt(arr[4]),Integer.parseInt(arr[5])
	    				 ,Integer.parseInt(arr[6]),Integer.parseInt(arr[7]));
	    	  A.getAbilities().add(a1);
	    	  A.getAbilities().add(a2);
	    	  A.getAbilities().add(a3);	   	  
	    	  availableChampions.add(A);  
	    	 }
	    	 else if (arr[0].equals("V")) 
	    	 {
	    	  Villain V = new Villain (arr[1],Integer.parseInt(arr[2]),Integer.parseInt(arr[3]),Integer.parseInt(arr[4]),Integer.parseInt(arr[5])
	    				 ,Integer.parseInt(arr[6]),Integer.parseInt(arr[7]));	 
	    	  V.getAbilities().add(a1);
	    	  V.getAbilities().add(a2);
	    	  V.getAbilities().add(a3);	   	  
	    	  availableChampions.add(V); 
	    	 }    
	         x = br.readLine();	        	    	       	      		 
	     }
		  
		  
		

	
}

	 
	 
	 
	 
	 
	 // new methods in M2
	 
	 // collection of  helper methods used here and outside(in other classes) ( not required in M2)
     public static boolean doesEffectExist(ArrayList<Effect> list,String EffectName) {
			for(int i = 0 ; i < list.size() ; i++)
			{
				if(list.get(i).getName().equals(EffectName))
					return true;
			    i++;
			}
			return false;
		}
     public static int calcDistance(Damageable d1, Damageable d2)// used when casting ability
     {
     	
     	Point P1 = d1.getLocation();
     	Point P2 = d2.getLocation();
     	int x1 = P1.x;
     	int y1 = P1.y;
     	int x2 = P2.x;
     	int y2 = P2.y;
     	return Math.abs(x2 - x1) + Math.abs(y2 - y1);
     	         
     }
	 public Player getCurrentPlayer() {
		 Champion c = (Champion) turnOrder.peekMin();
		 ArrayList<Champion> Team1 = firstPlayer.getTeam();
		 ArrayList<Champion> Team2 = secondPlayer.getTeam();
		 
		 for(int i = 0 ; i < Team1.size() ; i++)
			 if(Team1.get(i) == c)
				 return firstPlayer;
		 for(int i = 0 ; i < Team2.size() ; i++)
			 if(Team2.get(i) == c)
				 return secondPlayer;
		 return null; // will never happen 
	 }
     public Player getWaitingPlayer() {
    	 if(this.getCurrentPlayer() == firstPlayer)
    		 return secondPlayer;
    	 return firstPlayer;
     }
     public boolean boardLocationIsvalidAndEmpty(int x , int y) {
    	 if(x < 0 || y < 0 || x > 4 || y > 4) 
    		 return false ; 		 		 
    	 if(board[x][y] != null) 
    		 return false ;	
    	 return true ;
     }
	 public void checkIfDeadAndActAccordingly(Damageable d) { // gaveOver not checked yet(if will ever check it here in this method, not in a GameAction methods
   	  if(d.getCurrentHP() != 0)// IMPORTANT : will need also to check if condition = KNOCKOUT if removed the line " c.setCurrentHP(0) " from VILLIAN useLeaderAbility
   		  return;	
      if(d instanceof Cover)
  			  board[((Cover)d).getLocation().x][((Cover)d).getLocation().y] = null;
  	  else 
  		  {
  			  Champion c = (Champion) d;
  			  board[c.getLocation().x][c.getLocation().y] = null;
  			  c.setCondition(Condition.KNOCKEDOUT);// don't know if it adds something new, but just in case! 
  			  ArrayList<Champion> attackedTeam = this.getWaitingPlayer().getTeam();
  			  attackedTeam.remove(c);
		      
  			  // if Team of c became Empty,then end the game ( how to end the game ?)..(maybe by throwing an exception, and catcher of it will display : gameover!)
  		      if(attackedTeam.isEmpty()) // ( how to end the game ?), will ask gameRoom 
  		      {		    	  
  		    	  System.out.println("gameOver! winning player is :" + this.getCurrentPlayer().getName() );
  		      }
  		    	  
  		   
  		      
  		  }
   	
   }
	 public ArrayList<Damageable> getAllDamageablesInGivenRange(Direction direction, int range) // used in both (attake) and (cast Ability) methods  
	 {  
		 int vertical_movement = 0;
		 int horizontal_movement = 0;	
		 
		 switch(direction) 
		 {
		 case UP : vertical_movement = 1; break ;
		 case DOWN : vertical_movement = -1; break ;
		 case RIGHT : horizontal_movement = 1; break ;
		 case LEFT : horizontal_movement = -1; break ;		 
		 }
		 
		 int x_start = getCurrentChampion().getLocation().x;
		 int y_start = getCurrentChampion().getLocation().y;
		 ArrayList<Damageable> targets = new ArrayList<>(); 
		 for(int i = 0 ; i < range ; i++)
		 {		 
			 int x_current = x_start + vertical_movement;
			 int y_current = y_start + horizontal_movement;
			 
			 boolean outOfBoard = x_current > 4 || y_current > 4 || x_current < 0 || y_current < 0;
			 if(outOfBoard)
				 break;
			 Object CurrentLocation = board[x_current][y_current];
			 if( CurrentLocation != null)
			    targets.add((Damageable)(CurrentLocation));
			 x_start = x_current; // done so that the x_current be updated in the start of the loop
			 y_start = y_current; // done so that the y_current be updated in the start of the loop
		 }
		 return targets; 

		 
	 }

	 public void landNormalAttake(Champion attaker, Damageable target) // used in attake method 
	 {
		 int DamageAmount = attaker.getAttackDamage();
		 if(target instanceof Champion  )
			 if (attaker.getClass() != target.getClass())
				 DamageAmount *= 1.5;				 
		 target.setCurrentHP(target.getCurrentHP() - DamageAmount);
		 checkIfDeadAndActAccordingly(target);
		 attaker.setCurrentActionPoints(attaker.getCurrentActionPoints() - 2);
		 
	 }
	 	 
	 // required Methods in M2
	 
 	 public Champion getCurrentChampion() {
	
		while(! turnOrder.isEmpty() ) // gets the first non INCATIVE champion in the queue // it is already done in  end turn (darwish)
		{
			Champion currentChamp = (Champion) turnOrder.peekMin();
			Condition cond = currentChamp.getCondition();
	        if(cond == Condition.KNOCKEDOUT || cond == Condition.INACTIVE)   
	        	turnOrder.remove();
	        else
	        	return currentChamp;	 
	   }		
		return null; // i think it will never happen, because the endTurn method will handle this case
	 }
	 public Player checkGameOver() {
         		  
		 boolean all_Team1_is_dead = true;
		 for(int i = 0 ; i < firstPlayer.getTeam().size() ; i++)
		 {			 
			 Condition cond = firstPlayer.getTeam().get(i).getCondition();
			 if(cond != Condition.KNOCKEDOUT)
				 all_Team1_is_dead = false;			 
		 }
		 if(all_Team1_is_dead)
			 return secondPlayer;
		 
		 boolean all_Team2_is_dead = true;
		 for(int i = 0 ; i < secondPlayer.getTeam().size() ; i++)
		 {			 
			 Condition cond = secondPlayer.getTeam().get(i).getCondition();
			 if(cond != Condition.KNOCKEDOUT)
				 all_Team2_is_dead = false;			 
		 }
		 if(all_Team2_is_dead)
			 return firstPlayer;
		 
		 return null;
		 
      		 
	 }
     public void move(Direction d) throws NotEnoughResourcesException , UnallowedMovementException
	 {	 
		 Champion c = getCurrentChampion() ;	 
		 if(c.getCondition() == Condition.ROOTED) 
			 throw new UnallowedMovementException();
	     if(c.getCurrentActionPoints() < 1)
	    	 throw new NotEnoughResourcesException();
	     
	     int old_x = c.getLocation().x;
	     int old_y = c.getLocation().y;
		 int new_x = old_x ;
		 int new_y = old_y ; 
		 
		 switch(d) 
		 {
		 case UP : new_x ++ ; break;
		 case DOWN : new_x -- ; break;
		 case RIGHT : new_y ++ ; break;
		 case LEFT : new_y -- ; break;		 
		 }
		 	 
		 if( ! boardLocationIsvalidAndEmpty(new_x, new_y) )
		     throw new UnallowedMovementException();
		 
		 board[old_x][old_y] = null;
		 board[new_x][new_y] = c ;  
		 c.setLocation(new Point(new_x,new_y));
		 c.setCurrentActionPoints(c.getCurrentActionPoints() - 1);	 
	 }
	 public void attack(Direction direction) throws NotEnoughResourcesException, ChampionDisarmedException {
		 Champion attaker =  getCurrentChampion();
		 if(doesEffectExist(attaker.getAppliedEffects(), "Disarm"))
			 throw new ChampionDisarmedException();
		 if(attaker.getCurrentActionPoints() < 2)
			 throw new NotEnoughResourcesException();
         ArrayList<Damageable> all_tangets_in_range = getAllDamageablesInGivenRange(direction, attaker.getAttackRange());
         
         if(all_tangets_in_range.isEmpty() ) 
         {
        	 attaker.setCurrentActionPoints(attaker.getCurrentActionPoints() - 2);
        	 return;
         }
         
         Damageable firstTarget = all_tangets_in_range.get(0);
         
         if(firstTarget instanceof Cover)
         {
        	 landNormalAttake(attaker,firstTarget);
        	 return;
         }
         Champion targetChampion = (Champion) firstTarget;
         if(doesEffectExist(targetChampion.getAppliedEffects(), "Shield"))
         {
        	 for(int i = 0 ; i < targetChampion.getAppliedEffects().size() ; i++)// removes the shield from appliedEffects
        		 if(targetChampion.getAppliedEffects().get(i).getName() == "Shield")
        		 {
        			 targetChampion.getAppliedEffects().get(i).remove(targetChampion);
        	         break;
        		 }
        	 attaker.setCurrentActionPoints(attaker.getCurrentActionPoints() - 2);
        	 return;
         }
         if(doesEffectExist(targetChampion.getAppliedEffects(), "Dodge"))
         {
        	 Random rd = new Random();
        	 boolean doDodge = rd.nextBoolean();
        	 if( doDodge )
        	 {
        		 attaker.setCurrentActionPoints(attaker.getCurrentActionPoints() - 2);
        		 return;       		 
        	 }
            	 
         }
         landNormalAttake(attaker,firstTarget);
         		 		 
	 }
	 
	 public void castAbility(Ability a) // use getFirstDamageableInRange 
	 {
		 
	 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
	 }
	// helper method used in castAbility (Ability, Direction)
	 public void checkAbilityResources (Champion c , Ability a) throws NotEnoughResourcesException 
	 {
		 if (c.getCurrentActionPoints()<a.getRequiredActionPoints())
		 {
			 throw new NotEnoughResourcesException ();
		 }
		 if (c.getMana()<a.getManaCost())
		 {
			 throw new NotEnoughResourcesException ();
		 }
		 
		
	 }
	// helper method used in castAbility (Ability, Direction)
	 public void apply_ability_cost (Champion c,Ability a)
	 {
		 c.setCurrentActionPoints(c.getCurrentActionPoints()-a.getRequiredActionPoints());
		 c.setMana(c.getMana()-a.getManaCost());
		 a.setCurrentCooldown(a.getBaseCooldown());
		 
	 }
	// helper method used in castAbility (Ability, Direction) 
	 public boolean CheckFriendly_or_opponent (Champion c)
	 {
      /* there is predefined method in arrayList called : contains(Object o), I think it is easier(hosain),
       * I think the below code works
      
	  Champion current  = this.getCurrentChampion();	 
	  boolean both_in_team1 = firstPlayer.getTeam().contains(c) && firstPlayer.getTeam().contains(current);
	  boolean both_in_team2 = secondPlayer.getTeam().contains(c) && secondPlayer.getTeam().contains(current);
	  if(both_in_team1 || both_in_team2)
		  return true;
	  return false;
	  
	  */
		 for (int i=0 ;i<firstPlayer.getTeam().size();i++)
		 {
			 if (this.getCurrentChampion().equals(firstPlayer.getTeam().get(i)))
			 {
				 for (int j=0;j<firstPlayer.getTeam().size();j++)
				 {
					 if (c.equals(firstPlayer.getTeam().get(i)))
					 {
						 return true;
					 }
				 }
				 return false;
			 }
		 }
		 for (int i=0 ;i<secondPlayer.getTeam().size();i++)
		 {
			 if (this.getCurrentChampion().equals(secondPlayer.getTeam().get(i)))
			 {
				 for (int j=0;j<secondPlayer.getTeam().size();j++)
				 {
					 if (c.equals(secondPlayer.getTeam().get(i)))
					 {
						 return true;
					 }
				 }
				 return false;
			 }
		 }
		 
		 return false;
		 
		 
	 }
	
	// helper method used in castAbility (Ability, Direction) 
	 public void check_directionvalid (Direction d) throws InvalidTargetException
	 {
		 int x = this.getCurrentChampion().getLocation().y;
		 int y = this.getCurrentChampion().getLocation().x;
		 if (d== Direction.RIGHT && x>3)
		 {
			 throw new InvalidTargetException();
		 }
		 if (d == Direction.LEFT&& x<1)
		 {
			 throw new InvalidTargetException();
		 }
		 if (d == Direction.UP && y>3)
		 {
			 throw new InvalidTargetException();
		 }
		 if (d == Direction.DOWN && y<1)
		 {
			 throw new InvalidTargetException();
		 }
	 }

	 public void castAbility1(Ability a, Direction d) throws NotEnoughResourcesException, AbilityUseException, CloneNotSupportedException, InvalidTargetException
	 {
		 checkAbilityResources(this.getCurrentChampion(), a);
		 check_directionvalid(d);
		 
			ArrayList<Damageable> c= getAllDamageablesInGivenRange(d, a.getCastRange()) ;

			ArrayList<Damageable> targets = new ArrayList<>();
			
		
			 for (int i=0;i<c.size();i++)
				 {
				 if (a instanceof DamagingAbility )
				 {
					 if (c.get(i) instanceof Cover || !CheckFriendly_or_opponent((Champion) c.get(i)))
					 {
						  
						 targets.add(c.get(i));
					 }
					 
				 }
				 else if  (a instanceof HealingAbility)
				 {
					if (c.get(i) instanceof Champion &&CheckFriendly_or_opponent((Champion) c.get(i)))
					{
						targets.add(c.get(i));
					}
				 }
				 else 
				 {
					 if (c.get(i) instanceof Champion && ((CrowdControlAbility)a).getEffect().getType()==EffectType.BUFF&&CheckFriendly_or_opponent((Champion) c.get(i)))
					 {
						 targets.add(c.get(i));
					 }
					 else 
					 {
						 if (c.get(i) instanceof Champion && ((CrowdControlAbility)a).getEffect().getType()==EffectType.DEBUFF&&!CheckFriendly_or_opponent((Champion) c.get(i)))

						 targets.add(c.get(i));
					 }
					 
				 }
			 }
			 apply_ability_cost(getCurrentChampion(), a);
			 if (targets.size()==0)
			 {
				 throw new InvalidTargetException();
			 }
			 a.execute(targets);
			
		 
		 
	 }
	 
	   
		  
	 public void castAbility(Ability a, int x, int y) throws NotEnoughResourcesException
	 {	 
		 checkAbilityResources(this.getCurrentChampion(), a);
		 
		 
		 
		 
		 
		 
		 
		 
		
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
	 }
	 
	 public void useLeaderAbility() //  don't know what to loop over, team or turnOrder
	 {	  
	 }
	  
	 public void endTurn()
	 {	 	
		 turnOrder.remove() ;
 		
		 
		 if(turnOrder.isEmpty()) {
			 prepareChampionTurns();
			 // shouldn't be a return statement here ? (hosain)
		//no , to prepare the first champion in the new turn 
		 }
		 
		//while loop to skip inactive and knocked out champions 
while(((Champion)turnOrder.peekMin()).getCondition().equals(Condition.KNOCKEDOUT) || ((Champion)turnOrder.peekMin()).getCondition().equals(Condition.INACTIVE))
	turnOrder.remove() ;
		 
Champion x =getCurrentChampion() ;
x.setCurrentActionPoints(x.getMaxActionPointsPerTurn()); //maxmize current action points for the new turn

for(int i = 0 ; i< x.getAbilities().size();i++) {// decrease the cooldown by 1 .see ms1 definition of current cool down
	
	
	x.getAbilities().get(i).setCurrentCooldown(x.getAbilities().get(i).getCurrentCooldown()-1);
	
}
for(int i=0 ;i<x.getAppliedEffects().size();i++) {// decrease the duration by 1 .see ms1 definition of current duration
	if(x.getAppliedEffects().get(i).getDuration()<1) {
		x.getAppliedEffects().get(i).remove(getCurrentChampion());
		x.getAppliedEffects().remove(i) ;
	}
	else
	x.getAppliedEffects().get(i).setDuration(x.getAppliedEffects().get(i).getDuration()-1);
	
	
}
		 
		 
		 
		 
		 
		 
		 
		 
		 
	 }
	 
	 private void prepareChampionTurns()
	 {	 
		
		
		if(checkGameOver()!=null) return ; ///i think this could work
		
		
	    for(int i =0 ; i<firstPlayer.getTeam().size();i++)    
			 if(!firstPlayer.getTeam().get(i).getCondition().equals(Condition.KNOCKEDOUT))
			          turnOrder.insert(firstPlayer.getTeam().get(i));	 
	    
	    for(int i =0 ; i<secondPlayer.getTeam().size();i++) 	    
			 if(!secondPlayer.getTeam().get(i).getCondition().equals(Condition.KNOCKEDOUT))
			          turnOrder.insert(secondPlayer.getTeam().get(i));		

	 }

}
