package megamind.exception;

/**
 * Exception thrown when an invalid command is encountered.
 */
public class InvalidCommandException extends Exception {

    /**
     * Constructor for InvalidCommandException
     *
     * @param message the message to be displayed
     */
    public InvalidCommandException(String message) {
        super(message);
    }
}
