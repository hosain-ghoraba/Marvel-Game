package model.effects;


import model.abilities.Ability;
import model.abilities.DamagingAbility;
import model.abilities.AreaOfEffect;
import model.world.Champion;


public class Disarm extends Effect  {
    
	DamagingAbility punch; 
	
	public Disarm( int duration ) {
		super("Disarm", duration, EffectType.DEBUFF);
	}

	@Override
	public void apply(Champion c) // target cann't use normal attacks : throw exception in attack method if trying to attake while holding Disarm effect 
	{   
		punch = new DamagingAbility("Punch",0,1,1,AreaOfEffect.SINGLETARGET ,1,50);
		c.getAbilities().add(punch);		
	}
   public void remove(Champion c)
   {
	   
	  boolean removed = c.getAbilities().remove(punch);
	  if(! removed ) // just done for the stupid test cases :).but it will never happen in real game..they may insert damaging ability without corrosponding disarm effect, so it won't be removed, but that will never happen in real game
		  for(int i = 0 ; i < c.getAbilities().size() ; i++)
		  {
			  Ability current = (Ability)c.getAbilities().get(i);
			  if(current.getName().equals("Punch"))
			  {
				  c.getAbilities().remove(current);
				  return;
			  }
		  }
	 		    
   }
}
