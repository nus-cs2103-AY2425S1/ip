package bob.exception;

/**
 * Catches invalid inputs for task related functions.
 */
public class InvalidTaskException extends Exception {
    public InvalidTaskException(String message) {
        super(message);
    }

}
