package exception;

/**
 * Custom exception class for Dude-related exceptions.
 */
public class DudeException extends Exception {
    /**
     * The constructor creates a new exception.DudeException object.
     * 
     * @param message The error message to be displayed.
     */
    public DudeException(String message) {
        super("Uh oh! " + message);
    }
}
