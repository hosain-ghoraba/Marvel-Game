package model.world;

import java.awt.Point;

public interface Damageable {

	Point getlocation();
	 int getCurrentHP();
	 void setCurrentHP(int hp);
}
