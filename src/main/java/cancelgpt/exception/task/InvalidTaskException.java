package cancelgpt.exception.task;

/**
 * Represents an Exception for invalid task.
 */
public class InvalidTaskException extends Exception {
    /**
     * Initialises InvalidTaskException with message indicating invalid task.
     */
    public InvalidTaskException(String message) {
        super(message);
    }
}
