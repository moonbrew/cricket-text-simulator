import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

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

        //is null! play innings exception unsupported operation exception.
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
     * After play state tests
     */
    @Test
    public void testAfterPlayState(){
        Innings a = new Innings("Lengaburu", 4, 40);
        a.addBatsman("Kirat Boli", new int[]{5, 30, 25, 10, 15, 1, 9, 5});
        a.addBatsman("N.S Nodhi", new int[]{10, 40, 20, 5, 10, 1, 4, 10});
        a.addBatsman("R Rumrah", new int[]{20, 30, 15, 5, 5, 1, 4, 20});
        a.addBatsman("Shashi Henra", new int[]{30, 25, 5, 0, 5, 1, 4, 30});
        
        int [] r = {8, 60, 55, 54, 16, 24,
        83, 46, 15, 91, 9, 64,
        70, 68, 75, 8, 16, 71,
        28, 90, 2, 46, 76};
        a.testPlay(r);
    }
}
