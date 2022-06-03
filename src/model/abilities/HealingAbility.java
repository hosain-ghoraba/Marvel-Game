package model.abilities;

import java.util.ArrayList;

import model.world.Damageable;

public class HealingAbility extends Ability {
	
private int healAmount;

public HealingAbility(String name, int cost, int baseCoolDown, int castRange, AreaOfEffect area, int required,
		int healAmount) {
	super(name, cost, baseCoolDown, castRange, area, required);
	this.healAmount = healAmount;
}
////// setters
public void setHealAmount(int healAmount) {
	this.healAmount = healAmount;
}

/////// getters
public int getHealAmount() {
	return healAmount;
}
////// end of getters
//Override the abstract method
public void execute(ArrayList<Damageable> targets) {
	for (int i = 0 ; i < targets.size() ; i++)
	{
		Damageable x=targets.get(i);
	    x.setCurrentHP(x.getCurrentHP() + healAmount);	
	}
	
}

public String toString() {
	return super.toString() + "heal amount : " + healAmount ;
}

}
