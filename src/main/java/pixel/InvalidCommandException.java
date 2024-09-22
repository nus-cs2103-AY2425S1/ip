package pixel;

/**
 * Exception that is thrown when user inputs
 * command that is invalid
 */
public class InvalidCommandException extends Exception {
    /**
     * Constructor method for InvalidCommandException
     *
     * @param message message to be printed when error is thrown
     */
    public InvalidCommandException(String message) {
        super(message);
    }
}
