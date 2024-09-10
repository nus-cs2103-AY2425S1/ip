package llama.exceptions;

/**
 * Represents an invalid task input exception
 */
public class InvalidTaskException extends Exception {
    /**
     * Creates an InvalidTaskException object
     *
     * @param message error message
     */
    public InvalidTaskException(String message) {
        super(message);
    }
}
