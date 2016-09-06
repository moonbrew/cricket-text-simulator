import java.util.ArrayList;
import java.util.Iterator;

/**
 * Class representing a single Innings. 
 */
public class Innings {

    private final String       teamName;
    private final boolean      isBattingSecond;
    private final int          totalOvers;
    private final int          runsToWin;      //new: so final means you have to initialize in constructor? 

    private ArrayList<Batsman> lineUp;
    private Result             result;
    private String             commentary = "";
    private int                runs;
    private int                balls;
    private int                wickets;

    public enum Result {
        ALLOUT,
        BALLSOVER,
        RUNSCHASED
    }

    /**
     * Constructs a new first innings.
     * @param t Team name
     * @param o Totalovers
     */
    public Innings(String t, int o) {
        teamName = t;
        totalOvers = o;

        runsToWin = 0;
        lineUp = new ArrayList<Batsman>();
        isBattingSecond = false;
    }

    /**
     * Constructs a new second innings
     * @param t Team name
     * @param o Total overs left
     * @param rtw Runs to win
     */
    public Innings(String t, int o, int rtw) {
        teamName = t;
        totalOvers = o;
        runsToWin = rtw;

        lineUp = new ArrayList<Batsman>();//chech?
        isBattingSecond = true;
    }

    /**
     * Play the innings
     * @return Runs scored
     * @throws IllegalStateException Innings is not in correct state to be played
     */
    public int play() throws IllegalStateException {
        if (lineUp.size() < 2)
            throw new IllegalStateException("Please add more Batsmen");

        Iterator<Batsman> l = lineUp.iterator();

        Batsman striker = l.next(), nonStriker = l.next();

        commentary = "";
        //e.g. 3 overs left.
        commentary += String.format("%d %s left.", totalOvers, totalOvers == 1 ? "over" : "overs");
        //e.g. 28 runs to win
        if (isBattingSecond)
            commentary += String.format(" %d %s to win.", runsToWin, runsToWin == 1 ? "run" : "runs");
        commentary += "\n\n";

        balls = 1;
        runs = 0;
        wickets = 0;
        while (balls <= totalOvers * 6) {
            String currentOver = (balls - 1) / 6 + "." + ((balls - 1) % 6 + 1);
            String ballResult = striker.playBall();

            if (ballResult.equals("out")) {
                commentary += String.format("%s %s gets out!", currentOver, striker.getName());
                wickets++;
                if (!l.hasNext()) {//is this correct?
                    result = Result.ALLOUT;
                    commentary += String.format(" Team %s gets all out!\n", teamName);
                    break;
                }
                striker = l.next();
            } else if (ballResult.equals("dot ball"))
                commentary += String.format("%s %s scored 0 runs", currentOver, striker.getName());
            else {
                int score = Integer.parseInt(ballResult);
                runs += score;
                String y = score == 1 ? "run" : "runs";
                commentary += String.format("%s %s scored %d %s", currentOver, striker.getName(), score, y);

                //switch
                if (score == 1 || score == 3 || score == 5) {
                    Batsman z = striker;
                    striker = nonStriker;
                    nonStriker = z;
                }
            }

            //chase check
            if (isBattingSecond && runs >= runsToWin) {
                result = Result.RUNSCHASED;
                commentary += String.format(" %s wins!\n", teamName);
                break;
            }

            //if over
            if (balls % 6 == 0 && balls / 6 != totalOvers) {//not last ball of innings
                //switch
                Batsman z = striker;
                striker = nonStriker;
                nonStriker = z;

                commentary += "\n\n";

                //e.g. 3 overs left.
                int ol = totalOvers - (balls) / 6;
                commentary += String.format("%d %s left.", ol, ol == 1 ? "over" : "overs");
                //e.g. 28 runs to win
                if (isBattingSecond) {
                    int rl = runsToWin - runs;
                    commentary += String.format(" %d %s to win.", rl, rl == 1 ? "run" : "runs");
                }
                commentary += "\n";
            }
            commentary += "\n";
            balls++;
        }

        if (balls > totalOvers * 6) {
            result = Result.BALLSOVER;//for reruns. better no default
            balls--;
        }

        return runs;
    }

    public String getTeamName() {
        return teamName;
    }

    /**
     * Provides the innings result.
     * @throws IllegalStateException if innings is not complete
     */
    public Result getResult() throws IllegalStateException {
        if (result == null)
            throw new IllegalStateException("Please play and complete the innings first.");
        return result;
    }

    /**
     * Provide a preformatted commentary of the innings
     * @return commentary
     */
    public String getCommentary() {
        return commentary;
    }

    /**
     * Results runs left.
     * @throws UnsupportedOperationException if its first innings.
     */
    public int getRunsLeft() throws UnsupportedOperationException {
        if (isBattingSecond)
            return runsToWin;
        else
            throw new UnsupportedOperationException("Cannot ask for runs left in first innings.");
    }

    public int getBallsLeft() {
        return totalOvers * 6 - balls;
    }

    /**
     * Provides wickets left
     * @throws IllegalStateException if atleast 1 batsman is not added.
     */
    public int getWicketsLeft() throws IllegalStateException {
        if (lineUp.size() < 1)
            throw new IllegalStateException("Please add batsman first.");
        return lineUp.size() - wickets - 1;
    }

    /**
     * Adds a batsman. They will bat in the order they are added with this method
     * @param name Name of batsman
     * @param prob Probabilities in an integer array in the same order as Batsman.BALL_RESULT.
     * @throws IllegalArgumentException When probability array is not valid.
     */
    public void addBatsman(String name, int[] prob) throws IllegalArgumentException {
        lineUp.add(new Batsman(name, prob));
    }

    public Iterator<Batsman> getBatsmanIterator() {
        return lineUp.iterator();
    }
}
