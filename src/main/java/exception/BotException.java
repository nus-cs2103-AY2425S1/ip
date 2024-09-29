package exception;

/**
 * Generic exception thrown by BotManager.
 *
 * @author dwsc37
 */
public abstract class BotException extends Exception {
    public BotException(String message) {
        super(message);
    }
}
