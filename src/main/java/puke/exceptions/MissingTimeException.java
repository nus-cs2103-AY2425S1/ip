package puke.exceptions;

/**
 * Exception thrown when a deadline is missing its specified time.
 */
public class MissingTimeException extends PukeException {

    /**
     * Constructs a MissingTimeException with a default message.
     */
    public MissingTimeException() {
        super("OOPS!!! The deadline must have a specified time.");
    }
}
