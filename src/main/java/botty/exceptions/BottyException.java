package botty.exceptions;

/**
 * Generic exception class for exceptions relating to Botty
 */
public class BottyException extends Exception {
    /**
     * Constructs a new {@code BottyException} with the specified detail message.
     *
     * @param message the detail message.
     */
    public BottyException(String message) {
        super(message);
    }
}
