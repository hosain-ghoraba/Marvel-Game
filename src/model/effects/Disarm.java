package model.effects;

import java.util.ArrayList;

import model.abilities.*;
import model.world.Champion;


public class Disarm extends Effect  {
    
	DamagingAbility punch; 
	
	public Disarm( int duration) {
		super("Disarm", duration, EffectType.DEBUFF);
	}

	@Override
	public void apply(Champion c)   {
		c.getAppliedEffects().add(this);
		// 1 : cann't use normal attaks : throw ChampionDisarmedException if attack Method(defined in engine) is called while the champion is under disarm effect
		// 2 : gain single target ability called punch :
		punch = new DamagingAbility("Punch",0,1,1,AreaOfEffect.SINGLETARGET ,1,50);
		c.getAbilities().add(punch);
		
	}
   public void remove(Champion c) {
	  c.getAppliedEffects().remove(this);
	  boolean removed = c.getAbilities().remove(punch);
	  if(! removed ) // just done for the stupid test cases :)...they may insert damaging ability without corrosponding disarm effect, so the reference of the removed ability will not be removed
		  for(int i = 0 ; i < c.getAbilities().size() ; i++)
		  {
			  Ability current = (Ability)c.getAbilities().get(i);
			  if(current.getName().equals("Punch"))
				  c.getAbilities().remove(current);
		  }
	 		    
   }
}
