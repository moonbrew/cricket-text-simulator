package cricket;

/**
 * Represents the result of a ball played.
 */
public enum BallResult {
    ZERO(0),
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    OUT(-1) {

        public boolean isRuns() {
            return false;
        }

        public boolean isOdd() throws UnsupportedOperationException {
            throw new UnsupportedOperationException("OUT is not a run");
        }

        public int getRuns() throws UnsupportedOperationException {
            throw new UnsupportedOperationException("OUT is not a run.");
        }
    };

    private int runs;

    BallResult(int runs) {
        this.runs = runs;
    }

    public boolean isRuns() {
        return true;
    }

    public boolean isOdd() {
        return runs % 2 != 0;
    }

    public int getRuns() {
        return runs;
    }
}
