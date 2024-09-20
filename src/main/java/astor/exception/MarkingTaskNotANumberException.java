package astor.exception;

/**
 * Represents an exception where the user did not provide a task index to mark.
 */
public class MarkingTaskNotANumberException extends AstorException {
    public MarkingTaskNotANumberException() {
        super("Please indicated clearly which task to mark!");
    }
}
