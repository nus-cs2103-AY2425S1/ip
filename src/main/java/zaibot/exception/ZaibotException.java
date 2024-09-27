package zaibot.exception;

/**
 * This is the exception used for any Zaibot related errors.
 */
public class ZaibotException extends Exception {
    public ZaibotException(String message) {
        super(message + "\n");
    }
}
