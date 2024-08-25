package yihuibot.exception;

/**
 * An exceptions specific to YihuiBot.
 *
 * @author Toh Yi Hui A0259080A
 */
public class BotException extends Exception {
    /**
     * Sets the given message as the message for this Exception.
     *
     * @param message the message of this Exception.
     */
    public BotException(String message) {
        super(message);
    }
}
