package exception;

/**
 * An exception for when task data are not in correct format.
 *
 * @author Toh Yi Hui A0259080A
 */
public class IncorrectTaskFormatException extends BotException {
    public IncorrectTaskFormatException() {
        super("data.txt at root folder has corrupted data.");
    }
}
