package model.effects;

import model.abilities.Ability;
import model.abilities.DamagingAbility;
import model.abilities.HealingAbility;
import model.world.Champion;


public class PowerUp extends Effect {

	public PowerUp(int duration) {
		super("PowerUp", duration, EffectType.BUFF);
		
	}

	@Override
	public void apply(Champion c) throws CloneNotSupportedException {
		beforeApply = (Champion) c.clone();
		for(int i = 0 ; i < c.getAbilities().size() ; i++)
		{
			Ability current = (Ability) c.getAbilities().get(i);
			if(current instanceof DamagingAbility)
			{
			  int damage = ((DamagingAbility) current).getDamageAmount();
			  int result = (int)(damage * 1.2);
			  ((DamagingAbility) current).setDamageAmount(result);
			}
			else if(current instanceof HealingAbility)
			{
				int heal = ((HealingAbility) current).getHealAmount();
				int result = (int)(heal * 1.2);
				((HealingAbility) current).setHealAmount(result);
			
			}
		}
		
	}
	
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	
    // no need to override remove method here
}
