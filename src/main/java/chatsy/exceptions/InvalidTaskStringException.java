package chatsy.exceptions;

/**
 * Represents an exception that is thrown when a task string is in an invalid format.
 */
public class InvalidTaskStringException extends ChatsyException {

    /**
     * Constructs an {@code InvalidTaskStringException} with a default error message.
     */
    public InvalidTaskStringException() {
        super("The task data is in an invalid format.");
    }
}
