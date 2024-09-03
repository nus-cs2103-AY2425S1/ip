package Alex.Exceptions;

/**
 * Exception thrown when a todo task is created with an empty description.
 */
public class EmptyTodoException extends AlexException {

    /**
     * Constructs an EmptyTodoException with a default message.
     */
    public EmptyTodoException() {
        super("Oops! Description of a todo cannot be empty!");
    }
}
