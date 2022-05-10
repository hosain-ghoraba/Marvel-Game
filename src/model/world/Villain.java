package model.world;

import java.util.ArrayList;

public class Villain extends Champion {
public Villain (String name, int maxHP, int mana, int maxActions, int speed, int attackRange,int attackDamage) {
	 super(name,maxHP,  mana,maxActions, speed, attackRange,attackDamage);
}
// not sure about lost of percision in hp_percentage
@Override
public void useLeaderAbility(ArrayList<Champion> targets) {
	for (int i=0;i<targets.size();i++)
	{
		double hp_percentage = (targets.get(i).getCurrentHP()/targets.get(i).getMaxHP())*100;
		if (hp_percentage<30)
		{
			targets.get(i).setCondition(Condition.KNOCKEDOUT);
		    // should also eliminate the Champ,so call eliminiteChamp from game(done know how! we don't have game reference)!			
		}
	}
	
}
}
