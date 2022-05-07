package model.effects;

import model.world.Champion;

public class Embrace extends Effect {

	public Embrace(int duration) {
		super("Embrace", duration,EffectType.BUFF );
		
	}

	@Override
	public void apply(Champion c)  {	
		c.setCurrentHP((int)(c.getCurrentHP() + 0.2 * c.getMaxHP())); // should I do this? or round to the closest integer?
		c.setMana((int)(c.getMana() * 1.2));// should I do this or round?
	    c.setSpeed((int)(c.getSpeed() * 1.2));// should I do this or round?
	    c.setAttackDamage((int)(c.getAttackDamage() * 1.2));// should I do this or round?
	
	    Effect e = new Embrace(); // what is the duration ?
		c.getAppliedEffects().add(e);	
	}

	@Override
	public void remove(Champion c) {
		
	}
	
	

}
