package skibidi;

/**
 * Represents an exception specific to the Skibidi program.
 */
public class SkibidiException extends Exception {
    /**
     * Creates a new SkibidiException.
     *
     * @param message The error message.
     */
    public SkibidiException(String message) {
        super(message);
    }
}
