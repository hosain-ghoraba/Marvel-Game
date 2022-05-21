package model.effects;

import engine.Game;
import model.world.Champion;
import model.world.Condition;

public class Stun extends Effect {

	public Stun( int duration) {
		super("Stun", duration, EffectType.DEBUFF);
	}
    		
	public void apply(Champion c) 
   {				
		c.setCondition(Condition.INACTIVE);
   }
	
	
	public void remove(Champion c) 
	{ 
		
		if(Game.doesEffectExist(c.getAppliedEffects(), "Stun"))
				return;
		if(Game.doesEffectExist(c.getAppliedEffects(), "Root"))
			{
				c.setCondition( Condition.ROOTED);
			    return;
			}		
		c.setCondition(Condition.ACTIVE);
	}

	
}
