package duke.exceptions;

/**
 * Represents an exception that is thrown when a negative priority for a task.
 */
public class InvalidPriorityException extends Exception {

    /**
     * Constructs an InvalidPriorityException with a specified error message.
     *
     * @param e The error message describing the exception.
     */
    public InvalidPriorityException(String e) {
        super(e);
    }
}
