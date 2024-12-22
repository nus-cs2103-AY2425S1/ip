package colress.exception;

/**
 * An exception that is thrown when there is an invalid command format entered by the user.
 */
public class InvalidCommandFormatException extends RuntimeException {
    /**
     * Constructs an InvalidCommandFormatException with the given error message.
     */
    public InvalidCommandFormatException(String message) {
        super(message);
    }
}
