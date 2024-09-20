package quack.exception;

/**
 * This exception class indicates that the task description is invalid.
 */
public class InvalidDescriptionException extends Exception {

    /**
     * Creates an InvalidDescriptionException exception object.
     */
    public InvalidDescriptionException() {

        super("The task description cannot be empty!");
    }
}
