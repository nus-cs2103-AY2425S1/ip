package elara.task;

/**
 * Represents an exception that is thrown when the user provides an invalid or empty input for a ToDo task.
 */
public class ToDoException extends InvalidInputException {

    /**
     * Constructs a new ToDoException with a predefined error message.
     */
    public ToDoException() {
        super("The description of a todo cannot be empty! Please append the description of your todo task.");
    }
}
