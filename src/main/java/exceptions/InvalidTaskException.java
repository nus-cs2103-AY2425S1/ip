package exceptions;

/**
 * Represents a invalid task input exception
 */
public class InvalidTaskException extends Exception {
    public InvalidTaskException(String message) {
        super(message);
    }
}
