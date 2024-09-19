package winner;

/**
 * Represents a custom exception used in the Winner bot.
 * This exception is thrown when specific error conditions to Winner bot are met.
 */
public class WinnerException extends Exception {
    /**
     * Creates a new WinnerException with the specific error message.
     *
     * @param message Error message.
     */
    public WinnerException(String message) {
        super(message);
    }
}
