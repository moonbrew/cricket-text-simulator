import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

public class TestInnings {
    private int[] values = {5, 10, 15, 20, 25, 30, 35, 40, 45, 50, 55, 60, 65, 70, 75, 80, 85, 90, 95, 99};


    @Test
    public void testPlayBall() {

        Batsman a = new Batsman("Testguy", new int[]{0, 0, 0, 0, 0, 0, 0, 100});
        for (int x : values) {
            a.testPlayBall((double)x/100);
            assertThat(a.getCurrentBallResult(), is((Batsman.BALL_RESULT[7])));
        }

        Batsman b = new Batsman("Testguy", new int[]{});
        Batsman c = new Batsman("Testguy", new int[]{});
        Batsman d = new Batsman("Testguy", new int[]{});
    }

}
