import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

public class TestBatsman {

    @Test
    public void testPlayBall1() {//checking 100%
        int[] values = {5, 10, 15, 20, 25, 30, 35, 40, 45, 50, 55, 60, 65, 70, 75, 80, 85, 90, 95, 99};
        Batsman a = new Batsman("Testguy", new int[]{0, 0, 0, 0, 0, 0, 0, 100});
        for (int x : values) {
            a.testPlayBall((double)x / 100);
            assertThat(a.getCurrentBallResult(), is((Batsman.BALL_RESULT[7])));
        }
    }

    @Test
    public void testPlayBall2() {//edge value, skipping 0s
        Batsman a = new Batsman("Testguy", new int[]{0, 0, 0, 50, 0, 0, 50, 0});

        a.testPlayBall(0);
        assertThat(a.getCurrentBallResult(), is((Batsman.BALL_RESULT[3])));
        a.testPlayBall(0.49);
        assertThat(a.getCurrentBallResult(), is((Batsman.BALL_RESULT[3])));
        a.testPlayBall(0.5);
        assertThat(a.getCurrentBallResult(), is((Batsman.BALL_RESULT[6])));
        a.testPlayBall(0.75);
        assertThat(a.getCurrentBallResult(), is((Batsman.BALL_RESULT[6])));
        a.testPlayBall(0.9999999);
        assertThat(a.getCurrentBallResult(), is((Batsman.BALL_RESULT[6])));
    }

    @Test
    public void testPlayBall3() {///all results
        Batsman a = new Batsman("Testguy", new int[]{10, 10, 20, 10, 10, 20, 10, 10});

        a.testPlayBall(0.09);
        assertThat(a.getCurrentBallResult(), is((Batsman.BALL_RESULT[0])));
        a.testPlayBall(0.19);
        assertThat(a.getCurrentBallResult(), is((Batsman.BALL_RESULT[1])));
        a.testPlayBall(0.39);
        assertThat(a.getCurrentBallResult(), is((Batsman.BALL_RESULT[2])));
        a.testPlayBall(0.49);
        assertThat(a.getCurrentBallResult(), is((Batsman.BALL_RESULT[3])));
        a.testPlayBall(0.59);
        assertThat(a.getCurrentBallResult(), is((Batsman.BALL_RESULT[4])));
        a.testPlayBall(0.79);
        assertThat(a.getCurrentBallResult(), is((Batsman.BALL_RESULT[5])));
        a.testPlayBall(0.89);
        assertThat(a.getCurrentBallResult(), is((Batsman.BALL_RESULT[6])));
        a.testPlayBall(0.99);
        assertThat(a.getCurrentBallResult(), is((Batsman.BALL_RESULT[7])));
    }
}
