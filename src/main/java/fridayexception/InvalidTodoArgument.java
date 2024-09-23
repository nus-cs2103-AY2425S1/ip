package fridayexception;

/**
 * Represents an exception where the todo argument is invalid.
 */
public class InvalidTodoArgument extends FridayException {

    /**
     * Constructs an InvalidTodoArgument exception.
     */
    public InvalidTodoArgument() {
        super("The description of a todo cannot be empty.");
    }
}
