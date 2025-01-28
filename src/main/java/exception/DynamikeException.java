package exception;

/**
 * Custom exception class for Dynamike-related exceptions.
 */
public class DynamikeException extends Exception {
    /**
     * The constructor creates a new exception.DynamikeException object.
     *
     * @param message The error message to be displayed.
     */
    public DynamikeException(String message) {
        super("Uh oh! " + message);
    }
}
