package thanos.exceptions;

/**
 * Exception thrown to indicate that a task is invalid.
 */
public class InvalidTaskException extends Exception {
    /**
     * Constructs an {@code InvalidTaskException} with the specified detail message.
     *
     * @param message the detail message to be included in the exception.
     */
    public InvalidTaskException(String message) {
        super(message);
    }
}
