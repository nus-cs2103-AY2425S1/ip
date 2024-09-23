package exception;

/**
 * Represents an exception thrown when an unknown command is encountered.
 */
public class UnknownCommandException extends DashException {

    /**
     * Constructs an UnknownCommandException with the specified detail message.
     *
     * @param message The detail message, which is saved for later retrieval by the {@link #getMessage()} method.
     */
    public UnknownCommandException(String message) {
        super(message);
    }
}
