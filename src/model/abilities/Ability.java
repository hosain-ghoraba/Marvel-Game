package model.abilities;

import java.util.ArrayList;

import model.world.Damageable;

public abstract class Ability implements Cloneable {

	
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


/// new in M2

public abstract void execute(ArrayList<Damageable> targets) throws CloneNotSupportedException;

public Object clone() throws CloneNotSupportedException {
	return super.clone();
}

// new in M3
public String toString () {
	
	String result = "";
	result += "Name : " + name + '\n';
	result += "Type : " + getClass().getSimpleName() + '\n';
	result += "area of effect : " + castArea + '\n';
	result += "cast range : " + castRange + '\n';
	result += "mana cost : " + manaCost + '\n';
	result += "required action points : " + requiredActionPoints + '\n';
	result += "base cooldown : " + baseCooldown + '\n';
	result += "current cooldown : " + currentCooldown + '\n';
    
	return result;
}

public static void main(String[] args) {
	
	Ability a = new DamagingAbility("na", 0, 0, 0, AreaOfEffect.DIRECTIONAL, 0, 0);
	System.out.println(a);
	
}


}
