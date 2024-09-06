package shenhe.exception;

/**
 * Exception thrown when the task number provided by the user is invalid.
 * <p>
 * This exception is used to indicate that a task number provided does not correspond
 * to any existing task in the task list, possibly because it is out of range or not
 * a valid integer. The error message provided will inform the user that the task number
 * is invalid and prompt them to provide a valid one.
 * </p>
 */
public class InvalidTaskNumberException extends Exception {

    /**
     * Constructs a new InvalidTaskNumberException with a default error message
     * indicating that the task number is invalid.
     */
    public InvalidTaskNumberException() {
        super("Sorry traveller. Your task number seems to be invalid. Please give me a valid task number");
    }
}
