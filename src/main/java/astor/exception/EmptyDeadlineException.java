package astor.exception;

/**
 * Represents an exception where the deadline for deadline tasks is not given.
 */
public class EmptyDeadlineException extends AstorException {
    public EmptyDeadlineException() {
        super("Please input your deadline!");
    }
}
