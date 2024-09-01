package ahmad.exceptions.deadline;

import ahmad.exceptions.AhmadException;

/**
 * Exception class for invalid deadline arguments.
 */
public class DeadlineInvalidArgsException extends AhmadException {
    /**
     * Constructs a DeadlineInvalidArgsException instance.
     */
    public DeadlineInvalidArgsException() {
        super("That is not a valid \"deadline\" command");
    }
}
