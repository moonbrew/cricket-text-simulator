import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static cricket.InningsController.InningsState.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.Test;

import cricket.InningsController;
import cricket.Batsman;
import cricket.Broadcaster;

public class TestInnings {
    /*
     * Play tests
     */
    @Test
    public void testInnings1() {
        String commentaryExpected = "\n";
        try {
            Scanner s = new Scanner(new File("src/Commentary1.txt"));
            try {
                while (s.hasNext()) {
                    commentaryExpected += s.nextLine() + "\n";
                }
            } finally {
                s.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }

        InningsController a = new InningsController("Lengaburu", 4, 40);
        a.addBatsman("Kirat Boli", new int[]{5, 30, 25, 10, 15, 1, 9, 5});
        a.addBatsman("N.S Nodhi", new int[]{10, 40, 20, 5, 10, 1, 4, 10});
        a.addBatsman("R Rumrah", new int[]{20, 30, 15, 5, 5, 1, 4, 20});
        a.addBatsman("Shashi Henra", new int[]{30, 25, 5, 0, 5, 1, 4, 30});

        int[] r = {8, 60, 55, 54, 16, 24, 83, 46, 15, 91, 9, 64, 70, 68, 75, 8, 16, 71, 28, 90, 2, 46, 76};
        a.test(r);
        Broadcaster br = new Broadcaster(a);
        String commentaryResult = "";
        while (a.canPlay())
            commentaryResult += "\n" + br.getOverHeader() + "\n" + br.getOverCommentary();


        assertThat("Commentary1", commentaryResult, is(commentaryExpected));
        String[] name = {"Kirat Boli", "N.S Nodhi", "R Rumrah", "Shashi Henra"};
        int[] runs = {22, 7, 11, 1};
        int[] balls = {10, 5, 6, 2};
        ArrayList<Batsman> l = a.getLineUp();
        int i = 0;
        for (Batsman b : l) {//scoreboard
            assertThat("Scoreboard name 1", b.getName(), is(name[i]));
            assertThat("Scoreboard runs scored 1", b.getRunsScored(), is(runs[i]));
            assertThat("Scoreboard balls played 1", b.getBallsPlayed(), is(balls[i]));
            i++;
        }
        //result
        assertThat("Result 1", a.getInningsState(), is(TARGETACHIEVED));
        assertThat("Result runs 1", a.getCurrentRuns(), is(41));
        assertThat("Result balls 1", a.getCurrentBalls(), is(23));
        assertThat("Result wickets 1", a.getCurrentWicket(), is(2));
    }

    @Test
    public void testInnings2() {
        String commentaryExpected = "\n";
        try {
            Scanner s = new Scanner(new File("src/Commentary2.txt"));
            try {
                while (s.hasNext()) {
                    commentaryExpected += s.nextLine() + "\n";
                }
            } finally {
                s.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }

        InningsController a = new InningsController("Lengaburu", 4, 40);
        a.addBatsman("Kirat Boli", new int[]{5, 30, 25, 10, 15, 1, 9, 5});
        a.addBatsman("N.S Nodhi", new int[]{10, 40, 20, 5, 10, 1, 4, 10});
        a.addBatsman("R Rumrah", new int[]{20, 30, 15, 5, 5, 1, 4, 20});
        a.addBatsman("Shashi Henra", new int[]{30, 25, 5, 0, 5, 1, 4, 30});
        int[] r = {37, 70, 37, 38, 45, 67, 96, 32, 94, 23, 55, 44, 1, 80};
        a.test(r);
        Broadcaster br = new Broadcaster(a);
        String commentaryResult = "";
        while (a.canPlay())
            commentaryResult += "\n" + br.getOverHeader() + "\n" + br.getOverCommentary();

        assertThat("Commentary2", commentaryResult, is(commentaryExpected));
        String[] name = {"Kirat Boli", "N.S Nodhi", "R Rumrah", "Shashi Henra"};
        int[] runs = {15, 0, 1, 3};
        int[] balls = {7, 1, 1, 5};

        ArrayList<Batsman> l = a.getLineUp();
        int i = 0;
        for (Batsman b : l) {//scoreboard
            assertThat("Scoreboard name 2", b.getName(), is(name[i]));
            assertThat("Scoreboard runs scored 2", b.getRunsScored(), is(runs[i]));
            assertThat("Scoreboard balls played 2", b.getBallsPlayed(), is(balls[i]));
            i++;
        }
        //result
        assertThat("Result 2", a.getInningsState(), is(ALLOUT));
        assertThat("Result runs 2", a.getCurrentRuns(), is(19));
        assertThat("Result balls 2", a.getCurrentBalls(), is(14));
        assertThat("Result wickets 2", a.getCurrentWicket(), is(3));
    }

    @Test
    public void testInnings3() {
        String commentaryExpected = "\n";
        try {
            Scanner s = new Scanner(new File("src/Commentary3.txt"));
            try {
                while (s.hasNext()) {
                    commentaryExpected += s.nextLine() + "\n";
                }
            } finally {
                s.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }

        InningsController a = new InningsController("Lengaburu", 4, 40);
        a.addBatsman("Kirat Boli", new int[]{5, 30, 25, 10, 15, 1, 9, 5});
        a.addBatsman("N.S Nodhi", new int[]{10, 40, 20, 5, 10, 1, 4, 10});
        a.addBatsman("R Rumrah", new int[]{20, 30, 15, 5, 5, 1, 4, 20});
        a.addBatsman("Shashi Henra", new int[]{30, 25, 5, 0, 5, 1, 4, 30});
        int[] r = {53, 9, 20, 85, 24, 97, 15, 14, 43, 33, 55, 70, 10, 30, 27, 57, 91, 3, 59, 82, 37, 29, 13, 57};
        a.test(r);
        Broadcaster br = new Broadcaster(a);
        String commentaryResult = "";
        while (a.canPlay())
            commentaryResult += "\n" + br.getOverHeader() + "\n" + br.getOverCommentary();

        assertThat("Commentary3", commentaryResult, is(commentaryExpected));
        String[] name = {"Kirat Boli", "N.S Nodhi", "R Rumrah", "Shashi Henra"};
        int[] runs = {8, 13, 10, 2};
        int[] balls = {4, 9, 7, 4};

        ArrayList<Batsman> l = a.getLineUp();
        int i = 0;
        for (Batsman b : l) {//scoreboard
            assertThat("Scoreboard name 3", b.getName(), is(name[i]));
            assertThat("Scoreboard runs scored 3", b.getRunsScored(), is(runs[i]));
            assertThat("Scoreboard balls played 3", b.getBallsPlayed(), is(balls[i]));
            i++;
        }
        //result
        assertThat("Result 3", a.getInningsState(), is(BALLSOVER));
        assertThat("Result runs 3", a.getCurrentRuns(), is(33));
        assertThat("Result balls 3", a.getCurrentBalls(), is(24));
        assertThat("Result wickets 3", a.getCurrentWicket(), is(2));
    }

    /*
     * Subsequent play tests
     */
    @Test
    //last ball runs achieved or out. The breaks keep balls accurate.
    public void testInningsResetLastBallWinAndState() {
        InningsController a = new InningsController("Lengaburu", 4, 40);
        assertThat("State after creation", a.getInningsState(), is(SETUP));
        a.addBatsman("Kirat Boli", new int[]{5, 30, 25, 10, 15, 1, 9, 5});
        a.addBatsman("N.S Nodhi", new int[]{10, 40, 20, 5, 10, 1, 4, 10});
        a.addBatsman("R Rumrah", new int[]{20, 30, 15, 5, 5, 1, 4, 20});
        a.addBatsman("Shashi Henra", new int[]{30, 25, 5, 0, 5, 1, 4, 30});

        a.ready();
        assertThat("State after ready", a.getInningsState(), is(READY));
        int[] r = {8, 60, 55, 54, 16, 24, 83, 46, 15, 91, 9, 64, 70, 68, 75, 8, 16, 71, 28, 90, 2, 46, 4, 76};
        a.test(r);
        assertThat("State for testing", a.getInningsState(), is(TESTING));
        Broadcaster br = new Broadcaster(a);
        while (a.canPlay())
            a.playNextBall();

        assertThat("State last ball win", a.getInningsState(), is(TARGETACHIEVED));//last ball win
        assertThat("Runs last ball win", a.getCurrentRuns(), is(41));
        assertThat("Balls last ball win", a.getCurrentBalls(), is(24));
        assertThat("Wickets last ball win", a.getCurrentWicket(), is(2));

        String commentaryExpected = "\n";
        try {
            Scanner s = new Scanner(new File("src/Commentary4.txt"));
            try {
                while (s.hasNext()) {
                    commentaryExpected += s.nextLine() + "\n";
                }
            } finally {
                s.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
        a.ready();
        assertThat("State after ready", a.getInningsState(), is(READY));
        int[] s = {8, 60, 55, 54, 16, 24, 83, 46, 15, 91, 9, 64, 70, 68, 75, 8, 16, 71, 28, 90, 2, 46, 4, 99};
        a.test(s);
        assertThat("State for testing", a.getInningsState(), is(TESTING));
        String commentaryResult = "";
        while (a.canPlay())
            commentaryResult += "\n" + br.getOverHeader() + "\n" + br.getOverCommentary();

        assertThat("Commentary second run", commentaryResult, is(commentaryExpected));

        String[] name = {"Kirat Boli", "N.S Nodhi", "R Rumrah", "Shashi Henra"};
        int[] runs = {18, 7, 11, 1};
        int[] balls = {11, 5, 6, 2};
        ArrayList<Batsman> l = a.getLineUp();
        int i = 0;
        for (Batsman b : l) {//scoreboard
            assertThat("Scoreboard name repeat", b.getName(), is(name[i]));
            assertThat("Scoreboard runs scored repeat", b.getRunsScored(), is(runs[i]));
            assertThat("Scoreboard balls played repeat", b.getBallsPlayed(), is(balls[i]));
            i++;
        }


        assertThat("State last ball allout", a.getInningsState(), is(ALLOUT));
        assertThat("Runs last ball allout", a.getCurrentRuns(), is(37));
        assertThat("Balls last ball allout", a.getCurrentBalls(), is(24));
        assertThat("Wickets last ball allout", a.getCurrentWicket(), is(3));
    }
}
