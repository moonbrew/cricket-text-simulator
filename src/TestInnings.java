import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

public class TestInnings {
    
    @Test
    public void testInitialState() {
        Innings a = new Innings ("Testteam", 4);
        assertThat (a.getRunsLeft(), is (0));// exception? Cant call?
        assertThat (a.getBallsLeft(), is (24));
        
        assertThat (a.getWicketsLeft(), is (0));//exception add batsman. atleast 2?
        assertThat (a.getBatsmanIterator(), is (0)); //ok at 0?
        assertThat (a.getResult(), is (0)); // play innings exception
    }
}
