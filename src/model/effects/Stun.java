package model.effects;

import engine.Game;
import model.world.Champion;
import model.world.Condition;

public class Stun extends Effect {

	public Stun( int duration) {
		super("Stun", duration, EffectType.DEBUFF);
	}
    
	
	
	
	public void apply(Champion c)  {
		// TODO Auto-generated method stub
		
		c.getAppliedEffects().add(this);
		c.setCondition(Condition.INACTIVE);
		// not allowed to play there turn for a while : throws exception in another method

	}
	
	
	public void remove(Champion c) { // DARWISH !! Leave this Method as is PLS! the order of these 2 lines is very Fatal ( see setCondition() Method in Champion Class to understand why)
		c.getAppliedEffects().remove(this);
		
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
