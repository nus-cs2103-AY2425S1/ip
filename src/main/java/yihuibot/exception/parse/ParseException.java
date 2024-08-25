package yihuibot.exception.parse;

import yihuibot.exception.BotException;

/**
 * An Exception for all invalid user inputs.
 *
 * @author A0259080A Toh Yi Hui
 */
public class ParseException extends BotException {
    /**
     * Sets the given message as the message for this Exception.
     *
     * @param message the message of this Exception.
     */
    public ParseException(String message) {
        super(message);
    }
}
