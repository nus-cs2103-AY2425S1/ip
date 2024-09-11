package Johnson.exceptions;

/**
 * Exception thrown when an index is missing for a command that requires one.
 */
public class MissingIndexException extends Exception {

    public static final String MISSING_INDEX_MESSAGE = "You forgot to specify the index of the task you want to mark!";
    public MissingIndexException(String message) {
        super(message);
    }
}
