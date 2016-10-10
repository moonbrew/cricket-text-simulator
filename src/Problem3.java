import cricket.InningsController;
import static cricket.InningsController.InningsState;
import cricket.Broadcaster;

public class Problem3 {
    public static void main(String[] args) {

        int oversRemaining = 1;

        InningsController inningsController1 = new InningsController("Lengaburu", oversRemaining);
        inningsController1.addBatsman("Kirat Boli", new int[]{5, 10, 25, 10, 25, 1, 14, 10});
        inningsController1.addBatsman("N.S Nodhi", new int[]{5, 15, 15, 10, 20, 1, 19, 15});
        inningsController1.ready();
        Broadcaster broadcaster1 = new Broadcaster(inningsController1);
        String commentary1 = "";
        while (inningsController1.canPlay())
            commentary1 += broadcaster1.getOverCommentary();

        InningsController inningsController2 = new InningsController("Enchai", oversRemaining, inningsController1.getCurrentRuns() + 1);
        inningsController2.addBatsman("DB Vellyers", new int[]{5, 10, 25, 10, 25, 1, 14, 10});
        inningsController2.addBatsman("H Mamla", new int[]{10, 15, 15, 10, 20, 1, 19, 10});//last probability is 15 in problem pdf
        inningsController2.ready();
        Broadcaster broadcaster2 = new Broadcaster(inningsController2);
        String commentary2 = "";
        while (inningsController2.canPlay())
            commentary2 += broadcaster2.getOverCommentary();

        InningsState inningsState2 = inningsController2.getInningsState();
        String teamName1 = inningsController1.getTeamName();
        String teamName2 = inningsController2.getTeamName();
        int runsRemaining = inningsController2.getTargetRuns() - inningsController2.getCurrentRuns();
        if (runsRemaining == 0)
            System.out.println("The match ended in a tie.");
        else
            switch (inningsState2) {
                case ALLOUT:
                    System.out.printf("%s won by %d %s.\n", teamName1, runsRemaining, runsRemaining == 1 ? "run" : "runs");
                    break;
                case BALLSOVER:
                    System.out.printf("%s won by %d %s.\n", teamName1, runsRemaining, runsRemaining == 1 ? "run" : "runs");
                    break;
                case TARGETACHIEVED:
                    int ballsRemaining = inningsController2.getTotalBalls() - inningsController2.getCurrentBalls();
                    System.out.printf("%s won with %d %s remaining.\n", teamName2, ballsRemaining, ballsRemaining == 1 ? "ball" : "balls");
                    break;
                case READY:
                    break;
                case SETUP:
                    break;
                default:
                    break;
            }
        System.out.println("\nLengaburu:");
        System.out.print(broadcaster1.getScoreboard());
        System.out.println("\nEnchai");
        System.out.print(broadcaster2.getScoreboard());


        System.out.println("\nCommentary:");
        System.out.println("\nLengaburu Innings:");
        System.out.print(commentary1);
        System.out.println("\nEnchai Innings:");
        System.out.print(commentary2);

    }
}
