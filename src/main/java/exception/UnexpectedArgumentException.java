package exception;

/**
 * An exception for when user's input argument(s) when no argument are expected.
 *
 * @author Toh Yi Hui A0259080A
 */
public class UnexpectedArgumentException extends BotException {
    public UnexpectedArgumentException(String message) {
        super(message);
    }
}
