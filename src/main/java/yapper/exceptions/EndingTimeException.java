package yapper.exceptions;

/**
 * Exception thrown when an ending time is missing or not provided.
 */
public class EndingTimeException extends YapperTimeException {
    public EndingTimeException(String message) {
        super("No ending time given " + message);
    }
}
