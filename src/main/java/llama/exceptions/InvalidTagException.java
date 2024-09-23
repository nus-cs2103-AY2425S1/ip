package llama.exceptions;

/**
 * Represents an invalid tag input
 */
public class InvalidTagException extends Exception {
    /**
     * Creates an InvalidTagException with the specified detail message.
     * Used when the tag input is invalid.
     *
     * @param message error message to be displayed.
     */
    public InvalidTagException(String message) {
        super(message);
    }
}
