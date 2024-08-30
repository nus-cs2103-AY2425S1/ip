package puke.exceptions;

/**
 * Exception thrown when an event is missing its start or end time.
 */
public class MissingEventTimeException extends PukeException {

    /**
     * Constructs a MissingEventTimeException with a default message.
     */
    public MissingEventTimeException() {
        super("OOPS!!! An event must have both start and end times specified.");
    }
}
