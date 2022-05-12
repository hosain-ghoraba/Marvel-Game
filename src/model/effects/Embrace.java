package model.effects;

import model.world.Champion;

public class Embrace extends Effect {

	public Embrace(int duration) {
		super("Embrace", duration,EffectType.BUFF );		
	}

	@Override
	public void apply(Champion c)  {	
		c.getAppliedEffects().add(this);
		
		c.setCurrentHP((int)(c.getCurrentHP() + 0.2 * c.getMaxHP()));
		c.setMana((int)(c.getMana() * 1.2));
	    c.setSpeed((int)(c.getSpeed() * 1.2));
	    c.setAttackDamage((int)(c.getAttackDamage() * 1.2));

	}

	@Override
	public void remove(Champion c) {
		c.getAppliedEffects().remove(this);
		
		c.setSpeed((int)(c.getSpeed() / 1.2));
	    c.setAttackDamage((int)(c.getAttackDamage() / 1.2));
		
	}
	
	

}
