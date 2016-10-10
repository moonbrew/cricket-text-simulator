package cricket;

import java.util.ArrayList;

/**
 * Provides methods for formatted output for an innings.
 */
public class Broadcaster {
    private InningsController inningsController;

    public Broadcaster(InningsController inningsController) {
        this.inningsController = inningsController;
    }

    public String getOverCommentary() {
        String commentary = "";
        while (inningsController.canPlay()) {
            inningsController.playNextBall();
            commentary += getFormattedOvers();
            String batsmanName = inningsController.getStriker().getName();
            BallResult ballResult = inningsController.getBallResult();
            if (ballResult.isRuns())
                commentary +=
                              String.format(" %s scored %d %s.\n", batsmanName, ballResult.getRuns(), ballResult.getRuns() == 1 ? "run"
                                  : "runs");
            else
                commentary += String.format(" %s gets out!\n", batsmanName);

            if (inningsController.isOver())
                break;
        }
        return commentary;
    }

    /**
     * @throws UnsupportedOperationException If its not an over, innings hasn't started or got completed.
     */
    public String getOverHeader() throws UnsupportedOperationException {
        if ((inningsController.isOver() || inningsController.getCurrentBalls() == 0) && inningsController.canPlay()) {
            int oversLeft = inningsController.getTotalBalls() / 6 - inningsController.getCurrentBalls() / 6;
            String commentaryHeader = String.format("%d %s left.", oversLeft, oversLeft == 1 ? "over" : "overs");
            if (inningsController.isSecondInnings()) {
                int runsLeft = inningsController.getTargetRuns() - inningsController.getCurrentRuns();
                commentaryHeader += String.format(" %d %s to win.", runsLeft, runsLeft == 1 ? "run" : "runs");
            }
            return commentaryHeader + "\n";
        } else
            throw new UnsupportedOperationException("Over header is only available at the beginning of an over.");
    }

    public String getScoreboard() {
        ArrayList<Batsman> lineUp = inningsController.getLineUp();
        String scoreBoard = "";
        for (Batsman b : lineUp)
            scoreBoard +=
                          String.format("%s - %d (%d %s)\n", b.getName(), b.getBallsPlayed(), b.getRunsScored(), b.getRunsScored() == 1
                              ? "run" : "runs");
        return scoreBoard;
    }

    private String getFormattedOvers() {
        int balls = inningsController.getCurrentBalls() - 1;
        return balls / 6 + "." + (balls % 6 + 1);
    }
}
