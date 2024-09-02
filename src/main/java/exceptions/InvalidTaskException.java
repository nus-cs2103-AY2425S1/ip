package exceptions;

/**
 * Represents an invalid task input exception
 */
public class InvalidTaskException extends Exception {
    public InvalidTaskException(String message) {
        super(message);
    }
}
