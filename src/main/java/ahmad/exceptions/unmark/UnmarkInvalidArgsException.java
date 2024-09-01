package ahmad.exceptions.unmark;

import ahmad.exceptions.AhmadException;

/**
 * Exception class for invalid unmark arguments.
 */
public class UnmarkInvalidArgsException extends AhmadException {
    /**
     * Constructs an UnmarkInvalidArgsException instance.
     */
    public UnmarkInvalidArgsException() {
        super("That is not a valid \"unmark\" command");
    }
}
