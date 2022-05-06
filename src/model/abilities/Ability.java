package model.abilities;

public class Ability {
	
private	String name;	
private	int manaCost;
private	int baseCooldown;
private int currentCooldown;
private	int castRange;
private	int requiredActionPoints;
private	AreaOfEffect castArea;

public Ability(String name,int cost, int baseCoolDown, int castRange, AreaOfEffect area ,int required) {
	this.name = name;
	this.manaCost = cost;
	this.baseCooldown = baseCoolDown;
	this.currentCooldown = 0;
	this.castRange = castRange;
	this.requiredActionPoints = required;
	this.castArea = area;
}
////// setters

public void setCurrentCooldown(int currentCooldown) {
	if(currentCooldown < 0)
		this.currentCooldown = 0;
	else if (currentCooldown > baseCooldown)
		this.currentCooldown = baseCooldown;
	else
	    this.currentCooldown = currentCooldown;
}

//////// getters
public String getName() {
	return name;
}
public int getManaCost() {
	return manaCost;
}
public int getBaseCooldown() {
	return baseCooldown;
}
public int getCurrentCooldown() {
	return currentCooldown;
}
public int getCastRange() {
	return castRange;
}
public int getRequiredActionPoints() {
	return requiredActionPoints;
}
public AreaOfEffect getCastArea() {
	return castArea;
}
///////// end of getters






}
