package yihuibot.exception.executable;

import yihuibot.exception.BotException;

/**
 * An Exception for all errors encountered when executing Executable.
 *
 * @author Toh Yi Hui A0259080A
 */
public class ExecutableException extends BotException {
    /**
     * Constructor for a new ExecutableException.
     *
     * @param message the message of the Exception.
     */
    public ExecutableException(String message) {
        super(message);
    }
}
