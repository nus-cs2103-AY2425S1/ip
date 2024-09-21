package puke.exceptions;

/**
 * Exception thrown when a deadline is missing its specified time.
 */
public class MissingDeadlineTimeException extends PukeException {

    /**
     * Constructs a MissingDeadlineTimeException with a default message.
     */
    public MissingDeadlineTimeException() {
        super("OOPS!!! The deadline must have a specified time.");
    }
}
