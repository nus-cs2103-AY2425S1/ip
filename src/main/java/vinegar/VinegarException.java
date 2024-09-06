package vinegar;

/**
 * Custom exception class for the Vinegar application.
 * <p>
 * Thrown when the application encounters an error or invalid input during command processing.
 */
public class VinegarException extends Exception {

    /**
     * Constructs a new VinegarException with the specified error message.
     *
     * @param message The error message to include with the exception.
     */
    public VinegarException(String message) {
        super(message);
    }
}
