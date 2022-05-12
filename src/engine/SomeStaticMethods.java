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
	        public static void main(String[] args) {
				Object[][] x = new Object [5][5];
				System.out.println(x[3][4]);
			}
}
