package model.effects;

import java.util.Random;

import model.world.Champion;

public class Dodge extends Effect {

	public Dodge( int duration) {
		super("Dodge", duration, EffectType.BUFF);
		
	}
	@Override
	public void apply(Champion c) throws CloneNotSupportedException {
		//  50% chance to dodge : done in attake method 
		//    increase speed by 5 % : 
		c.setSpeed((int) (c.getSpeed() * 1.05)); // should I do this(typecast to int) or should I round to the closeset integer?
		Effect e = new Dodge(); // what is the duration ?
		c.getAppliedEffects().add(e);
	
	}

	@Override
	public void remove(Champion c) {
		
	}
}
