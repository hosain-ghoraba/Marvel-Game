package model.effects;

import model.world.Champion;

public abstract class Effect implements Cloneable,Comparable {
	 	
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
	public int compareTo(Object o) { // done to help in get_effect_With_Given_Name_With_the_least_Duration in game class
		Effect other = (Effect) o;
		if(other.getDuration() > this.getDuration())
			return -1;
		if (other.getDuration() < this.getDuration())
			return 1;
		return 0;
		
		
	}
	
	public abstract void apply(Champion c) throws CloneNotSupportedException ; 	
	public abstract void remove(Champion c); 
	
public String toString() {
	
   return  name + " , duration : " + duration + '\n';
}

}
