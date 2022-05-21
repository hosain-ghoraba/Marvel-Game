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
		 String line= br.readLine();
		 while (line!=null)
	    {
		 
	     String arr[]= line.split(",");
	   
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
	     
	     line=br.readLine();
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
			  String line = br.readLine();	  
			  while (line != null)
		      {  
		         String arr[]= line.split(",");	
		         	    	 
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
		         line = br.readLine();	        	    	       	      		 
		     }
			  
			  
			
	
		
	}


	// new methods in M2
	 
	// collection of  helper methods used here and outside(in other classes) ( not required in M2)
	
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
	public boolean isFriend (Champion c)
	{
	
	 Champion current  = this.getCurrentChampion();	 
	 boolean both_in_team1 = firstPlayer.getTeam().contains(c) && firstPlayer.getTeam().contains(current);
	 boolean both_in_team2 = secondPlayer.getTeam().contains(c) && secondPlayer.getTeam().contains(current);
	 if(both_in_team1 || both_in_team2)
		  return true;
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
	public boolean boardLocationIsvalidAndEmpty(int x , int y) {
	    	
		 if(x > 4 || x < 0 || y > 4 || y < 0) 
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
		     Champion dead_champ = (Champion) d;
		     dead_champ.setCondition(Condition.KNOCKEDOUT);
		     firstPlayer.getTeam().remove(dead_champ);// does nothing if dead_champ is not is firstPlayer team
		     secondPlayer.getTeam().remove(dead_champ);// does nothing if dead_champ is not is secondPlayer team
		      
		     // done removing dead_champ from his team, lets remove it from turnOrder : 
		     
		      ArrayList <Champion> alive_Champions = new ArrayList<Champion>();
		      while(!turnOrder.isEmpty()) // : IMPORTANT : it may change the current champ after the dead_champ is removed !		    	          
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
			 
			 boolean outOfBoard = x_current > 4 || x_current < 0 || y_current > 4 || y_current < 0;
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
		 if(champ.getCondition()!=Condition.INACTIVE)
		    champ.setCurrentActionPoints(champ.getMaxActionPointsPerTurn()); //maxmize current action points for the new turn
		 
		 for(int i = 0 ; i < champ.getAbilities().size() ; i++)// decrease the cooldown by 1 .see ms1 definition of current cool down  	     	 	  	 	
			 champ.getAbilities().get(i).setCurrentCooldown(champ.getAbilities().get(i).getCurrentCooldown() - 1);    	 	
		 
		 
		 for(int i = 0 ; i < champ.getAppliedEffects().size() ; i++)// decrease the duration by 1 .see ms1 definition of current duration 
		 {
			 Effect currentEffect = champ.getAppliedEffects().get(i);
			 currentEffect.setDuration(currentEffect.getDuration() - 1); 
			 if(currentEffect.getDuration() < 1)
			 {
				 currentEffect.remove(champ);
				 champ.getAppliedEffects().remove(currentEffect);
				 i -- ; // this is horribly important ! because the size of appliedEffects will decrease 
			 }
		 }
		 
	}
	public static boolean doesEffectExist(ArrayList<Effect> list,String EffectName) {
				for(int i = 0 ; i < list.size() ; i++)			
					if(list.get(i).getName().equals(EffectName))
						return true;			
				return false;
			}
	public Effect get_effect_With_Given_Name_With_the_least_Duration(ArrayList<Effect> list,String InputName) {
	    	 
	        int listSize = list.size(); 		
	    	PriorityQueue all_Effects_with_Input_Name = new PriorityQueue(listSize); 
	 		for(int i = 0 ; i < listSize ; i++)
	 		{
	 			Effect currentEffect = list.get(i);
	 			if(InputName.equals(currentEffect.getName()) )
	 				all_Effects_with_Input_Name.insert(currentEffect);	
	 		}
	 		return (Effect) all_Effects_with_Input_Name.peekMin();
	
	 	}    
	public void land_normal_attack(Champion attaker, Damageable target) // used in attack method 
		 {
			 int DamageAmount = attaker.getAttackDamage();
			 if(target instanceof Champion)
				 if (attaker.getClass() != target.getClass())
					 DamageAmount *= 1.5;				 
			 target.setCurrentHP(target.getCurrentHP() - DamageAmount);
			 checkIfDeadAndActAccordingly(target);
			 attaker.setCurrentActionPoints(attaker.getCurrentActionPoints() - 2);
			 
		 }

	public void checkAbilityResources (Champion c , Ability a) throws NotEnoughResourcesException, AbilityUseException 
		 {
			 if(a.getCurrentCooldown() != 0) 			
				 throw new AbilityUseException();		
			 
			 if (c.getCurrentActionPoints() < a.getRequiredActionPoints())
	 			 throw new NotEnoughResourcesException ();
			 
			 if (c.getMana() < a.getManaCost())
				 throw new NotEnoughResourcesException ();	
		 }
	public void apply_ability_cost (Champion c,Ability a)
		 {
			 c.setCurrentActionPoints(c.getCurrentActionPoints() - a.getRequiredActionPoints());
			 c.setMana(c.getMana() - a.getManaCost());
			 a.setCurrentCooldown(a.getBaseCooldown());
			 
		 }
	public boolean checkPointExistAndOccupied (int x,int y) {
			 
			 if(x > 4 || x < 0 || y > 4 || y < 0)		 
				 return false;	
			 if(board[x][y] == null)
				 return false;
			 return true;
			 
		 }
	public ArrayList<Point> getPoints_Surround (int x, int y)
		 {
			 ArrayList<Point> result= new ArrayList<Point>();
			 
			 Point a1 = new Point(x+1, y);
			 if (checkPointExistAndOccupied(a1.x, a1.y))
				 result.add(a1);
			 
			 Point a2 = new Point(x-1,y);
			 if (checkPointExistAndOccupied(a2.x, a2.y))
				 result.add(a2);
			 
			 Point a3 = new Point(x,y+1);
			 if (checkPointExistAndOccupied(a3.x, a3.y))
				 result.add(a3);
			 
			 Point a4 = new Point(x,y-1);
			 if (checkPointExistAndOccupied(a4.x, a4.y))
				 result.add(a4);
			 
			 Point a5 = new Point(x+1,y+1);
			 if (checkPointExistAndOccupied(a5.x, a5.y))
				 result.add(a5);
			 
			 Point a6 = new Point(x-1,y-1);
			 if (checkPointExistAndOccupied(a6.x, a6.y))
				 result.add(a6);
			 
			 Point a7 = new Point(x+1,y-1);
			 if (checkPointExistAndOccupied(a7.x, a7.y))
				 result.add(a7);
			 
			 Point a8 = new Point(x-1,y+1);
			 if (checkPointExistAndOccupied(a8.x, a8.y))
				 result.add(a8);
			 
			 return result;
			 
		 }
		
    // required Methods in M2
	 
 	public Champion getCurrentChampion() {	     
		  		 
 		 return (Champion)turnOrder.peekMin(); 
	   		
	 }
	public Player checkGameOver() {
		 
		 if(firstPlayer.getTeam().isEmpty())
		 {
			 System.out.println("Gave Over ! Player " + secondPlayer.getName() + " is the winner ! ");
			 return secondPlayer;
		 }
		 if(secondPlayer.getTeam().isEmpty())
		 {
			 System.out.println("Gave Over ! Player " + firstPlayer.getName() + " is the winner ! ");
			 return firstPlayer;
		 }
		 return null;
		 
	 }
    public void move(Direction d) throws NotEnoughResourcesException , UnallowedMovementException
	 {	 
		 Champion currentChamp = getCurrentChampion() ;	 
		 if(currentChamp.getCondition() == Condition.ROOTED) 
			 throw new UnallowedMovementException();
	     if(currentChamp.getCurrentActionPoints() < 1)
	    	 throw new NotEnoughResourcesException();
	     
	     int old_x = currentChamp.getLocation().x;
	     int old_y = currentChamp.getLocation().y;
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
		 board[new_x][new_y] = currentChamp ;  
		 currentChamp.setLocation(new Point(new_x,new_y));
		 currentChamp.setCurrentActionPoints(currentChamp.getCurrentActionPoints() - 1);	 
	 }
	public void attack(Direction direction) throws NotEnoughResourcesException, ChampionDisarmedException {
		 
		 Champion attaker =  getCurrentChampion();
		 if(doesEffectExist(attaker.getAppliedEffects(), "Disarm"))
			 throw new ChampionDisarmedException();
		 if(attaker.getCurrentActionPoints() < 2)
			 throw new NotEnoughResourcesException();
		 
         ArrayList<Damageable> all_targets_in_range = getAllDamageablesInGivenRange(direction, attaker.getAttackRange()); 
         
         Damageable first_valid_target = null;
         for (Damageable target : all_targets_in_range) // done to get the first valid_target(cover or Enemy) in range         	  
              if(target instanceof Cover || ! isFriend((Champion) target))    
              {
            	  first_valid_target = target;
            	  break;
              }
        	
         if( first_valid_target == null) 
         {
        	 attaker.setCurrentActionPoints(attaker.getCurrentActionPoints() - 2);
        	 return;
         }
                
         if(first_valid_target instanceof Cover)
         	{
        	 land_normal_attack(attaker,first_valid_target);
        	 return;
         	}
         Champion targetChampion = (Champion) first_valid_target;
         if(doesEffectExist(targetChampion.getAppliedEffects(), "Shield"))
         	{
        	 Effect to_be_removed = get_effect_With_Given_Name_With_the_least_Duration(targetChampion.getAppliedEffects(), "Shield");
             to_be_removed.remove(targetChampion);
        	 targetChampion.getAppliedEffects().remove(to_be_removed);       		         	 
        	 attaker.setCurrentActionPoints(attaker.getCurrentActionPoints() - 2);
        	 return;
         	}
         if(doesEffectExist(targetChampion.getAppliedEffects(), "Dodge"))
         	{
        	 Random rd = new Random();
        	 boolean dodge_succeed = rd.nextBoolean();
        	 if( dodge_succeed )
        	 {
        		 attaker.setCurrentActionPoints(attaker.getCurrentActionPoints() - 2);
        		 return;       		 
        	 }
            	 
         	}
         land_normal_attack(attaker,first_valid_target);
         		 		 
	 }
    public void castAbility(Ability casted_ability) throws CloneNotSupportedException, AbilityUseException, NotEnoughResourcesException
	 {
    	 Champion attaker = getCurrentChampion();
		 if(doesEffectExist(attaker.getAppliedEffects(),"Silence"))
			 throw new AbilityUseException();	
		 
		 checkAbilityResources(attaker, casted_ability);
		 
		 ArrayList<Damageable> targets = new ArrayList<Damageable>();
		 
		 if(casted_ability.getCastArea() == AreaOfEffect.SELFTARGET) 
		 {
			 if(casted_ability instanceof DamagingAbility || (casted_ability instanceof CrowdControlAbility && ((CrowdControlAbility)casted_ability).getEffect().getType()==EffectType.DEBUFF)) 
				 throw new AbilityUseException();
	         targets.add(attaker);

		 }
		 else if(casted_ability.getCastArea() == AreaOfEffect.TEAMTARGET) 
		 {
			 
				 if(casted_ability instanceof DamagingAbility || (casted_ability instanceof CrowdControlAbility && ((CrowdControlAbility)casted_ability).getEffect().getType()==EffectType.DEBUFF)) 		 
				 {
		                 ArrayList<Champion> enemyTeam = getWaitingPlayer().getTeam();
						 for(Champion current_enemy : enemyTeam)           							
							if(casted_ability.getCastRange() >= calcDistance(current_enemy, attaker))
								 if(doesEffectExist(current_enemy.getAppliedEffects(), "Shield") )
								 {
									 Effect to_be_removed = get_effect_With_Given_Name_With_the_least_Duration(current_enemy.getAppliedEffects(), "Shield");
									 to_be_removed.remove(current_enemy);
									 current_enemy.getAppliedEffects().remove(to_be_removed);
								 }
								 else 
							         targets.add(current_enemy);			    
                 }
				 else // it is healing ability or buff crowedControlAbility				
					 for(Champion friend : getCurrentPlayer().getTeam() )					 
						 if(casted_ability.getCastRange() >= calcDistance(friend, attaker) )
							 targets.add(friend);				 
		  }


		 else // it is SURROUND 
	     {
				 ArrayList<Point> occupied_surround_Points = getPoints_Surround(attaker.getLocation().x, attaker.getLocation().y);// contains only OCCUPIED SURROUD POINTS (MODIFIED)
		         
				 for (Point point : occupied_surround_Points )
				 {
				         Damageable current_target = (Damageable)board[point.x][point.y];
						 if ( casted_ability instanceof DamagingAbility )
						 {
					 
							 if (current_target instanceof Cover)				 						  
								 targets.add(current_target);
							 else
							   {
								 Champion current_target_champ = (Champion) current_target ; 				 
								 if(!isFriend(current_target_champ)) 		
									 
										if( doesEffectExist(current_target_champ.getAppliedEffects(), "Shield") )
										{
											Effect to_be_removed =  get_effect_With_Given_Name_With_the_least_Duration(current_target_champ.getAppliedEffects(), "Shield");
										    to_be_removed.remove(current_target_champ);
										    current_target_champ.getAppliedEffects().remove(to_be_removed);
										}
										else 
										    targets.add(current_target_champ);
				
							   }						 
							 	 
						 }  	 
						 else if  (casted_ability instanceof HealingAbility)		
						 {
							     if (current_target instanceof Champion && isFriend((Champion) current_target))				
								       targets.add(current_target);
						 }
						 else // it is crowdControlAbility
						 {	
							boolean b1 =  current_target instanceof Champion && isFriend( (Champion) current_target) && ((CrowdControlAbility)casted_ability).getEffect().getType() == EffectType.BUFF ;			 				    
							boolean b2 =  current_target instanceof Champion && !isFriend((Champion) current_target) && ((CrowdControlAbility)casted_ability).getEffect().getType() == EffectType.DEBUFF ; 
						    if(b1 || b2)						 
								 targets.add(current_target);
						 }
		
				   }	 // for loop bracket
	     }// surround case bracket
		 casted_ability.execute(targets);
		 for(Damageable target : targets)
			 checkIfDeadAndActAccordingly(target);			 
		 apply_ability_cost(attaker, casted_ability);
	 }
    public void castAbility(Ability casted_ability, Direction d) throws NotEnoughResourcesException, AbilityUseException, CloneNotSupportedException
	 {
         Champion attaker = getCurrentChampion();
		 if(doesEffectExist(attaker.getAppliedEffects(),"Silence"))
			 throw new AbilityUseException();			 
		 checkAbilityResources(attaker, casted_ability);
		 
		 ArrayList<Damageable> damageablesInRange = getAllDamageablesInGivenRange(d, casted_ability.getCastRange()) ;
		 ArrayList<Damageable> targets = new ArrayList<Damageable>(); // passed targets to execute method
		
		
		 for (int i = 0 ; i < damageablesInRange.size() ; i++) {
			 
			 Damageable current_target = damageablesInRange.get(i) ;
			 if ( casted_ability instanceof DamagingAbility )
			 
				 if (current_target instanceof Cover)				 						  
					 targets.add(current_target);
				 else
				   {						
					 Champion current_target_champ = (Champion) current_target; 
						
					 if(!isFriend(current_target_champ)) 
							 if( ! doesEffectExist(current_target_champ.getAppliedEffects(), "Shield") )
									targets.add(current_target_champ);
							 else // just remove that shield from applied effects
								{
								Effect to_be_removed =  get_effect_With_Given_Name_With_the_least_Duration(current_target_champ.getAppliedEffects(), "Shield");
							    to_be_removed.remove(current_target_champ);
							    current_target_champ.getAppliedEffects().remove(to_be_removed);
								}
							 
				   }
			 
			 else if  (casted_ability instanceof HealingAbility)			
			 {
				if (current_target instanceof Champion && isFriend((Champion) current_target))				
					targets.add(current_target);
			 }	
			 
			 else // it is crowdControlAbility
			 {
				boolean b1 = current_target instanceof Champion && isFriend( (Champion) current_target) && ((CrowdControlAbility)casted_ability).getEffect().getType() == EffectType.BUFF ;			 				    
				boolean b2 = current_target instanceof Champion && !isFriend((Champion) current_target) && ((CrowdControlAbility)casted_ability).getEffect().getType() == EffectType.DEBUFF ; 
			    if(b1 || b2)						 
					 targets.add(current_target);									 
			 }
		 }
		 
		 casted_ability.execute(targets);
         for(Damageable target : targets)
        	 checkIfDeadAndActAccordingly(target);
		 apply_ability_cost(attaker, casted_ability);
	 
	 }
    public void castAbility(Ability casted_ability, int x, int y) throws NotEnoughResourcesException, AbilityUseException, InvalidTargetException, CloneNotSupportedException
	 {	  
    	Champion attaker = getCurrentChampion();
   	
    	if(doesEffectExist(attaker.getAppliedEffects(),"Silence"))
		      throw new AbilityUseException();
	  
		 checkAbilityResources(attaker, casted_ability);
		 
		 if(! checkPointExistAndOccupied(x, y))
			  throw new InvalidTargetException() ; // or throw AbilityUseException ? 
               
		 Damageable target = (Damageable) board[x][y] ;
		 
		 if( casted_ability.getCastRange() < calcDistance(attaker, target))
			 throw new AbilityUseException () ;
		 
	     ArrayList <Damageable> targets = new ArrayList<Damageable>();
			 if ( casted_ability instanceof DamagingAbility )
				 
					 if(target instanceof Cover)
						 targets.add(target);
					 else
					 {
						  Champion current_target_champ = (Champion) target;
	                      if( isFriend(current_target_champ) )
	                	      throw new InvalidTargetException();
	                  
						  if( !doesEffectExist(current_target_champ.getAppliedEffects(), "Shield") )						
	                     		targets.add(target);						
						  else // just remove that shield from applied effects
							{
								Effect to_be_removed =  get_effect_With_Given_Name_With_the_least_Duration(current_target_champ.getAppliedEffects(), "Shield");
							    to_be_removed.remove(current_target_champ);
							    current_target_champ.getAppliedEffects().remove(to_be_removed);
							}						
					 }
				 
			 else if  (casted_ability instanceof HealingAbility)								
					 if (target instanceof Champion && isFriend((Champion) target))				
						targets.add(target);
					 else
					    throw new InvalidTargetException() ;
 
			 else // it is crowdControlAbility
			 {				 
				 if(target instanceof Cover ) 
					throw new InvalidTargetException() ;
				 
				 Champion current_target_champ = (Champion) target;
 
				 boolean b1 =  isFriend( current_target_champ) && ((CrowdControlAbility)casted_ability).getEffect().getType() == EffectType.BUFF ;			 				    
				 boolean b2 =  !isFriend( current_target_champ) && ((CrowdControlAbility)casted_ability).getEffect().getType() == EffectType.DEBUFF ; 
			     if(b1 || b2)						 
				 	 targets.add(target);									 
			     else 
			     	throw new InvalidTargetException() ;
			 }
			 if(! targets.isEmpty())
			 { 
		     casted_ability.execute(targets);
			 checkIfDeadAndActAccordingly(targets.get(0));
			 }
			 apply_ability_cost(attaker, casted_ability);
	 }
    public void useLeaderAbility() throws LeaderNotCurrentException , LeaderAbilityAlreadyUsedException 

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
			 for(Champion friend : friendlyTeam)
                 targets.add(friend);
			 		 
		 else if(attaker instanceof Villain)		 
			 for(Champion enemy : enemyTeam) 
			     targets.add(enemy);
			 			 		 
		 else
		 {
			 for(Champion friend : friendlyTeam)			 
				if( friend != getCurrentPlayer().getLeader() )
					 targets.add(friend);			 
			 
			 for(Champion enemy : enemyTeam)			 
					if( enemy != getWaitingPlayer().getLeader() )
						 targets.add(enemy);		 			 
		 }
		 
		 attaker.useLeaderAbility(targets);
		 
		 if(getCurrentPlayer() == firstPlayer)
			 firstLeaderAbilityUsed = true;
		 else 
			 secondLeaderAbilityUsed = true;
		 
		 for(Champion target : targets)
			 checkIfDeadAndActAccordingly(target);
	 }
    public void endTurn() 
	 {	 	
		 turnOrder.remove() ;			
		 
		 if(turnOrder.isEmpty())		 			 
			 prepareChampionTurns();
				 
		//while loop to prepare Champions_needed_To_Update_their_Timers and skip knockedout ones
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
        for(Champion champ : Champions_needed_To_Update_their_Timers) 
        	 updateTimers(champ);
        
        if(turnOrder.isEmpty())        
        	prepareChampionTurns();
	               
	 }
    private void prepareChampionTurns()
	 {	 	 	
	    for(Champion champ : firstPlayer.getTeam())    
	    	    turnOrder.insert(champ);
	    
	    for(Champion champ : secondPlayer.getTeam())  
	    	    turnOrder.insert(champ);
     }
 
 
 
 }