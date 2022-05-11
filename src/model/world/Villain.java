package model.world;

import java.util.ArrayList;

import engine.SomeStaticMethods;

public class Villain extends Champion {
public Villain (String name, int maxHP, int mana, int maxActions, int speed, int attackRange,int attackDamage) {
	 super(name,maxHP,  mana,maxActions, speed, attackRange,attackDamage);
}
// not sure about lost of percision in hp_percentage

public void useLeaderAbility(ArrayList<Champion> targets) {
	for (int i=0;i<targets.size();i++)
	{
		Champion c = targets.get(i);
		double hp_percentage = (c.getCurrentHP() / c.getMaxHP())*100;
		if (hp_percentage<30)
		{
			c.setCurrentHP(0); // don't know if they consider it logically wrong, maybe don't do it, to differentiate between a champion who died by the leader ability and another who died due to taking damage(setting life = 0)
			c.setCondition(Condition.KNOCKEDOUT);
		}
	}
	
}
}
