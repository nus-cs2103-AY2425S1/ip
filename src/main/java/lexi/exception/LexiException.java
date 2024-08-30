package lexi.exception;

/**
 * Represents an exception specific to the Lexi application.
 * This exception is thrown when the application encounters an error that needs to be handled gracefully.
 */
public class LexiException extends Exception {

    /**
     * Constructs a new LexiException with the specified detail message.
     *
     * @param message The detail message for the exception.
     */
    public LexiException(String message) {
        super(message);
    }
}
