import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import cricket.Innings;
import cricket.Batsman;

public class TestInnings {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    /*
     * state after creation
     */

    @Test
    public void testInitialStateRunsLeft1() {
        Innings a = new Innings("Testteam", 6);

        //cant call because first inning
        thrown.expect(UnsupportedOperationException.class);
        thrown.expectMessage("Cannot ask for runs left in first innings.");
        a.getRunsLeft();
    }

    @Test
    public void testInitialStateRunsLeft2() {
        Innings a = new Innings("Testteam", 6, 40);

        //can call because second inning
        assertThat("Runs", a.getRunsLeft(), is(40));
    }

    @Test
    public void testInitialStateResult1() {
        Innings a = new Innings("Testteam", 6);

        //is null! play innings IllegalStateException exception.
        //no null coz its bad style to send nulls to indicate something
        //be more clear
        thrown.expect(IllegalStateException.class);
        thrown.expectMessage("Please play and complete the innings first.");
        a.getResult();
    }

    @Test
    public void testInitialStateWickets() {
        Innings a = new Innings("Testteam", 6);

        //add batsman. Atleast 1.
        thrown.expect(IllegalStateException.class);
        thrown.expectMessage("Please add batsman first.");
        a.getWicketsLeft();
    }

    @Test
    public void testInitialState() {
        Innings a = new Innings("Testteam", 6);
        assertThat("Balls", a.getBallsLeft(), is(36));
    }

    /*
     * add batsman state checks
     */

    @Test
    public void testAddBatsmanStateWickets1() {
        Innings a = new Innings("Testteam", 6);
        a.addBatsman("Testguy1", new int[]{5, 30, 25, 10, 15, 1, 9, 5});
        assertThat("Add batsman wickets1", a.getWicketsLeft(), is(0));
    }

    @Test
    public void testAddBatsmanStateWickets2() {
        Innings a = new Innings("Testteam", 6);
        a.addBatsman("Testguy1", new int[]{5, 30, 25, 10, 15, 1, 9, 5});
        a.addBatsman("Testguy2", new int[]{10, 40, 20, 5, 10, 1, 4, 10});
        a.addBatsman("Testguy3", new int[]{20, 30, 15, 5, 5, 1, 4, 20});
        a.addBatsman("Testguy4", new int[]{30, 25, 5, 0, 5, 1, 4, 30});
        assertThat("Add batsman state wickets2", a.getWicketsLeft(), is(3));
    }

    @Test
    public void testAddBatsman() {
        Innings a = new Innings("Testteam", 6);
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Probability array should be of length 8");
        a.addBatsman("Testguy1", new int[]{5, 30, 25, 10, 15, 1, 9});
    }

    /*
     * play initial state checks
     */

    @Test
    public void testPlayInitialStateWickets1() {
        Innings a = new Innings("Testteam", 6);
        thrown.expect(IllegalStateException.class);
        thrown.expectMessage("Please add more Batsmen");
        a.play();
    }

    @Test
    public void testPlayInitialStateWickets2() {
        Innings a = new Innings("Testteam", 6);
        a.addBatsman("Testguy1", new int[]{5, 30, 25, 10, 15, 1, 9, 5});
        thrown.expect(IllegalStateException.class);
        thrown.expectMessage("Please add more Batsmen");
        a.play();
    }

