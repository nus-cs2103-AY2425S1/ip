package bobby.exceptions;

/**
 * The {@code EmptyTodoException} class represents a specific type of {@code BobbyException}
 * that is thrown when a "todo" task is created with an empty description.
 */
public class EmptyTodoException extends BobbyException {

    /**
     * Constructs a new {@code EmptyTodoException} with a default detail message
     * indicating that the description of a "todo" task cannot be empty.
     */
    public EmptyTodoException() {
        super("The description of todo cannot be empty!");
    }
}
