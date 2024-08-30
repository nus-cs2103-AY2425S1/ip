package yapper.exceptions;

/**
 * Base exception class for time-related errors in the Yapper application.
 */
public class YapperTimeException extends YapperException {
    public YapperTimeException(String errorMessage) {
        super(errorMessage);
    }
}
