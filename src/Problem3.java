import java.util.Iterator;
import cricket.Batsman;
import cricket.Innings;

public class Problem3 {

    public static void main(String[] args) {


        int oversRemaining = 1;

        Innings firstInnings = new Innings("Lengaburu", oversRemaining);
        firstInnings.addBatsman("Kirat Boli", new int[]{5, 10, 25, 10, 25, 1, 14, 10});
        firstInnings.addBatsman("N.S Nodhi", new int[]{5, 15, 15, 10, 20, 1, 19, 15});

        firstInnings.play();

        Innings secondInnings = new Innings("Enchai", oversRemaining, firstInnings.getRuns() + 1);
        secondInnings.addBatsman("DB Vellyers", new int[]{5, 10, 25, 10, 25, 1, 14, 10});
        secondInnings.addBatsman("H Mamla", new int[]{10, 15, 15, 10, 20, 1, 19, 10});//last probability is 15 in problem pdf
        secondInnings.play();

        //overall result
        if (firstInnings.getRuns() == secondInnings.getRuns())
            System.out.println("Match ended in a tie.");
        else if (secondInnings.getResult() == Innings.Result.RUNSCHASED)
            System.out.printf("%s won by %d %s remaining.\n", secondInnings.getTeamName(), secondInnings.getBallsLeft(),
                              secondInnings.getBallsLeft() == 1 ? "ball" : "balls");
        else if (secondInnings.getResult() == Innings.Result.BALLSOVER)
            System.out.printf("%s won by %d %s.\n", firstInnings.getTeamName(), secondInnings.getRunsLeft(),
                              secondInnings.getRunsLeft() == 1 ? "run" : "runs");
        else if (secondInnings.getResult() == Innings.Result.ALLOUT)
            System.out.printf("%s won with %d %s.\n", firstInnings.getTeamName(), secondInnings.getRunsLeft(),
                              secondInnings.getRunsLeft() == 1 ? "run" : "runs");

        System.out.println();

        //Scoreboard
        Iterator<Batsman> batsmen = firstInnings.getBatsmanIterator();
        while (batsmen.hasNext()) {
            Batsman b = batsmen.next();
            System.out.printf("%s - %d (%d %s)\n", b.getName(), b.getRunsScored(), b.getBallsPlayed(), b.getBallsPlayed() == 1 ? "ball"
                : "balls");
        }

        System.out.println();

        batsmen = secondInnings.getBatsmanIterator();
        while (batsmen.hasNext()) {
            Batsman b = batsmen.next();
            System.out.printf("%s - %d (%d %s)\n", b.getName(), b.getRunsScored(), b.getBallsPlayed(), b.getBallsPlayed() == 1 ? "ball"
                : "balls");
        }

        System.out.println();

        System.out.printf("%s Innings:\n\n", firstInnings.getTeamName());
        System.out.println(firstInnings.getCommentary());

        System.out.println();

        System.out.printf("%s Innings:\n\n", secondInnings.getTeamName());
        System.out.println(secondInnings.getCommentary());


    }
}
