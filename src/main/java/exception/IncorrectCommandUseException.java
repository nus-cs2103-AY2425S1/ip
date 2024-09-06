package exception;

/**
 * Represents an exception thrown when a command is used incorrectly.
 */
public class IncorrectCommandUseException extends DashException {

    /**
     * Constructs an IncorrectCommandUseException with the specified detail message.
     *
     * @param message The detail message, which is saved for later retrieval by the {@link #getMessage()} method.
     */
    public IncorrectCommandUseException(String message) {
        super(message);
    }
}
