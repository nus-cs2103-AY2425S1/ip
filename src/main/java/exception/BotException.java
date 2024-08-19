package exception;

/**
 * An exceptions specific to YihuiBot. Not serious enough to terminate the bot.
 *
 * @author Toh Yi Hui A0259080A
 */
public class BotException extends Exception {
    public BotException(String message) {
        super(message);
    }
}
