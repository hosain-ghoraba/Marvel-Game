package model.effects;

import java.util.Random;

import model.world.Champion;

public class Dodge extends Effect {

	public Dodge( int duration) {
		super("Dodge", duration, EffectType.BUFF);
		
	}
	@Override
	public void apply(Champion c)  {
		c.getAppliedEffects().add(this);
		//  50% chance to dodge : done in attake method 
		c.setSpeed((int) (c.getSpeed() * 1.05)); 
	}

	@Override
	public void remove(Champion c) {
		c.getAppliedEffects().remove(this);
		c.setSpeed((int) (c.getSpeed() * 0.95));
	}
}
