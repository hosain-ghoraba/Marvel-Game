package model.effects;

import model.abilities.*;
import model.world.Champion;


public class Disarm extends Effect  {
    
	
	public Disarm( int duration) {
		super("Disarm", duration, EffectType.DEBUFF);
	}

	@Override
	public void apply(Champion c) throws CloneNotSupportedException  {
		beforeApply = (Champion) c.clone();
		// 1 : cann't use normal attaks : throw ChampionDisarmedException if attack Method(defined in engine) is called while the champion is under disarm effect
		// 2 : gain single target ability called punch :
		Ability punch = new DamagingAbility("Punch",0,1,1,AreaOfEffect.SINGLETARGET ,1,50);
		c.getAbilities().add(punch);
		// finally, add new Disarm Effect to appliedEffects :
		Effect e = new Disarm(); // what is the duration ?
		c.getAppliedEffects().add(e);
		
	}
   // no need to override remove method here  
}
