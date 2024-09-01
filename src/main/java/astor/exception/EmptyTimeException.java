package astor.exception;

/**
 * Represents an exception where the start and end time for event tasks is not provided.
 */
public class EmptyTimeException extends AstorException {
    public EmptyTimeException() {
        super("Please be clear about the time period for the event!");
    }
}
