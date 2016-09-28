package cricket;

/**
 * Class representing a single batsman.
 * Knows name, probabilities, runs scored and balls played.
 * Plays a ball based on probability.
 */
public class Batsman {

    private final String         name;
    private final int[]          probabilities;
    private int                  runsScored;
    private int                  ballsPlayed;

    public static final String[] BALL_RESULT = {"dot ball", "1", "2", "3", "4", "5", "6", "out"};
    private String               currentBallResult;

    /**
     * Construct a batsman given a name and probabilities.
     * @param n Name of batsman
     * @param p Probabilities as percentages for each result in Batsman.BALL_RESULT in the same order in an integer array.
     * @throws IllegalArgumentException When the probability array is invalid. Does not check if order is right.
     */
    public Batsman(String n, int[] p) throws IllegalArgumentException {
        name = n;

        if (p == null)
            throw new IllegalArgumentException("Probability array cannot be null!");
        else if (p.length != BALL_RESULT.length)
            throw new IllegalArgumentException("Probability array should be of length " + BALL_RESULT.length);

        int sum = 0;
        for (int x : p) {
            if (x < 0)
                throw new IllegalArgumentException("Probabilities should not be negative!");
            sum += x;
        }

        if (sum != 100)
            throw new IllegalArgumentException("Probabilities should add up to 100");

        probabilities = p;
    }

    /**
     * Play a ball. Result is influenced by batsman's probabilities. Updates balls played and runs scored.
     * @return Result.
     */
    public String playBall() {
        double r = Math.random();
        return testPlayBall(r);//body separated for testing
    }

    //remove
    public String testPlayBall(double r) {
        //System.out.println(r);//
        int count = 0;
        int sum = 0;
        while (count < BALL_RESULT.length) {
            sum += probabilities[count];
            if (r * 100 < sum)//double seems to fuck up only during addition
                break;
            count++;
        }
        currentBallResult = BALL_RESULT[count];
        addRuns();
        ballsPlayed++;
        return currentBallResult;
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
