package bot.exceptions;

/**
 * Represents any exception thrown by the <code>Bot</code>.
 */
public class BotException extends Exception {
    /**
     * Creates a new <code>BotException</code>.
     *
     * @param msg error message.
     */
    public BotException(String msg) {
        // TODO: Move "Type 'help' to see..." into error formatter
        super("Ooops... Something went wrong:\n\n" + msg + "\n\nType 'help' to see what you can do.");
    }
}
