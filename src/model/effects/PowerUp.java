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
	public void apply(Champion c)  {
		c.getAppliedEffects().add(this);
		
		for(int i = 0 ; i < c.getAbilities().size() ; i++)
		{
			Ability current =  c.getAbilities().get(i);
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

	@Override
	public void remove(Champion c) {
		c.getAppliedEffects().remove(this);
		
		for(int i = 0 ; i < c.getAbilities().size() ; i++)
		{
			Ability current = (Ability) c.getAbilities().get(i);
			if(current instanceof DamagingAbility)
			{
			  int damage = ((DamagingAbility) current).getDamageAmount();
			  int result = (int)(damage * 0.8);
			  ((DamagingAbility) current).setDamageAmount(result);
			}
			else if(current instanceof HealingAbility)
			{
				int heal = ((HealingAbility) current).getHealAmount();
				int result = (int)(heal * 0.8);
				((HealingAbility) current).setHealAmount(result);
			
			}
		}
		
	}
	
}
