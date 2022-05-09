package model.world;

import java.util.ArrayList;

import model.effects.Disarm;
import model.effects.Effect;
import model.effects.EffectType;

public class Hero extends Champion {

	
 public Hero(String name, int maxHP, int mana, int maxActions, int speed, int attackRange,int attackDamage) {
	 super(name,maxHP,  mana,maxActions, speed, attackRange,attackDamage);
 }

@Override
public void useLeaderAbility(ArrayList<Champion> targets) {
	for (int i=0;i<targets.size();i++)
	{
	    ArrayList<Effect> x =targets.get(i).getAppliedEffects();
		for (int j=0;j<x.size();j++)
		{
		  if (x.get(j).getType()== EffectType.DEBUFF)
		  {
			  x.remove(j);
			  
		  }
		}
		 x.add(new Disarm(2));
	}
	
}
 

}
