public class LevelHundredException extends RuntimeException {
    private String message;
    public LevelHundredException(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "OOPS!!! " + this.message;
    }
}