package exception;

/**
 * Represents an exception thrown when an incorrect index is encountered.
 */
public class WrongIndexException extends DashException {

    /**
     * Constructs a WrongIndexException with the specified detail message.
     *
     * @param message The detail message, which is saved for later retrieval by the {@link #getMessage()} method.
     */
    public WrongIndexException(String message) {
        super(message);
    }
}
