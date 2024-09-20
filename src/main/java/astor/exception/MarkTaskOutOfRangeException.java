package astor.exception;

/**
 * Represents an exception where the user provided a task index out of range.
 */
public class MarkTaskOutOfRangeException extends AstorException {
    public MarkTaskOutOfRangeException(int numberOfTasks) {
        super("Please enter a valid task number between 1 and " + numberOfTasks);
    }
}
