package dook;

/**
 * Custom exception class for handling exceptions specific to the Dook application.
 */
public class DookException extends Exception {

    /**
     * Constructs a new DookException with the specified detail message.
     *
     * @param message The detail message.
     */
    public DookException(String message) {
        super(message);
    }
}
