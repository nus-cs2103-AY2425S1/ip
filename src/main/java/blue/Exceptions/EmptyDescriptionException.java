package blue.Exceptions;

/**
 * Thrown to indicate that a task description is empty when it is required.
 */
public class EmptyDescriptionException extends Exception {

    /**
     * Constructs an {@code EmptyDescriptionException} with the specified detail message.
     * @param message The detail message, saved for later retrieval by the {@link Throwable#getMessage()} method.
     */
    public EmptyDescriptionException(String message) {
        super(message);
    }
}
