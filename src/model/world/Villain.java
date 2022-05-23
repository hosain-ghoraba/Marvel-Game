package model.world;

import java.util.ArrayList;


public class Villain extends Champion {
public Villain (String name, int maxHP, int mana, int maxActions, int speed, int attackRange,int attackDamage) {
	 super(name,maxHP,  mana,maxActions, speed, attackRange,attackDamage);
}

public void useLeaderAbility(ArrayList<Champion> targets) {
	for (int i = 0 ; i < targets.size() ; i++)
	{
		Champion current_champ = targets.get(i);
		double hp_percentage = (current_champ.getCurrentHP() / current_champ.getMaxHP())*100;
		if (hp_percentage < 30)	
		{	
			current_champ.setCurrentHP(0);
			current_champ.setCondition(Condition.KNOCKEDOUT);
		}
	}
	
}
}
