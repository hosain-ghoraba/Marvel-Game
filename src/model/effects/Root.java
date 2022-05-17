package model.effects;

import engine.Game;
import model.world.Champion;
import model.world.Condition;

public class Root extends Effect {

	public Root( int duration) {
		super("Root", duration, EffectType.DEBUFF);
	}

	@Override
	public void apply(Champion c)  {
		
        c.getAppliedEffects().add(this);
        if (c.getCondition() == Condition.INACTIVE)
             return;
        c.setCondition(Condition.ROOTED);
		//  throw exception in move method if tried to move while rooted
        
	}
	@Override
	public void remove(Champion c) {
		c.getAppliedEffects().remove(this);
		
		if (c.getCondition() == Condition.INACTIVE)        
	        	return;
		if( Game.doesEffectExist(c.getAppliedEffects(), "Root") )
 				return;
		
		c.setCondition(Condition.ACTIVE);
			

	}
	

}
