package exception;

/**
 * An exception for when user's input an invalid, or nil argument to a command
 * when argument(s) are expected.
 *
 * @author Toh Yi Hui A0259080A
 */
public class InvalidArgumentException extends BotException {
    public InvalidArgumentException(String message) {
        super(message);
    }
}
