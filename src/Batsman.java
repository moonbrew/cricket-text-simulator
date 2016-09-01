/**
 * Knows name, probabilities, runs scored and balls played.
 * Plays a ball based on probability.
 */

public class Batsman {

    private String               name;
    private int[]                probabilities;
    private int                  runsScored;
    private int                  ballsPlayed;

    public static final String[] BALL_RESULT = {"dot ball", "1", "2", "3", "4", "5", "6", "out"};
    private String               currentBallResult;

    /**
     * Construct a batsman given a name and probabilities.
     * @param n Name of batsman
     * @param p Probabilities in an integer array in the same order as Batsman.BALL_RESULT.
     */
    public Batsman(String n, int[] p) {
        name = n;
        probabilities = p;
        //check same length as results(8)
        //check no negative?
        //check total 100
    }

    /**
     * Play a ball based on given probability. Updates balls played and runs scored.
     * @return ball result.
     */
    public String playBall() {
        double r = Math.random();
        playBall(r);//body separated for testing
        return currentBallResult;
    }

    private void playBall(double r) {
        //System.out.println(r);//
        int count = 0;
        double sum = 0;
        while (count < BALL_RESULT.length) {
            sum += (double)probabilities[count] / 100;//coz int
            if (r < sum)
                break;
            count++;
        }
        currentBallResult = BALL_RESULT[count];
        addRuns();
        ballsPlayed++;
    }

    //remove
    public void testPlayBall(double r) {
        playBall(r);
    }

    //Helper method showing short way of getting runs that can have a different implementation
    private void addRuns() {
        if (!currentBallResult.equals("dot ball") && !currentBallResult.equals("out"))
            runsScored += Integer.parseInt(currentBallResult);
    }

    /**
     * @return name of batsman.
     */
    public String getName() {
        return name;
    }

    /**
     * @return runs scored.
     */
    public int getRunsScored() {
        return runsScored;
    }

    /**
     * @return balls played by this batsman.
     */
    public int getBallsPlayed() {
        return ballsPlayed;
    }

    /**
     * @return result of last ball played.
     */
    public String getCurrentBallResult() {
        return currentBallResult;
    }
}
