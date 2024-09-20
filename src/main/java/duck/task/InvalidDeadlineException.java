package duck.task;

/**
 * Represents an exception thrown to indicate that the format of deadline is wrong.
 */
public class InvalidDeadlineException extends Exception {
    /**
     * Constructs new InvalidDeadlineException with specified message.
     */
    public InvalidDeadlineException(String message) {
        super(message);
    }
}
