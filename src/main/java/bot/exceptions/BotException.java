package bot.exceptions;

/**
 * Represents any exception thrown by the <code>Bot</code>.
 *
 * @author mongj
 */
public class BotException extends Exception {
    /**
     * Creates a new <code>BotException</code>.
     *
     * @param msg error message.
     */
    public BotException(String msg) {
        super(msg);
    }
}
