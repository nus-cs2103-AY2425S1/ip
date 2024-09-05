package exception;

/**
 * Exception thrown when nothing is keyed in
 */
public class EmptyStringException extends HyperionException {

    /**
     * Constructs a {@code EmptyStringException} with a detailed message
     */
    public EmptyStringException() {
        super("You did not type anything");
    }
}
