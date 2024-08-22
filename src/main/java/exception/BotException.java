package exception;

/**
 * Generic exception thrown by BotManager
 */
public abstract class BotException extends Exception{
    public BotException(String message) {
        super(message);
    }
}
