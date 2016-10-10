import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import cricket.InningsController;
import cricket.Broadcaster;

public class TestInningsControllerAndBroadcaster {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testAfterReadyAddBatsman() {
        InningsController a = new InningsController("Lengaburu", 4, 40);
        a.addBatsman("Kirat Boli", new int[]{5, 30, 25, 10, 15, 1, 9, 5});
        a.addBatsman("N.S Nodhi", new int[]{10, 40, 20, 5, 10, 1, 4, 10});
        a.addBatsman("R Rumrah", new int[]{20, 30, 15, 5, 5, 1, 4, 20});
        a.addBatsman("Shashi Henra", new int[]{30, 25, 5, 0, 5, 1, 4, 30});

        a.ready();

        thrown.expect(UnsupportedOperationException.class);
        thrown.expectMessage("No longer in Setup state. Cannot add batsman.");
        a.addBatsman("Rumali", new int[]{1, 2, 3});
    }

    @Test
    public void testNotReadyPlayNextBall() {
        InningsController a = new InningsController("Lengaburu", 4, 40);
        a.addBatsman("Kirat Boli", new int[]{5, 30, 25, 10, 15, 1, 9, 5});
        a.addBatsman("N.S Nodhi", new int[]{10, 40, 20, 5, 10, 1, 4, 10});
        a.addBatsman("R Rumrah", new int[]{20, 30, 15, 5, 5, 1, 4, 20});
        a.addBatsman("Shashi Henra", new int[]{30, 25, 5, 0, 5, 1, 4, 30});


        thrown.expect(IllegalStateException.class);
        thrown.expectMessage("Can't play Innings.");
        a.playNextBall();
    }

    @Test
    public void testReadyBeforeAddBatsman1() {
        InningsController a = new InningsController("Testteam", 6);
        thrown.expect(IllegalStateException.class);
        thrown.expectMessage("Please add more batsmen.");
        a.ready();
    }

    @Test
    public void testReadyBeforeAddBatsman2() {
        InningsController a = new InningsController("Testteam", 6);
        a.addBatsman("Testguy1", new int[]{5, 30, 25, 10, 15, 1, 9, 5});
        thrown.expect(IllegalStateException.class);
        thrown.expectMessage("Please add more batsmen.");
        a.ready();
    }

    @Test
    public void testConstructor1() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Total overs and runs to win have to be greater than zero.");
        InningsController a = new InningsController(null, 0, 40);
    }

    @Test
    public void testConstructor2() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Total overs has to be greater than zero.");
        InningsController a = new InningsController(null, -1);
    }

    @Test
    public void testFirstInningsTargetRuns() {
        InningsController a = new InningsController("Testteam", 6);
        thrown.expect(UnsupportedOperationException.class);
        thrown.expectMessage("Cannot ask for runs left in first innings.");
        a.getTargetRuns();
    }

    @Test
    public void testSecondInningsTargetRuns() {
        InningsController a = new InningsController("Testteam", 6, 40);
        assertThat("Runs", a.getTargetRuns(), is(40));
    }

    @Test
    public void testOverHeaderInbetweenOver() {
        InningsController a = new InningsController("Lengaburu", 4, 40);
        a.addBatsman("Kirat Boli", new int[]{5, 30, 25, 10, 15, 1, 9, 5});
        a.addBatsman("N.S Nodhi", new int[]{10, 40, 20, 5, 10, 1, 4, 10});
        a.addBatsman("R Rumrah", new int[]{20, 30, 15, 5, 5, 1, 4, 20});
        a.addBatsman("Shashi Henra", new int[]{30, 25, 5, 0, 5, 1, 4, 30});

        a.ready();
        Broadcaster br = new Broadcaster(a);
        a.playNextBall();

        thrown.expect(UnsupportedOperationException.class);
        thrown.expectMessage("Over header is only available at the beginning of an over.");
        br.getOverHeader();
    }

    @Test
    public void testOverHeaderAfterInnings() {
        InningsController a = new InningsController("Lengaburu", 4, 40);
        a.addBatsman("Kirat Boli", new int[]{5, 30, 25, 10, 15, 1, 9, 5});
        a.addBatsman("N.S Nodhi", new int[]{10, 40, 20, 5, 10, 1, 4, 10});
        a.addBatsman("R Rumrah", new int[]{20, 30, 15, 5, 5, 1, 4, 20});
        a.addBatsman("Shashi Henra", new int[]{30, 25, 5, 0, 5, 1, 4, 30});

        a.ready();
        Broadcaster br = new Broadcaster(a);
        while (a.canPlay())
            br.getOverCommentary();

        thrown.expect(UnsupportedOperationException.class);
        thrown.expectMessage("Over header is only available at the beginning of an over.");
        br.getOverHeader();
    }
}
