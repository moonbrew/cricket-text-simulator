package cricket;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * Supervises an innings.
 */
public class InningsController {

    /**
     * Represests the states of this innings.
     */
    public enum InningsState {
        /**
         * Batsmen are being added. Cannot renter this state once exited. 
         */
        SETUP,
        /**
         * Ready to play.
         */
        READY,
        /**
         * Ready to test.
         */
        TESTING,
        /**
         * Result of this innings.
         */
        ALLOUT,
        TARGETACHIEVED,
        BALLSOVER
    }

    private String             teamName;
    private int                targetRuns;
    private int                totalBalls;
    private Batsman            striker;
    private Batsman            nonStriker;
    private ArrayList<Batsman> lineUp       = new ArrayList<Batsman>();
    private Iterator<Batsman>  lineUpIterator;
    private InningsState       inningsState = InningsState.SETUP;
    private BallResult         ballResult;
    private boolean            isSecondInnings;
    private int                balls;
    private int                totalRuns;
    private int                wickets;
    private Random             rand         = new Random();
    private int[]              testArray;
    private int                testArrayCount;

    /**
     * Create a first inning. (No target runs)
     * @param teamName Name of team playing this inning.
     * @param totalOvers Total overs.
     * @throws IllegalArgumentException Overs cannot be zero or negative. 
     */
    public InningsController(String teamName, int totalOvers) throws IllegalArgumentException {
        if (totalOvers > 0) {
            this.teamName = teamName;
            totalBalls = totalOvers * 6;
            isSecondInnings = false;
        } else
            throw new IllegalArgumentException("Total overs has to be greater than zero.");
    }

    /**
     * Create a second inning.
     * @param teamName Name of team playing this inning.
     * @param totalOvers Total overs.
     * @param targetRuns Runs to win.
     * @throws IllegalArgumentException If non-positive values are supplied.
     */
    public InningsController(String teamName, int totalOvers, int targetRuns) throws IllegalArgumentException {
        if (totalOvers > 0 && targetRuns > 0) {
            this.teamName = teamName;
            this.targetRuns = targetRuns;
            totalBalls = totalOvers * 6;
            isSecondInnings = true;
        } else
            throw new IllegalArgumentException("Total overs and runs to win have to be greater than zero.");
    }

    private void swap() {
        Batsman temp = striker;
        striker = nonStriker;
        nonStriker = temp;
    }

    public boolean isOver() {
        return balls % 6 == 0 && balls != 0;
    }

    /**
     * Prepares an inning for (re)play. Resets trackers and sets state to ready.
     * @throws IllegalStateException If atleast 2 batsmen are not in the lineUp.
     */
    public void ready() throws IllegalStateException {
        if (lineUp.size() > 1) {
            inningsState = InningsState.READY;
            reset();
        } else
            throw new IllegalStateException("Please add more batsmen.");
    }

    /**
     * Special method that lets you bypass the randomness.
     * @param An array of values between 0 (inclusive) to 100 (exclusive) to be used instead of random values.
     * @throws IllegalStateException If atleast 2 batsmen are not in the lineUp.
     */
    public void test(int[] testArray) throws IllegalStateException {
        if (lineUp.size() > 1) {
            inningsState = InningsState.TESTING;
            this.testArray = testArray;
            testArrayCount = 0;
            reset();
        } else
            throw new IllegalStateException("Please add more batsmen.");
    }

    private void reset() {
        totalRuns = 0;
        balls = 0;
        wickets = 0;
        lineUpIterator = lineUp.iterator();
        striker = lineUpIterator.next();
        nonStriker = lineUpIterator.next();
        ballResult = BallResult.ZERO;
        for (Batsman b : lineUp)
            b.reset();
    }

    /**
     * Is inning ready to continue?
     * @return True if inning state us ready or testing.
     */
    public boolean canPlay() {
        return inningsState == InningsState.READY || inningsState == InningsState.TESTING;
    }

    /**
     * Plays next ball.
     * @throws IllegalStateException When innings is not ready for play.
     */
    public void playNextBall() throws IllegalStateException {

        if (!canPlay())
            throw new IllegalStateException("Can't play Innings.");

        if (!ballResult.isRuns())
            striker = lineUpIterator.next();

        if (ballResult.isRuns() && ballResult.isOdd())
            swap();

        if (isOver())
            swap();

        balls++;
        striker.addBall();

        ballResult = getWeightedRandomBallResult();

        if (ballResult.isRuns()) {
            int runs = ballResult.getRuns();
            totalRuns += runs;
            striker.addRuns(runs);
        } else {
            wickets++;
            if (!lineUpIterator.hasNext())
                inningsState = InningsState.ALLOUT;
        }

        if (isSecondInnings && totalRuns >= targetRuns)
            inningsState = InningsState.TARGETACHIEVED;
        else if (balls == totalBalls && inningsState != InningsState.ALLOUT)
            inningsState = InningsState.BALLSOVER;
    }

    private BallResult getWeightedRandomBallResult() {
        int[] probabilities = striker.getProbabilities();
        int r, sum = 0;
        if (inningsState == InningsState.TESTING)
            r = testArray[testArrayCount++];
        else
            r = rand.nextInt(100);

        for (int i = 0; i < probabilities.length; i++) {
            sum += probabilities[i];
            if (r < sum)
                return BallResult.values()[i];
        }

        throw new ArithmeticException("Error in ball result calculation");
    }

    /**
     * Adds a batsman to the innings line up.
     * @param name Batsman name.
     * @param probabilities Integer array of probabilities in the same order as BallResult enum.
     * @throws UnsupportedOperationException When adding batsman outside of setup state.
     * @throws IllegalArgumentException For illegal probability values.
     */
    public void addBatsman(String name, int[] probabilities) throws UnsupportedOperationException, IllegalArgumentException {
        if (inningsState == InningsState.SETUP)
            lineUp.add(new Batsman(name, probabilities));
        else
            throw new UnsupportedOperationException("No longer in Setup state. Cannot add batsman.");
    }

    public int getTotalBalls() {
        return totalBalls;
    }

    /**
     * @throws UnsupportedOperationException If called on a first innings.
     */
    public int getTargetRuns() throws UnsupportedOperationException {
        if (!isSecondInnings)
            throw new UnsupportedOperationException("Cannot ask for runs left in first innings.");
        return targetRuns;
    }

    public int getTotalWickets() {
        return lineUp.size() - 1;
    }

    public Batsman getStriker() {
        return striker;
    }

    public ArrayList<Batsman> getLineUp() {
        return lineUp;
    }

    public BallResult getBallResult() {
        return ballResult;
    }

    public boolean isSecondInnings() {
        return isSecondInnings;
    }

    public int getCurrentRuns() {
        return totalRuns;
    }

    public int getCurrentBalls() {
        return balls;
    }

    public int getCurrentWicket() {
        return wickets;
    }

    public InningsState getInningsState() {
        return inningsState;
    }

    public String getTeamName() {
        return teamName;
    }
}
