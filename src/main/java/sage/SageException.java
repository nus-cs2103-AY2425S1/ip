package sage;

/**
 * Custom exception class for handling errors in the Sage application.
 */
public class SageException extends Exception {
    public SageException(String message) {
        super(message);
    }
}
