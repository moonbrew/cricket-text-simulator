import java.util.Iterator;

public class Problem1 {

	public static void main(String[] args) {

		int oversRemaining = 4;
		int runsRemaining = 40;

		Innings n = new Innings("Lengaburu", Innings.BattingOrder.SECOND, oversRemaining, runsRemaining);

		n.addBatsman("Kirat Boli", new int[]{5, 30, 25, 10, 15, 1, 9, 5});
		n.addBatsman("N.S Nodhi", new int[]{10, 40, 20, 5, 10, 1, 4, 10});
		n.addBatsman("R Rumrah", new int[]{20, 30, 15, 5, 5, 1, 4, 20});
		n.addBatsman("Shashi Henra", new int[]{30, 25, 5, 0, 5, 1, 4, 30});
		
                n.play();
		
		catch(IllegalStateException e){
			System.out.println(e.getMessage());
		}

				
		//overall result
                        if(n.getResult() == Innings.Result.RUNSCHASED){
			int br = oversRemaining * 6 - n.getBallsLeft();
			System.out.printf("%s won by %d %s and %d %s remaining.\n", n.getTeamName(),
					n.getWicketsLeft(), n.getWicketsLeft() == 1 ? "wicket" : "wickets", n.getBallsLeft(), n.getBallsLeft() == 1 ? "ball" : "balls");
		}else if(n.getResult() == Innings.Result.BALLSOVER){
			int rr = runsRemaining - n.getRunsLeft();
			System.out.printf("%s lost with %d %s and %d %s remaining.\n", n.getTeamName(),
					n.getWicketsLeft(), n.getWicketsLeft() == 1 ? "wicket" : "wickets", n.getRunsLeft(), n.getRunsLeft() == 1 ? "run" : "runs");
		}else if(n.getResult() == Innings.Result.ALLOUT){
			int br = oversRemaining * 6 - n.getBalls();
			int rr = runsRemaining - n.getRuns();
			System.out.printf("%s lost with %d %s and %d %s remaining.\n", n.getTeamName(),
					n.getBallsLeft(), n.getBallsLeft() == 1 ? "ball" : "balls", n.getRunsLeft(), n.getRunsLeft() == 1 ? "run" : "runs");
		}

		System.out.println();

		//e.g. Kirat Boli - 12 (6 balls) Scoreboard
		Iterator<Batsman> batsmen = n.getBatsmanIterator();
		while (batsmen.hasNext()){
			Batsman b = batsmen.next();
			System.out.printf("%s - %d (%d %s)\n", b.getName(), b.getRunsScored(), b.getBallsPlayed(),
					b.getBallsPlayed() == 1 ? "ball" : "balls");
		}

		System.out.println();

		System.out.println("Commentary\n");
		System.out.println(n.getCommentary());
	}

}
