package toothless.exceptions;

/**
 * Represents an exception when the timeline is invalid.
 */
public class InvalidTimelineException extends ToothlessExceptions {

    /**
     * Constructor for InvalidTimelineException.
     */
    public InvalidTimelineException(String message) {
        super("The timeline is invalid as " + message);
    }
}
