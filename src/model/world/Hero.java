package model.world;

import java.util.ArrayList;

import model.effects.Disarm;
import model.effects.Effect;
import model.effects.EffectType;
import model.effects.Embrace;


public class Hero extends Champion {

	
 public Hero(String name, int maxHP, int mana, int maxActions, int speed, int attackRange,int attackDamage) {
	 super(name,maxHP,  mana,maxActions, speed, attackRange,attackDamage);
 }


public void useLeaderAbility(ArrayList<Champion> targets) {
	for (int i = 0 ; i < targets.size() ; i++)
	{
		Champion currentChamp = targets.get(i);
	    ArrayList<Effect> curretAppliedEffects = currentChamp.getAppliedEffects();
		for (int j = 0 ; j < curretAppliedEffects.size() ; j++)
		{
		  Effect currentEffect = curretAppliedEffects.get(j);
		  if (currentEffect.getType() == EffectType.DEBUFF)	
		  {
			  currentEffect.remove(currentChamp);
			  j--; //(this is the new edit) :  horribly important ! ..because the upper line will decrease the size of currentAppliedEffects
		  }
		}
		 new Embrace(2).apply(currentChamp);
	}
	  
}
 

}
