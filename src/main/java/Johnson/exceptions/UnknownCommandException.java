package Johnson.exceptions;

/**
 * Exception thrown when user inputs an unknown command.
 */
public class UnknownCommandException extends Exception {
    public UnknownCommandException(String message) {
        super(message);
    }
}
