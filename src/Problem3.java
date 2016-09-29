import cricket.*;
public class Problem3 {

	public static void main(String[] args) {
		Batsman[] batsmen1 = new Batsman[2];
		batsmen1[0] = new Batsman("Kirat Boli", new int[]{5, 10, 25, 10, 25, 1, 14, 10});//null exception
		batsmen1[1] = new Batsman("N.S Nodhi", new int[]{5, 15, 15, 10, 20, 1, 19, 15});// sum exception
		
		Batsman[] batsmen2 = new Batsman[2];//null exception
		batsmen2[0] = new Batsman("DB Vellyers", new int[]{5, 10, 25, 10, 25, 1, 14, 10});
		batsmen2[1] = new Batsman("H Mamla", new int[]{10, 15, 15, 10, 20, 1, 19, 15});
	
		int oversRemaining = 1;

		Innings team1 = new Innings("Lengaburu", batsmen1, Innings.BattingOrder.FIRST, oversRemaining, 0);
		int runsRemaining = team1.play() + 1;
		
		Innings team2 = new Innings("Enchai", batsmen2, Innings.BattingOrder.SECOND, oversRemaining, runsRemaining);
		team2.play();
		
		
	}

}
