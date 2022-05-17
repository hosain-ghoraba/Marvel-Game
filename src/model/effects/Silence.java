package model.effects;

import model.world.Champion;

public class Silence extends Effect {

	public Silence( int duration) {
		super("Silence", duration, EffectType.DEBUFF);
		
	}

	@Override
	public void apply(Champion c) {
	
	// Target cannot use abilities : throw exception in castAbility Method 
	//c.getAppliedEffects().add(this);
	c.setMaxActionPointsPerTurn(c.getMaxActionPointsPerTurn()+2);
	c.setCurrentActionPoints(c.getCurrentActionPoints()+2);
	}

	@Override
	public void remove(Champion c) {
		// TODO Auto-generated method stub
	//	c.getAppliedEffects().remove(this);

		c.setMaxActionPointsPerTurn(c.getMaxActionPointsPerTurn()-2);
		c.setCurrentActionPoints(c.getCurrentActionPoints()-2);
	}

}
