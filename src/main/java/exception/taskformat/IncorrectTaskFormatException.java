package exception.taskformat;

import exception.BotException;

/**
 * An exception for when task data are not in correct format.
 *
 * @author Toh Yi Hui A0259080A
 */
public class IncorrectTaskFormatException extends BotException {
    /**
     * Constructor for a new IncorrectTaskFormatException
     *
     * @param message the message for the exception.
     */
    public IncorrectTaskFormatException(String message) {
        super(message);
    }
}
