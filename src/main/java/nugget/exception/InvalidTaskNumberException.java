package nugget.exception;

/**
 * Represents an exception that is thrown when an invalid task number is provided.
 */
public class InvalidTaskNumberException extends NuggetException {

    /**
     * Constructs an <code>InvalidTaskNumberException</code> with a default error message.
     */
    public InvalidTaskNumberException() {
        super("OOPS!!! The task number you provided is invalid.");
    }
}
