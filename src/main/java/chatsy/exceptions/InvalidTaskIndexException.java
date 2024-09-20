package chatsy.exceptions;

/**
 * Represents an exception that is thrown when an invalid task index is provided.
 */
public class InvalidTaskIndexException extends ChatsyException {

    /**
     * Constructs an {@code InvalidTaskIndexException} with a default error message.
     */
    public InvalidTaskIndexException() {
        super("Oops, invalid index provided.");
    }

    /**
     * Constructs an {@code InvalidTaskIndexException} with a custom error message.
     *
     * @param message The custom error message.
     */
    public InvalidTaskIndexException(String message) {
        super(message);
    }
}
