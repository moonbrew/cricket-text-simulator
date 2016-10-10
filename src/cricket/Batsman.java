package cricket;

/**
 * Represents a batsman.
 */
public class Batsman {

    private String name;
    private int[]  probabilities;
    private int    ballsPlayed = 0;
    private int    runsScored  = 0;

    /**
     * @param name Name of batsman.
     * @param probabilities The probability array in the order of BallResult enum.
     * @throws IllegalArgumentException for illegal probability values.
     */
    public Batsman(String name, int[] probabilities) throws IllegalArgumentException {
        if (probabilities.length != BallResult.values().length)
            throw new IllegalArgumentException("Probability array should be of length " + BallResult.values().length);
        else {
            int sum = 0;
            for (int p : probabilities) {
                if (p < 0)
                    throw new IllegalArgumentException("Probability cannot be negative");
                sum += p;
            }
            if (sum != 100)
                throw new IllegalArgumentException("Probabilities need to add up to 100");
        }
        this.name = name;
        this.probabilities = probabilities;
    }

    /**
     * Resets all trackers.
     */
    public void reset() {
        ballsPlayed = 0;
        runsScored = 0;
    }

    public void addBall() {
        ballsPlayed++;
    }

    public void addRuns(int runs) {
        runsScored += runs;
    }

    public int getBallsPlayed() {
        return ballsPlayed;
    }

    public int getRunsScored() {
        return runsScored;
    }

    public String getName() {
        return name;
    }

    public int[] getProbabilities() {
        return probabilities;
    }
}
