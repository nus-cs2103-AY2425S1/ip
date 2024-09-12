package orion.orionexceptions;

/**
 * Exception thrown when an invalid todo command is provided.
 *
 * <p>
 * This exception is thrown when the input does not match the expected format
 * for creating a todo task.
 * </p>
 *
 * @author Pradyumna
 */
public class InvalidTodoException extends OrionException {

    /**
     * Creates a new InvalidTodoException with a message showing the invalid input
     * and the correct format for using the todo command.
     *
     * @param message the invalid input that triggered the exception.
     */
    public InvalidTodoException(String message) {
        super("You put: " + message + ". Please use 'todo [task]' to create a todo task.");
    }
}
