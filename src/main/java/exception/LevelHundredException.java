package exception;

/**
 * The LevelHundredException class is the base class for all exceptions within the project
 */
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