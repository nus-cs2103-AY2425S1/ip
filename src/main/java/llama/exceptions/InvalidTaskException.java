package llama.exceptions;

/**
 * Represents an invalid task input exception
 */
public class InvalidTaskException extends Exception {
    /**
     * Creates an InvalidTaskException object with the specified detail message.
     * Used when the task input is invalid.
     *
     * @param message error message to be displayed
     */
    public InvalidTaskException(String message) {
        super(message);
    }
}
