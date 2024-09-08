package reminderebot;

/**
 * Custom Exception for Reminderebot.
 */
public class ReminderebotException extends Exception{
    public ReminderebotException(String message) {
        super("Oops! " + message);
    }
}
