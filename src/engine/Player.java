package engine;

import java.util.ArrayList;

import model.world.Champion;

public class Player {
	
private String name;
private Champion leader ;
private ArrayList<Champion> team ;
int haha = 4;
public Player (String name)
{
	this.name=name;
	team = new ArrayList<Champion>();
}
////////// setters
public void setLeader(Champion leader) {
	this.leader = leader;
}

///////////////////// getters
public String getName() {
	return name;
}
public Champion getLeader() {
	return leader;
}
public ArrayList<Champion> getTeam() {
	return team;
}
//////////////////////// end of getters

}
