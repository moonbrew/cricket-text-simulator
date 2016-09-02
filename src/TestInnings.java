import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

public class TestInnings {

    @Test
    public void testInitialState() {
        Innings a = new Innings("Testteam", 4);
        assertThat("Runs", a.getRunsLeft(), is(0));// exception? Cant call?
        assertThat("Balls", a.getBallsLeft(), is(24));

        assertThat("Wickets", a.getWicketsLeft(), is(-1));//exception add batsman. atleast 2?
        assertThat("Iterator", a.getBatsmanIterator().hasNext(), is(false)); //ok at 0? Yes
        assertThat("Iterator", a.getBatsmanIterator().next(), is(0)); //throws no such element.
        assertThat("Result", a.getResult(), is(0)); // play innings exception unsupported operation exception
    }
}
