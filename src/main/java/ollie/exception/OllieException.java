package ollie.exception;

/**
 * Represents a general exception in the ollie.Ollie chatbot.
 */
public class OllieException extends Exception {
    /**
     * Constructs an ollie.exception.OllieException with the specified detail message.
     *
     * @param message the detail message
     */
    public OllieException(String message) {
        super(message);
    }
}