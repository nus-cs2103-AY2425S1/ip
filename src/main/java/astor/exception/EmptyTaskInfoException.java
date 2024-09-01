package astor.exception;

/**
 * Represents an exception where the user did not provide task information.
 */
public class EmptyTaskInfoException extends AstorException {
    public EmptyTaskInfoException() {
        super("Please be specific about what to do!");
    }
}
