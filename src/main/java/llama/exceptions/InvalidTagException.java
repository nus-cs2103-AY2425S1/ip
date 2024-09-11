package llama.exceptions;

/**
 * Represents an invalid tag input
 */
public class InvalidTagException extends Exception {
    public InvalidTagException(String message) {
        super(message);
    }
}
