package exception;

/**
 * The InvalidTaskNumberException is thrown when a user provides an invalid task number.
 * It is a specific type of ScheduloException related to incorrect task indices.
 */
public class InvalidTaskNumberException extends ScheduloException {

    /**
     * Constructs an InvalidTaskNumberException with a default error message.
     * The exception message indicates that the provided task number is invalid.
     */
    public InvalidTaskNumberException() {
        super("Invalid task number given. Type 'list' to find out the task number.");
    }
}
