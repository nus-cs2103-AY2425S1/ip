package Johnson.exceptions;

/**
 * Exception thrown when an index is missing for a command that requires one.
 */
public class MissingIndexException extends Exception {
    public MissingIndexException(String message) {
        super(message);
    }
}
