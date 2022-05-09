package model.effects;

import model.world.Champion;

public class Shock extends Effect {

	public Shock(int duration) {
		super("Shock", duration, EffectType.DEBUFF);
	
	}

	@Override
	public void apply(Champion c) {
		// TODO Auto-generated method stub
		c.getAppliedEffects().add(this);
		c.setSpeed((int)(c.getSpeed()*0.9));
        c.setAttackDamage((int)(c.getAttackDamage()*0.9));
        c.setMaxActionPointsPerTurn(c.getMaxActionPointsPerTurn()-1);
        c.setCurrentActionPoints(c.getCurrentActionPoints()-1);
	}

	@Override
	public void remove(Champion c) {
		// TODO Auto-generated method stub
		c.getAppliedEffects().remove(this);
		c.setSpeed((int)(c.getSpeed()/0.9));
		c.setAttackDamage((int)(c.getAttackDamage()/0.9));	
		c.setMaxActionPointsPerTurn(c.getMaxActionPointsPerTurn()+1);
		c.setCurrentActionPoints(c.getCurrentActionPoints()+1);
	}

}
