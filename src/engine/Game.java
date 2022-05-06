package engine;
import model.world.*;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import model.effects.*;

import model.abilities.Ability;
import model.abilities.AreaOfEffect;
import model.abilities.CrowdControlAbility;
import model.abilities.DamagingAbility;
import model.abilities.HealingAbility;
import model.effects.Disarm;
import model.effects.Effect;
import model.world.Champion;

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
}





