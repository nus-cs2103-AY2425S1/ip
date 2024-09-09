package handsome.exception;

/**
 * The InvalidTaskException class is a custom exception that extends
 * HandsomeException and is thrown when an invalid task operation is encountered
 * in the Handsome chatBot application.
 * This exception is used to indicate errors related to task creation, formatting,
 * or any invalid task-related actions specified by the user.
 */
public class InvalidTaskException extends HandsomeException {
    public InvalidTaskException(String msg) {
        super(msg);
    }
}
