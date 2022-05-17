package model.effects;

import model.world.Champion;

public class Shield  extends Effect{

	public Shield(int duration) {
		super("Shield", duration, EffectType.BUFF);
	}

	@Override
	public void apply(Champion c) {
		// Block the next attack or damaging ability cast on target.Once an attack or ability is blocked, 
		//the effect should be removed
	
		//c.getAppliedEffects().add(this);
		c.setSpeed((int)(c.getSpeed()*1.02));
	}
	

	@Override
	public void remove(Champion c) {
		// TODO Auto-generated method stub
	//	c.getAppliedEffects().remove(this);
		c.setSpeed((int)(c.getSpeed()/1.02));
	}

}
