package monobot.exception;

/**
 * Represents the exception class to handle errors.
 */
public class MonoBotException extends Exception {
    private String message;

    /**
     * Constructs a MonoBotException with the specified message to notify user.
     */
    public MonoBotException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String toString() {
        return this.message;
    }
}
