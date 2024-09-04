package optimus.exceptions;

/**
 * Exception for when task number specified is incorrect
 */
public class InvalidTaskNumberException extends OptimusExceptions {
    /**
     * Sets error messages with default message
     */
    public InvalidTaskNumberException() {
        super("A task with this number does not exist.");
    }

    /**
     * Sets error message
     *
     * @param message - error message
     */
    public InvalidTaskNumberException(String message) {
        super(message);
    }
}
