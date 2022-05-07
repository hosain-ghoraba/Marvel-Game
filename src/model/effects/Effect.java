package model.effects;

import model.world.Champion;

public abstract class Effect implements Cloneable {
	
	// new instance variable : to be used in remove method
	Champion beforeApply ; 
	
	private String name;
	private int duration;
	private EffectType type;
	public Effect (String name, int duration, EffectType type)
	{
		this.name=name;
		this.duration=duration;
		this.type=type;
	}
///////// setters	
	public void setDuration(int duration) {
		this.duration = duration;
	}
	
////////// getters	
	public String getName() {
		return name;
	}
	public int getDuration() {
		return duration;
	}
	public EffectType getType() {
		return type;
	}
	
///////////// end of getters
	
	public Object clone() throws CloneNotSupportedException  {
		return super.clone();
	}
	
	public abstract void apply(Champion c) throws CloneNotSupportedException;	
	public void remove(Champion c) throws CloneNotSupportedException{
		c = (Champion)beforeApply.clone();
	}

	
	
	
	
	
	
	
	
}
