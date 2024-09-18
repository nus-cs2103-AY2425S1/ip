package colress.exception;

/**
 * An exception that is thrown when there is an invalid end time for an event with respect to its start time.
 */
public class EndTimeException extends Exception {
    /**
     * Constructs an EndTimeException with the given error message.
     */
    public EndTimeException() {
        super("""
                What is this?!
                Your end time is not after your start time!
                Try again.""");
    }
}
