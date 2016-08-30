import java.util.ArrayList;
import java.util.Iterator;

public class Innings {

	private String teamName;
	private ArrayList<Batsman> lineUp;
	private BattingOrder battingOrder;
	private Result result;
	private int oversLeft;
	private int runsToWin;
	private String commentary = "";
	private int runs;
	private int balls;
	private int wickets;
	
	public enum BattingOrder {
		FIRST, SECOND
	}
	
	public enum Result {
		ALLOUT, BALLSOVER, RUNSCHASED
	}
	
	/**
	 * Constructs a new innings
	 * @param t Team name
	 * @param b Whether the team is batting first or second indicated by BattingOrder.FIRST or BattingOrder.Second
	 * @param o Total overs left
	 * @param rtw Runs to win if team is batting second
	 */
	public Innings(String t, BattingOrder b, int o, int rtw){
		teamName = t;
		lineUp = new ArrayList<Batsman>();
		battingOrder = b;
		oversLeft = o;
		if(battingOrder == BattingOrder.SECOND)
			runsToWin = rtw;
	}
	
	/**
	 * Play the innings
	 * @return Runs scored
	 * @throws IllegalStateException Innings is not in correct state to be played
	 */
	public int play() throws IllegalStateException {
		if(lineUp.size() < 2){
			throw new IllegalStateException("Please add more Batsmen");
		}
		
		Iterator<Batsman> l = lineUp.iterator();

		Batsman striker = l.next(), nonStriker = l.next();
		
		//e.g. 3 overs left.
		commentary += String.format("%d %s left.", oversLeft, oversLeft == 1 ? "over" : "overs");
		//e.g. 28 runs to win
		if(battingOrder == BattingOrder.SECOND)
			commentary += String.format(" %d %s to win.", runsToWin, runsToWin == 1 ? "run" : "runs");
		commentary += "\n\n";
		
		balls = 1; runs = 0; wickets = 0;
		while(balls <= oversLeft * 6){
			String currentOver = (balls - 1) / 6 + "." + (balls % 6);
			String ballResult = striker.playBall();
			
			if(ballResult.equals("out")){
				commentary += String.format("%s %s gets out!", currentOver, striker.getName());
				wickets++;
				if(!l.hasNext()){//is this correct?
					result = Result.ALLOUT;
					commentary += String.format(" Team %s gets all out!\n", teamName);
					break;
				}
				striker = l.next();	
			}else if(ballResult.equals("dot ball"))
				commentary += String.format("%s %s scored 0 runs", currentOver, striker.getName());
			else{
				int score = Integer.parseInt(ballResult);
				runs += score;
				String y = score == 1 ? "run" : "runs";
				commentary += String.format("%s %s scored %d %s", currentOver, striker.getName(), score, y);
				
				//switch
				if(score == 1 || score == 3 || score == 5){
					Batsman z = striker;
					striker = nonStriker;
					nonStriker = z;
				}
			}
			
			//chase check
			if(battingOrder == BattingOrder.SECOND && runs >= runsToWin){
				result = Result.RUNSCHASED;
				commentary += String.format(" %s wins!\n", teamName);
				break;
			}
			
			//if over
			if(balls % 6 == 0 && balls / 6 != oversLeft){//not last ball of innings
				//switch
				Batsman z = striker;
				striker = nonStriker;
				nonStriker = z;
				
				commentary += "\n\n";

				//e.g. 3 overs left.
				int ol = oversLeft - (balls) / 6;
				commentary += String.format("%d %s left.", ol, ol == 1 ? "over" : "overs");
				//e.g. 28 runs to win
				if(battingOrder == BattingOrder.SECOND){
					int rl = runsToWin - runs;
					commentary += String.format(" %d %s to win.", rl, rl == 1 ? "run" : "runs");
				}
				commentary += "\n";
			}
			commentary += "\n";
			balls++;
		}
		
		if(balls > oversLeft * 6){
			result = Result.BALLSOVER;//for reruns. better no default
			balls--;
		}
			
		return runs;
	}
	public String getTeamName() {
		return teamName;
	}

	public Result getResult() {
		return result;
	}

	/**
	 * Provide a preformatted commentary of the innings
	 * @return commentary
	 */
	public String getCommentary() {
		return commentary;
	}

	public int getRuns() {
		return runs;
	}

	public int getBalls() {
		return balls;
	}

	public int getWickets() {
		return wickets;
	}

	/**
	 * Adds a batsman. They will bat in the order they are added with this method
	 * @param name Name of batsman
	 * @param prob Probabilities in an integer array in the same order as Batsman.BALL_RESULT.
	 */
	public void addBatsman(String name, int[] prob){
		lineUp.add(new Batsman(name, prob));
	}
	
	public int getTotalBatsmen(){
		return lineUp.size();
	}
	
	public Iterator<Batsman> getBatsmanIterator(){
		return lineUp.iterator();
	}
}
