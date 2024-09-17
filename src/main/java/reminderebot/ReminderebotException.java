package reminderebot;

/**
 * Custom Exception for Reminderebot.
 */
public class ReminderebotException extends Exception {
    /**
     * Initialise a custom exception.
     * @param message
     */
    public ReminderebotException(String message) {
        super("Oops! " + message);
    }
}
