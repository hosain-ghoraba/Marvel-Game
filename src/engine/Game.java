package engine;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;
//dd;
import exceptions.AbilityUseException;
import exceptions.ChampionDisarmedException;
import exceptions.InvalidTargetException;
import exceptions.LeaderAbilityAlreadyUsedException;
import exceptions.LeaderNotCurrentException;
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
     
     public Effect get_effect_With_Given_Name_With_the_least_Duration(ArrayList<Effect> list,String InputName) {
    	 
        int listSize = list.size(); 		
    	PriorityQueue all_Effect_with_Input_Name = new PriorityQueue(listSize); 
 		for(int i = 0 ; i < listSize ; i++)
 		{
 			Effect currentEffect = list.get(i);
 			if(InputName.equals(currentEffect.getName()) )
 				all_Effect_with_Input_Name.insert(currentEffect);	
 		}
 		return (Effect) all_Effect_with_Input_Name.peekMin();
 		
 		
 		
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
		 if(firstPlayer.getTeam().contains(getCurrentChampion()))
			 return firstPlayer;
		 return secondPlayer;
         

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
		 
	   	  if(d.getCurrentHP() > 0)
	   		  return;	

	      board[d.getLocation().x][d.getLocation().y] = null;  
	      if(d instanceof Champion) // must remove it from his team, and from turnOrder
	      {
		     Champion dead_champ = (Champion)d;
		     firstPlayer.getTeam().remove(dead_champ);// does nothing if dead_champ is not is firstPlayer team
		     secondPlayer.getTeam().remove(dead_champ);// does nothing if dead_champ is not is secondPlayer team
		      
		     // done removing dead_champ from his team, lets remove it from turnOrder : 
		     
		      ArrayList <Champion> alive_Champions = new ArrayList<Champion>();
		      while(true) // this loop causes extra 5 failures in castingAbility method,
		    	          // : IMPORTANT : it also may change the current champ after the dead_champ is removed !
		      {
		    	  Champion current = (Champion)turnOrder.remove();
		    	  if(current == dead_champ )
		    		  break;
		    	  alive_Champions.add(current);

		      }
		      while(! alive_Champions.isEmpty() ) // re_enter alive champions into turnOrder
		    	  turnOrder.insert(alive_Champions.remove(0));
		      
		      checkGameOver();
	      }
	      // must check if gameOver and terminate the game if the game is over, but don't know how to terminate the game yet.maybe in M3

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
		 ArrayList<Damageable> targets = new ArrayList<Damageable>(); 
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
	 public void updateTimers(Champion champ) {
		 
		 champ.setCurrentActionPoints(champ.getMaxActionPointsPerTurn()); //maxmize current action points for the new turn
		 
		 for(int i = 0 ; i < champ.getAbilities().size() ; i++)// decrease the cooldown by 1 .see ms1 definition of current cool down  	     	 	  	 	
			 champ.getAbilities().get(i).setCurrentCooldown(champ.getAbilities().get(i).getCurrentCooldown()-1);    	 	
		 
		 
		 for(int i = 0 ; i < champ.getAppliedEffects().size() ; i++)// decrease the duration by 1 .see ms1 definition of current duration 
		 {
			 Effect currentEffect = champ.getAppliedEffects().get(i);
			 currentEffect.setDuration(currentEffect.getDuration()-1); 
			 if(currentEffect.getDuration() < 1)
			 {
				 currentEffect.remove(champ);
				 i -- ; // this is horribly important ! 
			 }
		 }
		 
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
 		 return (Champion)turnOrder.peekMin(); 
	   		
	 }
	 public Player checkGameOver() {
		 
		 if(firstPlayer.getTeam().isEmpty())
			 return secondPlayer;
		 if(secondPlayer.getTeam().isEmpty())
			 return firstPlayer;
		 return null;

		 // NOTE : the below code must replace the upper code if dead champions will NOT be removed from their team ArrayList
		 
//		 boolean all_Team1_is_dead = true;
//		 for(int i = 0 ; i < firstPlayer.getTeam().size() ; i++)
//		 {			 
//			 Condition cond = firstPlayer.getTeam().get(i).getCondition();
//			 if(cond != Condition.KNOCKEDOUT)
//				 all_Team1_is_dead = false;			 
//		 }
//		 if(all_Team1_is_dead)
//			 return secondPlayer;
//		 
//		 boolean all_Team2_is_dead = true;
//		 for(int i = 0 ; i < secondPlayer.getTeam().size() ; i++)
//		 {			 
//			 Condition cond = secondPlayer.getTeam().get(i).getCondition();
//			 if(cond != Condition.KNOCKEDOUT)
//				 all_Team2_is_dead = false;			 
//		 }
//		 if(all_Team2_is_dead)
//			 return firstPlayer;
		 
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
         ArrayList<Damageable> all_targets_in_range = getAllDamageablesInGivenRange(direction, attaker.getAttackRange());
         
         if(all_targets_in_range.isEmpty() ) 
         {
        	 attaker.setCurrentActionPoints(attaker.getCurrentActionPoints() - 2);
        	 return;
         }
         
         Damageable firstTarget = all_targets_in_range.get(0);
         
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
	 
	 public void castAbility(Ability a) throws CloneNotSupportedException // use getFirstDamageableInRange 
, AbilityUseException, NotEnoughResourcesException
	 {

		 if(doesEffectExist(getCurrentChampion().getAppliedEffects(),"Silence"))
			 throw new AbilityUseException();		 
		 checkAbilityResources(getCurrentChampion(), a);
	
		 
		 
		 if(a.getCastArea()== AreaOfEffect.TEAMTARGET) {
		 if(a instanceof DamagingAbility || (a instanceof CrowdControlAbility && ((CrowdControlAbility)a).getEffect().getType()==EffectType.DEBUFF)) {
			 ArrayList<Damageable> z = new ArrayList<>() ;

			 if(firstPlayer.getTeam().contains(getCurrentChampion())) {
				 for(int i =0 ;i< secondPlayer.getTeam().size();i++) {
					Champion x =secondPlayer.getTeam().get(i);
					if( a.getCastRange()>=calcDistance(getCurrentChampion(), x))
					    z.add(x);
				 }
				 a.execute(z); 
				 
			 }
			 else {

				 for(int i =0 ;i< firstPlayer.getTeam().size();i++) {
					 Champion x =firstPlayer.getTeam().get(i);
						if( a.getCastRange()>=calcDistance(getCurrentChampion(), x))
						    z.add(x);
					 
				 }
				 
				 a.execute(z);
			 }
		 
			 for(int i = 0  ; i < z.size() ; i++) // to remove dead targets who died after casting the ability on them 
				 checkIfDeadAndActAccordingly(z.get(i));
			
			 apply_ability_cost(getCurrentChampion(), a);

		 }
		 else {
			 ArrayList<Damageable> z = new ArrayList<>() ;

			 if(firstPlayer.getTeam().contains(getCurrentChampion())) {
				 for(int i =0 ;i< firstPlayer.getTeam().size();i++) {
					 Champion x =firstPlayer.getTeam().get(i);
						if( a.getCastRange()>=calcDistance(getCurrentChampion(), x))
						    z.add(x);
				 }
				 a.execute(z); 
				 
			 }
			 else {

				 for(int i =0 ;i< secondPlayer.getTeam().size();i++) {
						Champion x =secondPlayer.getTeam().get(i);
						if( a.getCastRange()>=calcDistance(getCurrentChampion(), x))
						    z.add(x);				 }
				 
				 a.execute(z);
			 }
		 
			 for(int i = 0  ; i < z.size() ; i++) // to remove dead targets who died after casting the ability on them 
				 checkIfDeadAndActAccordingly(z.get(i));
			
			 apply_ability_cost(getCurrentChampion(), a);
		 }	 
	 
	 
	 
	 }
		 
	 if(a.getCastArea()== AreaOfEffect.SELFTARGET) {
		 if(a instanceof DamagingAbility || (a instanceof CrowdControlAbility && ((CrowdControlAbility)a).getEffect().getType()==EffectType.DEBUFF)) {
			 throw new AbilityUseException();
		 
		 }
	
		 else {
			 ArrayList<Damageable> z = new ArrayList<>() ;
              z.add(getCurrentChampion());
			 a.execute(z);
			 apply_ability_cost(getCurrentChampion(), a);
			 
		 }
	 
	 
	 
	 }
	 if(a.getCastArea()== AreaOfEffect.SURROUND) 
	 {
		 ArrayList<Point>surroundPoints= getPoints_Surround(this.getCurrentChampion().getLocation().x, this.getCurrentChampion().getLocation().y);
		 ArrayList<Damageable>targets=new ArrayList<Damageable>(); 

		 for (Point point:surroundPoints )
	     {
	    	 if (board[point.x][point.y]==null)
	    	 {
	    		 
	    	 }
	    		 
	    	 
	    	 else {
		 Damageable z =(Damageable)board[point.x][point.y]; 
		 if ( a instanceof DamagingAbility )
		 {
			
			 
			 if (z instanceof Cover)				 						  
				 targets.add(z);
			 else
			   {
					
				 
				 Champion current_target_champ = (Champion) z ; 
				 
				 if(!isFriend(current_target_champ)) {
				
				 
					if( ! doesEffectExist(current_target_champ.getAppliedEffects(), "Shield") )
						targets.add(current_target_champ);
					else // just remove that shield from applied effects
					{
						Effect to_be_removed =  get_effect_With_Given_Name_With_the_least_Duration(current_target_champ.getAppliedEffects(), "Shield");
					    to_be_removed.remove(current_target_champ);
					}
					
				 }
				

			   
			   }						 
			 	 
		 }
	    	 
		 else if  (a instanceof HealingAbility)			
		 {
			
			 if (z instanceof Champion && isFriend((Champion) z))				
				targets.add(z);
			 
				 
		 
		 }	
		 
		 else // it is crowdControlAbility
		 {
			 
			
			
		
			 
			 
			boolean b1 = z instanceof Champion && isFriend( (Champion) z) && ((CrowdControlAbility)a).getEffect().getType() == EffectType.BUFF ;			 				    
			boolean b2 = z instanceof Champion && !isFriend((Champion) z) && ((CrowdControlAbility)a).getEffect().getType() == EffectType.DEBUFF ; 
		    if(b1)						 
				 targets.add(z);									 
		 
		    else  if(b2) {
			
				 targets.add(z);}
		   
		 
		 
		 
		 
		 }
	    	 
	 
	    	 }
	    	 
	     }
		 a.execute(targets);
		for(Damageable z : targets)
		 checkIfDeadAndActAccordingly(z);
		 apply_ability_cost(getCurrentChampion(), a);
		 }
		 
		 
	 }
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
	 
	 public boolean checkpointexist (int x,int y)
	 {
		 if(x>4 || x<0 || y<0 || y>4)
		 {
			 return false;
			 
		 }
		 return true;
		 
	 }
	// helper method used in castAbility (Ability, Direction)
	 public ArrayList<Point> getPoints_Surround (int x, int y)
	 {
		 ArrayList<Point> result= new ArrayList<>();
		Point a1 = new Point(x+1,y);
		if (checkpointexist(a1.x, a1.y))
			result.add(a1);
		Point a2 = new Point(x-1,y);
		if (checkpointexist(a2.x, a2.y))
			result.add(a2);
		Point a3 = new Point(x,y-1);
		if (checkpointexist(a3.x, a3.y))
			result.add(a3);
		Point a4 = new Point(x+1,y-1);
		if (checkpointexist(a4.x, a4.y))
			result.add(a4);
		Point a5 = new Point(x-1,y-1);
		if (checkpointexist(a5.x, a5.y))
			result.add(a5);
		Point a6 = new Point(x,y+1);
		if (checkpointexist(a6.x, a6.y))
			result.add(a6);
		Point a7 = new Point(x+1,y+1);
		if (checkpointexist(a7.x, a7.y))
			result.add(a7);
		Point a8 = new Point(x-1,y+1);
		if (checkpointexist(a8.x, a8.y))
			result.add(a8);
		return result;
		
	 }
	 {
		 
	 }
	 public void checkAbilityResources (Champion c , Ability a) throws NotEnoughResourcesException, AbilityUseException 
	 {
		if(a.getCurrentCooldown()!=0) {
			
			throw new AbilityUseException();
		}
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
	 public boolean isFriend (Champion c)
	 {
      // there is predefined method in arrayList called : contains(Object o), I think it is easier(hosain),
      // I think the below code works
     
	  Champion current  = this.getCurrentChampion();	 
	  boolean both_in_team1 = firstPlayer.getTeam().contains(c) && firstPlayer.getTeam().contains(current);
	  boolean both_in_team2 = secondPlayer.getTeam().contains(c) && secondPlayer.getTeam().contains(current);
	  if(both_in_team1 || both_in_team2)
		  return true;
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

	 public void castAbility(Ability a, Direction d) throws NotEnoughResourcesException, AbilityUseException, CloneNotSupportedException
	 {
		 
		 // must check if attacker has Silence on it
		 // must check if defender has shield on it (in case of Damaging ability)..if yes, remove the the Shield Effect with least duration in appliedEffects
		 // must call checkIfDead on each member in targets at the end of the method
		 
		 if(doesEffectExist(getCurrentChampion().getAppliedEffects(),"Silence"))
			 throw new AbilityUseException();			 
		 checkAbilityResources(getCurrentChampion(), a);
		 
		 ArrayList<Damageable> damageablesInRange = getAllDamageablesInGivenRange(d, a.getCastRange()) ;
		 ArrayList<Damageable> targets = new ArrayList<Damageable>(); // passed targets to execute method
		
		
		 for (int i = 0 ; i < damageablesInRange.size() ; i++)
			 {
			 Damageable current_target = damageablesInRange.get(i) ;
			 if ( a instanceof DamagingAbility )
			 {
				 if (current_target instanceof Cover)				 						  
					 targets.add(current_target);
				 else
				   {
						
					 Champion current_target_champ = (Champion) current_target; 
						
					 if(!isFriend(current_target_champ)) {
					 if( ! doesEffectExist(current_target_champ.getAppliedEffects(), "Shield") )
							targets.add(current_target_champ);
						else // just remove that shield from applied effects
						{
							Effect to_be_removed =  get_effect_With_Given_Name_With_the_least_Duration(current_target_champ.getAppliedEffects(), "Shield");
						    to_be_removed.remove(current_target_champ);
						}
						
					
					}						 
				   }
			 }
			 else if  (a instanceof HealingAbility)			
			 {
				if (current_target instanceof Champion && isFriend((Champion) current_target))				
					targets.add(current_target);
			 }	
			 
			 else // it is crowdControlAbility
			 {
				boolean b1 = current_target instanceof Champion && isFriend( (Champion) current_target) && ((CrowdControlAbility)a).getEffect().getType() == EffectType.BUFF ;			 				    
				boolean b2 = current_target instanceof Champion && !isFriend((Champion) current_target) && ((CrowdControlAbility)a).getEffect().getType() == EffectType.DEBUFF ; 
			    if(b1 || b2)						 
					 targets.add(current_target);									 
			 }
		 }
		 
		 a.execute(targets);
		 for(int i = 0  ; i < targets.size() ; i++) // to remove dead targets who died after casting the ability on them 
			 checkIfDeadAndActAccordingly(targets.get(i));
		 apply_ability_cost(getCurrentChampion(), a);
	 
	 }
	 
	   
		  
	 public void castAbility(Ability a, int x, int y) throws NotEnoughResourcesException, AbilityUseException, InvalidTargetException, CloneNotSupportedException
	 {	  if(doesEffectExist(getCurrentChampion().getAppliedEffects(),"Silence"))
		       throw new AbilityUseException();
		 
	 
		  
		 checkAbilityResources(this.getCurrentChampion(), a);
		 if(x>4 || x<0 || y<0 || y>4)
		 {
			 throw new InvalidTargetException() ;
		 }
		 
		 if(boardLocationIsvalidAndEmpty(x, y)) {
			 if(a instanceof HealingAbility ) {
				 throw new InvalidTargetException() ;

			 }
			 apply_ability_cost(getCurrentChampion(), a);
            return ;
		 }

		 Damageable z = (Damageable)board[x][y] ;
		 if( a.getCastRange()< calcDistance(getCurrentChampion(), z))
			 throw new InvalidTargetException() ;		


		 ArrayList<Damageable> targets = new ArrayList<Damageable>(); // passed targets to execute method
		
		
	
			 if ( a instanceof DamagingAbility )
			 {
				
				 
				 if (z instanceof Cover)				 						  
					 targets.add(z);
				 else
				   {
						
					 
					 Champion current_target_champ = (Champion) z; 
					 if(current_target_champ.equals(getCurrentChampion())) {
							throw new InvalidTargetException() ;

					 }
					 
					 else if(!isFriend(current_target_champ)) {
					
					 
						if( ! doesEffectExist(current_target_champ.getAppliedEffects(), "Shield") )
							targets.add(current_target_champ);
						else // just remove that shield from applied effects
						{
							Effect to_be_removed =  get_effect_With_Given_Name_With_the_least_Duration(current_target_champ.getAppliedEffects(), "Shield");
						    to_be_removed.remove(current_target_champ);
						}
						
					 }
					 else 
					    	throw new InvalidTargetException() ;

				   
				   }						 
				 	 
			 }
			 else if  (a instanceof HealingAbility)			
			 {
				
				 if (z instanceof Champion && isFriend((Champion) z))				
					targets.add(z);
				 else
				    	throw new InvalidTargetException() ;
					 
			 
			 }	
			 
			 else // it is crowdControlAbility
			 {
				 
				 if(z instanceof Cover ) {
					throw new InvalidTargetException() ;
					
				}
				
			if(z instanceof Champion && ((Champion) z).equals(getCurrentChampion())  && ((CrowdControlAbility)a).getEffect().getType() == EffectType.DEBUFF) {
				throw new InvalidTargetException() ;
				
			}
				 
				 
				boolean b1 = z instanceof Champion && isFriend( (Champion) z) && ((CrowdControlAbility)a).getEffect().getType() == EffectType.BUFF ;			 				    
				boolean b2 = z instanceof Champion && !isFriend((Champion) z) && ((CrowdControlAbility)a).getEffect().getType() == EffectType.DEBUFF ; 
			    if(b1)						 
					 targets.add(z);									 
			 
			    else  if(b2) {
				
					 targets.add(z);}
			    else 
			    	throw new InvalidTargetException() ;

			 
			 
			 
			 
			 }
		 
		 
		 a.execute(targets);
			 checkIfDeadAndActAccordingly(z);
			 apply_ability_cost(getCurrentChampion(), a);
		 
		 
		 
		
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
	 }
	 

	 public void useLeaderAbility() throws LeaderNotCurrentException , LeaderAbilityAlreadyUsedException //  don't know what to loop over, team or turnOrder

	 {	  
		 Champion attaker = getCurrentChampion();
		 if( attaker != getCurrentPlayer().getLeader() )
		     throw new LeaderNotCurrentException();
		 
		 boolean b1 = firstLeaderAbilityUsed && firstPlayer == getCurrentPlayer() ;// checks if firstLeaderAbility is used and the firstPlayer is indeed the one who is playing now
		 boolean b2 = secondLeaderAbilityUsed && secondPlayer == getCurrentPlayer() ;// checks if secondLeaderAbility is used and the secondPlayer is indeed the one who is playing now
		 if( b1 || b2)    
			 throw new LeaderAbilityAlreadyUsedException();
		 ArrayList<Champion> targets = new ArrayList<Champion>();
		 
		 ArrayList<Champion> friendlyTeam = getCurrentPlayer().getTeam();
		 ArrayList<Champion> enemyTeam = getWaitingPlayer().getTeam();
		 
		 if(attaker instanceof Hero)
		 {
			
			 for(int i = 0 ; i < friendlyTeam.size() ; i++)
			 {
				Champion currentFriend = friendlyTeam.get(i); 
				 if(currentFriend.getCondition() != Condition.KNOCKEDOUT)
					 targets.add(currentFriend);
			 }

		 }
		 else if(attaker instanceof Villain)
		 {
			 for(int i = 0 ; i < enemyTeam.size() ; i++) // passes all alive enemies to targets
			 {
				 Champion current_Enemy = enemyTeam.get(i);
				 if(current_Enemy.getCondition() != Condition.KNOCKEDOUT)
					 targets.add(current_Enemy);
			 }
			 
		 }
		 else
		 {
			 for(int i = 0 ; i < friendlyTeam.size() ; i++)
			 {
				Champion currentFriend = friendlyTeam.get(i); 
				if(currentFriend.getCondition() != Condition.KNOCKEDOUT && currentFriend != getCurrentPlayer().getLeader() )
					 targets.add(currentFriend);
			 }
			 
			 for(int i = 0 ; i < enemyTeam.size() ; i++) // passes all alive enemies to targets
			 {
				 Champion current_Enemy = enemyTeam.get(i);
				 if(current_Enemy.getCondition() != Condition.KNOCKEDOUT && current_Enemy != getWaitingPlayer().getLeader())
					 targets.add(current_Enemy);
			 } 
			 
			 
		 }
		 
		 attaker.useLeaderAbility(targets);
		 
		 if(firstPlayer.getTeam().contains(getCurrentChampion()))
			 firstLeaderAbilityUsed = true;
		 else 
			 secondLeaderAbilityUsed = true;
		 
		 for(int i = 0 ; i < targets.size() ; i++)
			 checkIfDeadAndActAccordingly(targets.get(i));
	 }

	 public void endTurn() 
	 {	 	
		 turnOrder.remove() ;			
		 
		 if(turnOrder.isEmpty())		 			 
			 prepareChampionTurns();
				 
		//while loop to prepare Champions_needed_To_Update_their_Timers and skip knouckedout ones
		ArrayList<Champion> Champions_needed_To_Update_their_Timers = new ArrayList<Champion>();
        while(! turnOrder.isEmpty() )
        {
        	Champion peeked = (Champion)(turnOrder.peekMin());	
            if (peeked.getCondition() == Condition.INACTIVE)       	
        		Champions_needed_To_Update_their_Timers.add((Champion)turnOrder.remove()); 
        	else
        	   {
        		Champions_needed_To_Update_their_Timers.add(peeked);
        	    break;
        	   }        	
        }
        for(int i = 0 ; i < Champions_needed_To_Update_their_Timers.size() ; i++) 
        	updateTimers(Champions_needed_To_Update_their_Timers.get(i));
        
        if(turnOrder.isEmpty())        
        	prepareChampionTurns();
	               
	 }

	 private void prepareChampionTurns()
	 {	 	 
		if(checkGameOver()!=null)  ///i think this could work
		{
		     System.out.println("Gamed Over !");
		     return;
		}		
	    for(int i = 0 ; i < firstPlayer.getTeam().size() ; i++)    
			      turnOrder.insert(firstPlayer.getTeam().get(i));	 			  
	    for(int i = 0 ; i < secondPlayer.getTeam().size() ; i++)    
		          turnOrder.insert(secondPlayer.getTeam().get(i));		

	 }
}



