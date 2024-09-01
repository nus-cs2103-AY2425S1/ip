package barney.data.exception;

/**
 * Represents an exception thrown when an invalid command is entered by the
 * user.
 */
public class InvalidCommandException extends BarneyException {

    /**
     * Constructs a new InvalidCommandException with the specified detail message.
     *
     * @param message The detail message.
     */
    public InvalidCommandException(String message) {
        super(message);
    }
}
