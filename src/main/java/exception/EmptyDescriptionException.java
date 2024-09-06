package exception;

/**
 * Represents an exception thrown when a description is found to be empty.
 */
public class EmptyDescriptionException extends DashException {

    /**
     * Constructs an EmptyDescriptionException with the specified detail message.
     *
     * @param message The detail message, which is saved for later retrieval by the {@link #getMessage()} method.
     */
    public EmptyDescriptionException(String message) {
        super(message);
    }
}
