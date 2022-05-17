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
		
     //   c.getAppliedEffects().add(this);
        if (c.getCondition() == Condition.INACTIVE)
             return;
        c.setCondition(Condition.ROOTED);
		//  throw exception in move method if tried to move while rooted
        
	}
	@Override
	public void remove(Champion c) {
		//c.getAppliedEffects().remove(this);
		System.out.println("was"+c.getCondition());
		   if (c.getCondition()==Condition.INACTIVE ||c.getCondition()==Condition.KNOCKEDOUT)
	        {
				System.out.println("is"+c.getCondition());
				return;
	        }
	       // else  {
	        	//there a failure in this code I can not figure it out this should for loop should handle it but it did not
	        	// failure name:remove logic Root (Yousry)
	        //	for (int i=0;i<c.getAppliedEffects().size();i++)
	        	//{
	        		//if (c.getAppliedEffects().get(i).getName().equals("Stun"))
	        		//{
	        			//c.setCondition(Condition.INACTIVE);
	        			//return;
	        		//}
	        //	}
	        	//for (int i=0;i<c.getAppliedEffects().size();i++)
	        	//{
	        		//if (c.getAppliedEffects().get(i).getName().equals("Root"))
	        		//{
	        			//c.setCondition(Condition.ROOTED);
	        		//	return;
	        		//}
	        	//}
	        	
	//	c.setCondition((Condition.ACTIVE));
	        
		if( Game.doesEffectExist(c.getAppliedEffects(), "Root") )
		{
			System.out.println("is Rooted"+c.getCondition());
				return;

		}
	c.setCondition(Condition.ACTIVE);
	System.out.println("finally: "+ c.getCondition());

			

	}
	

}
