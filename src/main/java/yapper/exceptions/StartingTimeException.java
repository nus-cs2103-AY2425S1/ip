package yapper.exceptions;

/**
 * Exception thrown when a starting time is missing or not provided.
 */
public class StartingTimeException extends YapperTimeException {
    public StartingTimeException(String message) {
        super("No starting time given " + message);
    }
}
