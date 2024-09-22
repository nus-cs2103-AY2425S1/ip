package puke.exceptions;

/**
 * Exception thrown when the task number format is invalid.
 */
public class InvalidTaskNumberFormatException extends PukeException {

    /**
     * Constructs an InvalidTaskNumberFormatException with a default message.
     */
    public InvalidTaskNumberFormatException() {
        super("Invalid task number format!!!!");
    }
}
