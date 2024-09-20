package ratchet.exception;

/**
 * Exception for command with invalid arguments.
 */
public class InvalidCommandArgumentException extends RatchetException {
    /**
     * Constructor for an InvalidCommandArgumentException
     *
     * @param message description of error.
     */
    public InvalidCommandArgumentException(String message) {
        super("Invalid command argument: " + message);
    }
}
