package nugget.exception;

/**
 * Represents an exception that is thrown when a task description is empty.
 */
public class EmptyDescriptionException extends NuggetException {

    /**
     * Constructs an <code>EmptyDescriptionException</code> with a default error message.
     */
    public EmptyDescriptionException() {
        super("OOPS!!! The description of a task cannot be empty.");
    }
}
