import cricket.InningsController;
import static cricket.InningsController.InningsState;
import cricket.Broadcaster;

public class Problem2 {
    public static void main(String[] args) {
        int oversRemaining = 4;
        int targetRuns = 40;

        InningsController inningsController = new InningsController("Lengaburu", oversRemaining, targetRuns);
        inningsController.addBatsman("Kirat Boli", new int[]{5, 30, 25, 10, 15, 1, 9, 5});
        inningsController.addBatsman("N.S Nodhi", new int[]{10, 40, 20, 5, 10, 1, 4, 10});
        inningsController.addBatsman("R Rumrah", new int[]{20, 30, 15, 5, 5, 1, 4, 20});
        inningsController.addBatsman("Shashi Henra", new int[]{30, 25, 5, 0, 5, 1, 4, 30});
        inningsController.ready();
        Broadcaster broadcaster = new Broadcaster(inningsController);

        String commentary = "";
        while (inningsController.canPlay())
            commentary += "\n" + broadcaster.getOverHeader() + "\n" + broadcaster.getOverCommentary();

        InningsState inningsState = inningsController.getInningsState();
        String teamName = inningsController.getTeamName();
        int ballsRemaining, wicketsRemaining, runsRemaining;
        switch (inningsState) {
            case ALLOUT:
                ballsRemaining = inningsController.getTotalBalls() - inningsController.getCurrentBalls();
                runsRemaining = inningsController.getTargetRuns() - inningsController.getCurrentRuns();
                System.out.printf("%s lost with %d %s and %d %s remaining.\n", teamName, ballsRemaining, ballsRemaining == 1 ? "ball"
                    : "balls", runsRemaining, runsRemaining == 1 ? "run" : "runs");
                commentary += teamName + " got allout!";
                break;
            case BALLSOVER:
                runsRemaining = inningsController.getTargetRuns() - inningsController.getCurrentRuns();
                wicketsRemaining = inningsController.getTotalWickets() - inningsController.getCurrentWicket();
                System.out.printf("%s lost with %d %s and %d %s remaining.\n", teamName, runsRemaining,
                                  runsRemaining == 1 ? "run" : "runs", wicketsRemaining, wicketsRemaining == 1 ? "wicket" : "wickets");
                break;
            case TARGETACHIEVED:
                ballsRemaining = inningsController.getTotalBalls() - inningsController.getCurrentBalls();
                wicketsRemaining = inningsController.getTotalWickets() - inningsController.getCurrentWicket();
                System.out.printf("%s won with %d %s and %d %s remaining.\n", teamName, ballsRemaining, ballsRemaining == 1 ? "ball"
                    : "balls", wicketsRemaining, wicketsRemaining == 1 ? "wicket" : "wickets");
                commentary += teamName += " wins!";
                break;
            case READY:
                break;
            case SETUP:
                break;
            default:
                break;
        }
        System.out.println("\nScoreboard:\n");
        System.out.print(broadcaster.getScoreboard());

        System.out.println("\nCommentary:");
        System.out.print(commentary);
    }
}
