package model.world;

import java.util.ArrayList;


public class Villain extends Champion {
public Villain (String name, int maxHP, int mana, int maxActions, int speed, int attackRange,int attackDamage) {
	 super(name,maxHP,  mana,maxActions, speed, attackRange,attackDamage);
}
// not sure about lost of percision in hp_percentage

public void useLeaderAbility(ArrayList<Champion> targets) {
	for (int i = 0 ; i<targets.size() ; i++)
	{
		Champion c = targets.get(i);
		double hp_percentage = (c.getCurrentHP() / c.getMaxHP())*100;
		if (hp_percentage<30)	
			c.setCurrentHP(0); 	
		// no need to set condition = knouckout because setCurrentHP make condition = knockedout  if currentHp reached 0		
	}
	
}
}
