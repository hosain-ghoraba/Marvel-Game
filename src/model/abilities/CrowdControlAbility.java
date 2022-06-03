package model.abilities;

import java.util.ArrayList;

import model.effects.Effect;
import model.effects.Root;
import model.world.Champion;
import model.world.Damageable;
import model.world.Direction;

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

public void execute(ArrayList<Damageable> targets) throws CloneNotSupportedException {
	for (int i = 0 ; i < targets.size() ; i++)	
	{
		Effect copy = (Effect) effect.clone();
        copy.apply( (Champion) (targets.get(i))  );	
        ((Champion) (targets.get(i))).getAppliedEffects().add(copy);
	}
}

public String toString() {
	return super.toString() + "effect : " + effect.toString() ;
}
public static void main(String[] args) {
	Effect e = new Root(4);
	Ability a = new CrowdControlAbility("coco", 0, 0, 0, AreaOfEffect.DIRECTIONAL, 0, e);
	System.out.println(a);
}

}
