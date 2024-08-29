package bobby.exception;

/**
 * UnknownCommandException is an exception thrown when an unknown task type is provided.
 */
public class UnknownCommandException extends BobbyException {

    /**
     * Constructs an UnknownCommandException with a message
     */
    public UnknownCommandException() {
        super("Please use a valid command.");
    }
}
