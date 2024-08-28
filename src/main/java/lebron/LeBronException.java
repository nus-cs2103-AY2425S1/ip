package lebron;

/**
 * Custom exception class for the LeBron application.
 * Used to handle specific errors related to the application.
 */
public class LeBronException extends Exception {
    
    /**
     * Creates a new LeBronException with the given error message.
     *
     * @param message The error message to be displayed.
     */
    public LeBronException(String message) {
        super(message);
    }
    
}
