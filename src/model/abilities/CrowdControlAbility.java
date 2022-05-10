package model.abilities;

import java.util.ArrayList;

import model.effects.Effect;
import model.world.Champion;
import model.world.Damageable;

public class CrowdControlAbility extends Ability{

private Effect effect;

public CrowdControlAbility(String name, int cost, int baseCoolDown, int castRange, AreaOfEffect area, int required,
		Effect effect) {
	super(name, cost, baseCoolDown, castRange, area, required);
	this.effect = effect;
}
///////// no setters in this class

///// getters
public Effect getEffect() {
	return effect;
}
///// end of getters


//Override the abstract method
//not completed
public void execute(ArrayList<Damageable> targets) {
	for (int i=0;i<targets.size();i++)
	{
		
		Damageable x=targets.get(i);
		if (x instanceof Champion)
		{
	    effect.apply((Champion)x);
		}
	}
	
	
}



}
