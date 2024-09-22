package ui;

/**
 * Custom exception class for handling errors specific to the bot.
 */
public class BotException extends Exception {

    /**
     * Constructs a new BotException with the specified detail message.
     *
     * @param msg The detail message for the exception.
     */
    public BotException(String msg) {
        super(msg);
    }

}
