package ratchet.exception;

/**
 * Exception for invalid command
 */
public class InvalidCommandException extends RatchetException {
    /**
     * Constructor for an InvalidCommandException
     *
     * @param message description of error.
     */
    public InvalidCommandException(String message) {
        super("Invalid command: " + message);
    }
}
