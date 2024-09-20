package bot.exceptions;

import bot.constants.Message;

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
        super(Message.ERROR + "\n\n" + msg + "\n\n" + Message.HELP);
    }
}
