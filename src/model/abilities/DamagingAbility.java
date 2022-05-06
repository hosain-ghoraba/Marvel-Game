package model.abilities;

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



}
