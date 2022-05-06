package exceptions;

public class LeaderNotCurrentException extends GameActionException {
	 LeaderNotCurrentException(){}
	 LeaderNotCurrentException(String s){
		 super(s);
	 }
}
