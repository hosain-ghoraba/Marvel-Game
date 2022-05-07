package model.world;

import java.awt.Point;

import java.util.ArrayList;

import model.abilities.Ability;
import model.effects.Effect;



public class Champion implements Comparable,Damageable,Cloneable {
	private String name;
	private int maxHP ;
	private	int currentHP ;
	private	int mana; 
	private	int maxActionPointsPerTurn;
	private	int currentActionPoints ;
	private	int attackRange;
	private int attackDamage ;
	private	int speed;
	private  ArrayList<Ability> abilities;
	private ArrayList<Effect> appliedEffects;
	private Condition condition; 
	private Point location ;
	public Champion(String name, int maxHP, int mana, int maxActions, int speed, int attackRange,int attackDamage) {
		this.name=name;
		this.maxHP= maxHP;
		this.currentHP = maxHP;
		this.mana=mana ;
		this.maxActionPointsPerTurn =maxActions ;
		this.currentActionPoints = maxActions;
		this.speed=speed; 
		this.attackRange=attackRange;
		this.attackDamage=attackDamage ;
		abilities = new ArrayList<Ability>();
		appliedEffects = new ArrayList<Effect>();
		condition = Condition.ACTIVE ;
		
		
		
	}
	
////////////////// setters	
	
	public void setCurrentHP(int currentHP) {
		if(currentHP < 0)		
			this.currentHP = 0;
		else if(currentHP > maxHP)
			this.currentHP = maxHP;
		else 
			this.currentHP = currentHP;
	}
	public void setMana(int mana) {
		this.mana = mana;
	}
	public void setMaxActionPointsPerTurn(int maxActionPointsPerTurn) {
		this.maxActionPointsPerTurn = maxActionPointsPerTurn;
	}
	public void setCurrentActionPoints(int currentActionPoints) {
		if(currentActionPoints < 0)
			this.currentActionPoints = 0;
		else if (currentActionPoints > maxActionPointsPerTurn)
			this.currentActionPoints = maxActionPointsPerTurn;
		else
		    this.currentActionPoints = currentActionPoints;
	}
	public void setAttackDamage(int attackDamage) {
		this.attackDamage = attackDamage;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public void setCondition(Condition condition) {
		this.condition = condition;
	}
	public void setLocation(Point location) {
		this.location = location;
	}
	
///////////////////////////// getters

	
	public String getName() {
		return name;
	}
	public int getMaxHP() {
		return maxHP;
	}
	public int getCurrentHP() {
		return currentHP;
	}
	public int getMana() {
		return mana;
	}
	public int getMaxActionPointsPerTurn() {
		return maxActionPointsPerTurn;
	}
	public int getCurrentActionPoints() {
		return currentActionPoints;
	}
	public int getAttackRange() {
		return attackRange;
	}
	public int getAttackDamage() {
		return attackDamage;
	}
	public int getSpeed() {
		return speed;
	}
	public ArrayList<Ability> getAbilities() {
		return abilities;
	}
	public ArrayList<Effect> getAppliedEffects() {
		return appliedEffects;
	}
	public Condition getCondition() {
		return condition;
	}
	public Point getLocation() {
		return location;
	}
////////////////////////////////////////////////////////// end of getters

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		
	if (speed > ((Champion)o).speed ) return -1;
	if (speed < ((Champion)o).speed ) return 1;
	return 0 ;
	}
    
	
	public static ArrayList<Ability> deepCopyAbilitiesArrayList(ArrayList<Ability> l) throws CloneNotSupportedException  {
		ArrayList<Ability> result = new ArrayList<Ability>();		
		
		for(int i = 0 ; i < l.size() ; i++)
		{
			Ability out = l.get(i);
			Ability copy = (Ability)out.clone();
			result.add(copy);
		}
		return result;
		
	}
	public static ArrayList<Effect> deepCopyappliedEffectsArrayList(ArrayList<Effect> l) throws CloneNotSupportedException {
		ArrayList<Effect> result = new ArrayList<Effect>();		
		
		for(int i = 0 ; i < l.size() ; i++)
		{
			Effect out = l.get(i);
			Effect copy = (Effect)out.clone();
			result.add(copy);
		}
		return result;
		
	}
	// overriden to PUBLIC to be seen in all sub classes of Effect !
	public Object clone() throws CloneNotSupportedException {
		
		Champion result = (Champion) super.clone();
		
		result.abilities = deepCopyAbilitiesArrayList(result.abilities);
		result.appliedEffects = deepCopyappliedEffectsArrayList(result.appliedEffects);
		result.location = new Point(location.x,location.y);
		
		return result;
		
		
	}

	
}
