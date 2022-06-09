package model.world;

import java.awt.Point;
import java.util.Random;


public class Cover implements Damageable {
 
 
 private int currentHP;
 private Point location ;
	
 
 public Cover(int x, int y) {
	 this.location =new Point(x,y) ;
	 Random rand = new Random();
	 this.currentHP  =rand.nextInt(900) +100 ;
 }

//////////// setters
 
 public void setCurrentHP(int currentHP){  
	 if(currentHP < 0)
	 {
		 this.currentHP = 0;
		 return;
	 }
	 this.currentHP = currentHP;
 }
 
/////////// getters 
 public int getCurrentHP() {
		return currentHP;
	}
 public Point getLocation() {
		return location;
	}
/////////////////////////// end of getters	


	
}
