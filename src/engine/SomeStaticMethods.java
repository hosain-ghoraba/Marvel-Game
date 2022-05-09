package engine;

import java.util.ArrayList;

import model.abilities.Ability;
import model.effects.Effect;

public class SomeStaticMethods {
	        public static Ability removeLastAbilityWithInputName(ArrayList<Ability> l, String AbilityName) {	
			for(int i = l.size()-1 ; i >= 0 ; i--) {
				  Ability current = l.get(i);
				  if(current.getName().equals(AbilityName))
					  return l.remove(i);
				  i--;
					  
			  }
			  return null; // will never happen, just because it is obligatory to return something outside 
		                   // the for loop block
		    }
	  		public static Effect removeLastEffectWithInputName(ArrayList<Effect> l,String EffectName) { // not used in project yet
				for(int i = l.size()-1 ; i >= 0 ; i--)
				{
					Effect current = l.get(i);
					if(current.getName().equals(EffectName))							 
					    return  l.remove(i);			
					i--;
				}
				return null; // will never happen, just because it is obligatory to return something outside 
				             // the for loop block
				
			}
			public static ArrayList<Ability> deepCopyAbilitiesArrayList(ArrayList<Ability> l) throws CloneNotSupportedException  {
				ArrayList<Ability> result = new ArrayList<Ability>();		
				
				for(int i = 0 ; i < l.size() ; i++)
				{
					Ability out = l.get(i);
					Ability copy = (Ability)out.clone();
					result.add(copy);
				}
				return result;
				
			}
			public static ArrayList<Effect> deepCopyappliedEffectsArrayList(ArrayList<Effect> l) throws CloneNotSupportedException {
				ArrayList<Effect> result = new ArrayList<Effect>();		
				
				for(int i = 0 ; i < l.size() ; i++)
				{
					Effect out = l.get(i);
					Effect copy = (Effect)out.clone();
					result.add(copy);
				}
				return result;
				
			}
			public static boolean doesEffectExist(ArrayList<Effect> list,String EffectName) {
				for(int i = 0 ; i < list.size() ; i++)
				{
					if(list.get(i).getName().equals(EffectName))
						return true;
				    i++;
				}
				return false;
			}

}
