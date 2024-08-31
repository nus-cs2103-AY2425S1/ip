package fridayException;

/**
 * Represents an exception where the todo argument is invalid.
 */
public class InvalidTodoArgument extends FridayException {
    public InvalidTodoArgument() {
        super("The description of a todo cannot be empty.");
    }
}
