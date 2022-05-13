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
//not completed(ahmad)..( I think it is completed ya ahmad (hosain) )...all targets all vaild as stated in M2, so they must be only champions, no covers at all

public void execute(ArrayList<Damageable> targets) throws CloneNotSupportedException {
	for (int i=0 ; i<targets.size() ; i++)	
	{
		Effect copy = (Effect) effect.clone();
        copy.apply( (Champion) (targets.get(i))  );	
        
	}
}



}
