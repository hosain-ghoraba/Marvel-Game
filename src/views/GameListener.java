package views;

import model.abilities.Ability;
import model.world.Direction;

public interface GameListener {
	
	void onMove(Direction d);
	void onAttake(Direction d);
	void onUseLeaderAbility();
	void onCastAbility(Ability a , Direction d);
	void onCastAbility(Ability a, int x ,int y);
	void onCastAbility(Ability a);	
	void onEndTurn();

}
