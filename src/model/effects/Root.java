package model.effects;

import engine.Game;
import model.world.Champion;
import model.world.Condition;

public class Root extends Effect {

	public Root( int duration) {
		super("Root", duration, EffectType.DEBUFF);
	}

	@Override
	public void apply(Champion c) 		//  throw exception in move method if tried to move while rooted
	{	
        if (c.getCondition() != Condition.INACTIVE)
        	 c.setCondition(Condition.ROOTED);               
	}
	@Override
	public void remove(Champion c) 
	{		
		if ( c.getCondition() != Condition.INACTIVE && ! Game.doesEffectExist(c.getAppliedEffects(), "Root") )       
	           c.setCondition(Condition.ACTIVE);			
	}
	

}
