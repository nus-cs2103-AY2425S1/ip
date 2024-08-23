package duke.exceptions;

/**
 * Represents an exception that is thrown when a Todo task is created without a description.
 * This exception is used to signal that the task description cannot be empty.
 */
public class EmptyTodoDescriptionException extends Exception {

    /**
     * Constructs an EmptyTodoDescriptionException with a specified error message.
     *
     * @param e The error message describing the exception.
     */
    public EmptyTodoDescriptionException(String e) {
        super(e);
    }
}
