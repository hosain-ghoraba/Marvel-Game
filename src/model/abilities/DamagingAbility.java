package model.abilities;

import java.util.ArrayList;

import model.world.Champion;
import model.world.Damageable;

public class DamagingAbility extends Ability{

private int damageAmount;

public DamagingAbility(String name, int cost, int baseCoolDown, int castRange, AreaOfEffect area, int required,
		int damageAmount) {
	super(name, cost, baseCoolDown, castRange, area, required);
	this.damageAmount = damageAmount;
}
/////// setters
public void setDamageAmount(int damageAmount) {
	this.damageAmount = damageAmount;
}

//// getters
public int getDamageAmount() {
	return damageAmount;
}
//// end of getters

// Override abstract method

public void execute(ArrayList<Damageable> targets) {
	
	for (int i = 0 ; i < targets.size() ; i++)
	{
		Damageable x = targets.get(i);
	    x.setCurrentHP(x.getCurrentHP() - damageAmount);	    
	}
	
}
public String toString() {
	return super.toString() + "damage amount : " + damageAmount ;
}

}
