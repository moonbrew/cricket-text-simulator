import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import cricket.Batsman;
import static org.hamcrest.CoreMatchers.is;

public class TestBatsman {

    /*
     * Initialization tests
     */
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testBatsmanConstructor2() { //check same length as results(8)
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(is("Probability array should be of length 8"));
        Batsman a = new Batsman("Testguy", new int[]{10, 10, 10, 10, 60});
    }

    @Test
    public void testBatsmanConstructor3() {// total 100
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(is("Probabilities need to add up to 100"));
        Batsman a = new Batsman("Testguy", new int[8]);
    }

    @Test
    public void testBatsmanConstructor4() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(is("Probabilities need to add up to 100"));
        Batsman a = new Batsman("Testguy", new int[]{10, 10, 10, 10, 10, 10, 10, 10});
    }

    @Test
    public void testBatsmanConstructor5() {// no negatives 
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(is("Probability cannot be negative"));
        Batsman a = new Batsman("Testguy", new int[]{10, 20, 30, 30, 10, -10, 20, -10});
    }
}
