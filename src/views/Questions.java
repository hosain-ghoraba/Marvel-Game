package views;

import engine.Game;
import model.world.AntiHero;
import model.world.Champion;
import model.world.Condition;
import model.world.Damageable;
import model.world.Hero;
import model.world.Villain;

public class Questions {
  
	/* questions
    ERRORS : 1 - java.lang.IndexOutOfBoundsException: Index 0 out of bounds for length 0
    
	at java.base/jdk.internal.util.Preconditions.outOfBounds(Preconditions.java:64)
	at java.base/jdk.internal.util.Preconditions.outOfBoundsCheckIndex(Preconditions.java:70)
	at java.base/jdk.internal.util.Preconditions.checkIndex(Preconditions.java:266)
	at java.base/java.util.Objects.checkIndex(Objects.java:359)
	at java.base/java.util.ArrayList.get(ArrayList.java:427)
	at tests.M2PublicTests.testEndTurnUpdatesStunnedChampion(M2PublicTests.java:25007)
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.base/java.lang.reflect.Method.invoke(Method.java:568)
	at org.junit.runners.model.FrameworkMethod$1.runReflectiveCall(FrameworkMethod.java:59)
	at org.junit.internal.runners.model.ReflectiveCallable.run(ReflectiveCallable.java:12)
	at org.junit.runners.model.FrameworkMethod.invokeExplosively(FrameworkMethod.java:56)
	at org.junit.internal.runners.statements.InvokeMethod.evaluate(InvokeMethod.java:17)
	at org.junit.internal.runners.statements.FailOnTimeout$CallableStatement.call(FailOnTimeout.java:299)
	at org.junit.internal.runners.statements.FailOnTimeout$CallableStatement.call(FailOnTimeout.java:293)
	at java.base/java.util.concurrent.FutureTask.run(FutureTask.java:264)
	at java.base/java.lang.Thread.run(Thread.java:833)

	FAILURES : 
		
		1- apply shouldn't change appliedEffects ...how on earth !
		2- if INACTIVE and setting to Root, should remain INACTIVE (we should search for the causing effect,
				not the effect itself !)
		3- "should remove attacked champion from turnOrder if life reached zero after being attacked"...how to remove it? (speeds issue)		
    
    2 - must leader ability of Villain setLift to zero ? or only setCondition to KNOCKEDOUT ?
    
    3 - will a player team ever be empty ( will knockedout champions be removed from it, or will stay in 
       it but with knockedout condition ?
    
    4- must the useLeaderAbility in game determine passed Champions by looping over the players teams or the turnOrder ? 
    
    5- should endTurn method check also that the next champion in not KnockedOUt ? ( he might have just died).
       the Milestone requires only to check if the next champion is INACTIVE? shouldn't knoucked out also be checked?
    
    6 - VERY IMPORTANT !!!! if a champion died, I understand he should be immediately removed of the board,
       however, should he be immidiately removed from his player's team Array and from the turnOrder PQ?
       or this will happen in prepareChampionsTurns method ?
    7 - how to stop the game and return the winning player if a player got all his champinos died?
        must I checked gameOver after every action method in game ? and if gameOver, what to do ?
    
    8-  what to do in prepareChampions turn if one of teams was empty ? 
    
    9- must checkGameOver check if the team was empty ? or check if all its members are knockedout ?
    
    10- in endTurn method, if the current Champion was INACTIVE, shouldn't its timers also be updated ?
    
    11- set condition = knocked out in setCurrentHp if currentHp readed 0 ?
    
    12- IMPORTANT : should endTurn reconstruct the turnOrder? (because speeds might have changed)
    
    
 
 
       
        
    */
	
	
	
	
	
	
	
	public static void main(String[] args) {
		Champion attaker = new Hero (null, 0, 0, 0, 0, 0, 0);
		Damageable target = new Hero(null, 0, 0, 0, 0, 0, 0);

		Condition c = Condition.KNOCKEDOUT;
		Condition d = Condition.INACTIVE;
		
		System.out.println(c.equals(d));
	}
	
	
}
	
	
	
	
	
	

