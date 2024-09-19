package skywalker.exception;

/**
 * throw an exception when it is an unknown command.
 */
public class UnknownCommandException extends Exception {
    public UnknownCommandException(String message) {
        super(message);
    }
}
