import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

public class TestInnings {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

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
    public void testInitialStateResult() {
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

        //add batsman. Atleast 2?
        thrown.expect(IllegalStateException.class);
        thrown.expectMessage("Please add batsman first.");
        a.getWicketsLeft();
    }

    @Test
    public void testInitialState() {
        Innings a = new Innings("Testteam", 6);
        assertThat("Balls", a.getBallsLeft(), is(36));
    }
}
