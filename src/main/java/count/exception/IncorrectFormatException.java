package count.exception;

/**
 * IncorrectFormatException is thrown when the command given is not in a recognised format
 */
public class IncorrectFormatException extends CountException {
    public IncorrectFormatException(String msg) {
        super(msg);
    }
}