    /*
     * After play state tests: Normal.
     */
    @Test
    public void testAfterPlayState1() {
        Innings a = new Innings("Lengaburu", 4, 40);
        a.addBatsman("Kirat Boli", new int[]{5, 30, 25, 10, 15, 1, 9, 5});
        a.addBatsman("N.S Nodhi", new int[]{10, 40, 20, 5, 10, 1, 4, 10});
        a.addBatsman("R Rumrah", new int[]{20, 30, 15, 5, 5, 1, 4, 20});
        a.addBatsman("Shashi Henra", new int[]{30, 25, 5, 0, 5, 1, 4, 30});

        int[] r = {8, 60, 55, 54, 16, 24, 83, 46, 15, 91, 9, 64, 70, 68, 75, 8, 16, 71, 28, 90, 2, 46, 76};
        a.testPlay(r);
        String commentary = "";
        try {
            Scanner s = new Scanner(new File("src/Commentary1.txt"));
            try {
                while (s.hasNext()) {
                    commentary += s.nextLine() + "\n";
                }
            } finally {
                s.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
        assertThat("Commentary1", a.getCommentary(), is(commentary));
        String[] name = {"Kirat Boli", "N.S Nodhi", "R Rumrah", "Shashi Henra"};
        int[] runs = {22, 7, 11, 1};
        int[] balls = {10, 5, 6, 2};
        Iterator<Batsman> l = a.getBatsmanIterator();
        int i = 0;
        while (l.hasNext()) {//scoreboard
            Batsman b = l.next();
            assertThat("Scoreboard name 1", b.getName(), is(name[i]));
            assertThat("Scoreboard runs scored 1", b.getRunsScored(), is(runs[i]));
            assertThat("Scoreboard balls played 1", b.getBallsPlayed(), is(balls[i]));
            i++;
        }
        //result
        assertThat("Result 1", a.getResult(), is(Innings.Result.RUNSCHASED));
        assertThat("Result runs left 1", a.getRunsLeft(), is(0));
        assertThat("Result balls left 1", a.getBallsLeft(), is(1));
        assertThat("Result wickets left 1", a.getWicketsLeft(), is(1));
    }

    @Test
    public void testAfterPlayState2() {
        Innings a = new Innings("Lengaburu", 4, 40);
        a.addBatsman("Kirat Boli", new int[]{5, 30, 25, 10, 15, 1, 9, 5});
        a.addBatsman("N.S Nodhi", new int[]{10, 40, 20, 5, 10, 1, 4, 10});
        a.addBatsman("R Rumrah", new int[]{20, 30, 15, 5, 5, 1, 4, 20});
        a.addBatsman("Shashi Henra", new int[]{30, 25, 5, 0, 5, 1, 4, 30});

        int[] r = {37, 70, 37, 38, 45, 67, 96, 32, 94, 23, 55, 44, 1, 80};
        a.testPlay(r);
        String commentary = "";
        try {
            Scanner s = new Scanner(new File("src/Commentary2.txt"));
            try {
                while (s.hasNext()) {
                    commentary += s.nextLine() + "\n";
                }
            } finally {
                s.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
        assertThat("Commentary2", a.getCommentary(), is(commentary));
        String[] name = {"Kirat Boli", "N.S Nodhi", "R Rumrah", "Shashi Henra"};
        int[] runs = {15, 0, 1, 3};
        int[] balls = {7, 1, 1, 5};
        Iterator<Batsman> l = a.getBatsmanIterator();
        int i = 0;
        while (l.hasNext()) {//scoreboard
            Batsman b = l.next();
            assertThat("Scoreboard name 2", b.getName(), is(name[i]));
            assertThat("Scoreboard runs scored 2", b.getRunsScored(), is(runs[i]));
            assertThat("Scoreboard balls played 2", b.getBallsPlayed(), is(balls[i]));
            i++;
        }
        //result
        assertThat("Result 2", a.getResult(), is(Innings.Result.ALLOUT));
        assertThat("Result runs left 2", a.getRunsLeft(), is(21));
        assertThat("Result balls left 2", a.getBallsLeft(), is(10));
        assertThat("Result wickets left 2", a.getWicketsLeft(), is(0));
    }

    @Test
    public void testAfterPlayState3() {
        Innings a = new Innings("Lengaburu", 4, 40);
        a.addBatsman("Kirat Boli", new int[]{5, 30, 25, 10, 15, 1, 9, 5});
        a.addBatsman("N.S Nodhi", new int[]{10, 40, 20, 5, 10, 1, 4, 10});
        a.addBatsman("R Rumrah", new int[]{20, 30, 15, 5, 5, 1, 4, 20});
        a.addBatsman("Shashi Henra", new int[]{30, 25, 5, 0, 5, 1, 4, 30});

        int[] r = {53, 9, 20, 85, 24, 97, 15, 14, 43, 33, 55, 70, 10, 30, 27, 57, 91, 3, 59, 82, 37, 29, 13, 57};
        a.testPlay(r);
        String commentary = "";
        try {
            Scanner s = new Scanner(new File("src/Commentary3.txt"));
            try {
                while (s.hasNext()) {
                    commentary += s.nextLine() + "\n";
                }
            } finally {
                s.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
        assertThat("Commentary3", a.getCommentary(), is(commentary));
        String[] name = {"Kirat Boli", "N.S Nodhi", "R Rumrah", "Shashi Henra"};
        int[] runs = {8, 13, 10, 2};
        int[] balls = {4, 9, 7, 4};
        Iterator<Batsman> l = a.getBatsmanIterator();
        int i = 0;
        while (l.hasNext()) {//scoreboard
            Batsman b = l.next();
            assertThat("Scoreboard name 3", b.getName(), is(name[i]));
            assertThat("Scoreboard runs scored 3", b.getRunsScored(), is(runs[i]));
            assertThat("Scoreboard balls played 3", b.getBallsPlayed(), is(balls[i]));
            i++;
        }
        //result
        assertThat("Result 3", a.getResult(), is(Innings.Result.BALLSOVER));
        assertThat("Result runs left 3", a.getRunsLeft(), is(7));
        assertThat("Result balls left 3", a.getBallsLeft(), is(0));
        assertThat("Result wickets left 3", a.getWicketsLeft(), is(1));
    }

    /*
     * Subsequent play tests and zero and negative constructor parameters tests. Neg runs/balls, Allow add batsman? No.
     */
    @Test
    //last ball runs achieved or out. The breaks keep balls accurate.
    public void testAfterPlayStateSpecial1() {
        Innings a = new Innings("Lengaburu", 4, 40);
        a.addBatsman("Kirat Boli", new int[]{5, 30, 25, 10, 15, 1, 9, 5});
        a.addBatsman("N.S Nodhi", new int[]{10, 40, 20, 5, 10, 1, 4, 10});
        a.addBatsman("R Rumrah", new int[]{20, 30, 15, 5, 5, 1, 4, 20});
        a.addBatsman("Shashi Henra", new int[]{30, 25, 5, 0, 5, 1, 4, 30});

        int[] r = {8, 60, 55, 54, 16, 24, 83, 46, 15, 91, 9, 64, 70, 68, 75, 8, 16, 71, 28, 90, 2, 46, 4, 76};
        a.testPlay(r);
        assertThat("Result Special 1", a.getResult(), is(Innings.Result.RUNSCHASED));
        assertThat("Result runs left Special 1", a.getRunsLeft(), is(0));
        assertThat("Result balls left Special 1", a.getBallsLeft(), is(0));
        assertThat("Result wickets left Special 1", a.getWicketsLeft(), is(1));

        int[] s = {8, 60, 55, 54, 16, 24, 83, 46, 15, 91, 9, 64, 70, 68, 75, 8, 16, 71, 28, 90, 2, 46, 4, 99};
        a.testPlay(s);
        assertThat("Result Special 1", a.getResult(), is(Innings.Result.ALLOUT));
        assertThat("Result runs left Special 1", a.getRunsLeft(), is(3));
        assertThat("Result balls left Special 1", a.getBallsLeft(), is(0));
        assertThat("Result wickets left Special 1", a.getWicketsLeft(), is(0));
    }

    @Test
    public void testAfterPlayStateSpecial2() {
        Innings a = new Innings("Lengaburu", 4, 40);
        a.addBatsman("Kirat Boli", new int[]{5, 30, 25, 10, 15, 1, 9, 5});
        a.addBatsman("N.S Nodhi", new int[]{10, 40, 20, 5, 10, 1, 4, 10});
        a.addBatsman("R Rumrah", new int[]{20, 30, 15, 5, 5, 1, 4, 20});
        a.addBatsman("Shashi Henra", new int[]{30, 25, 5, 0, 5, 1, 4, 30});

        int[] r = {8, 60, 55, 54, 16, 24, 83, 46, 15, 91, 9, 64, 70, 68, 75, 8, 16, 71, 28, 90, 2, 46, 4, 76};
        a.testPlay(r);

        thrown.expect(UnsupportedOperationException.class);
        thrown.expectMessage("Cannot add more batsman once Innings is started. Make new innings to change batting line up.");
        a.addBatsman("Rumali", new int[]{1, 2, 3});
    }

    @Test
    public void testConstructor1() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Total overs and runs to win have to be greater than zero");
        Innings a = new Innings(null, 0, 40);
    }

    @Test
    public void testConstructor2() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Total overs has to be greater than zero");
        Innings a = new Innings(null, -1);
    }
}
