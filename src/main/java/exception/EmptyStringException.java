package exception;

/**
 * Exception thrown when nothing is keyed in
 */
public class EmptyStringException extends Exception {

    /**
     * Constructs a {@code EmptyStringException} with a detailed message
     */
    public EmptyStringException() {
        super("You did not type anything");
    }
}
