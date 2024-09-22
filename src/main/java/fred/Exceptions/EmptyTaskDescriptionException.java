package fred.Exceptions;

/**
 * The EmptyTaskDescriptionException class represents an exception that is thrown
 * when the user does not provide a description for a task.
 */
public class EmptyTaskDescriptionException extends FredException {

    /**
     * Constructs an EmptyTaskDescriptionException with a default error message indicating
     * that the task description is missing.
     */
    public EmptyTaskDescriptionException() {
        super("OOPS!!! The description of a task cannot be empty.");
    }
}
