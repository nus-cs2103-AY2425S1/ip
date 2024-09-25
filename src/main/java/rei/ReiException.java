package rei;

/**
 *  Represents a custom exception for the Rei application.
 */
public class ReiException extends Exception {
    /**
     * Constructs a new ReiException with the specified detail message.
     * @param message The detail message
     */
    public ReiException(String message) {
        super(message);
    }
}
