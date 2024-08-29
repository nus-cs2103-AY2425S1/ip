package Jard;

/**
 * An exception specific to the Jard chatbot application.
 * This exception is thrown when the application encounters an error
 */
public class JardException extends RuntimeException {
    /**
     * Constructs a new JardException with the specified detail message.
     *
     * @param message The detail message, saved for later retrieval by the {@link #getMessage()} method.
     */
    public JardException(String message) {
        super(message);
    }
}
