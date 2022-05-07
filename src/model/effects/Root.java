package model.effects;

import model.world.Champion;

public class Root extends Effect {

	public Root( int duration) {
		super("Root", duration, EffectType.DEBUFF);
	}

	@Override
	public void apply(Champion c) throws CloneNotSupportedException {
		// nothing to be done actually, 
		// just throw exception in move method if tried to move while rooted
		
	}
	// no need to override remove method here

}
