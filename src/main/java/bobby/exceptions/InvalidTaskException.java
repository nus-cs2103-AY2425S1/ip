package bobby.exceptions;

/**
 * Represents an exception thrown when an invalid task is added to the task list.
 */
public class InvalidTaskException extends BobbyException {

    /**
     * Constructs a new {@code DuplicateTaskException} with the specified detail message.
     *
     * @param message the detail message
     */
    public InvalidTaskException(String message) {
        super(message);
    }
}
