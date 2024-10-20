package papagu.ui;


/**
 * Represents an exception thrown when the deadline is not in the correct format
 */
public class IllegalDeadlineException extends IllegalArgumentException {
    public IllegalDeadlineException(String message) {
        super(message);
    }
}
