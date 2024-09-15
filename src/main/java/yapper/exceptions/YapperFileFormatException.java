package yapper.exceptions;

/**
 * Exception thrown when a file format is invalid or corrupted in the Yapper application.
 */
public class YapperFileFormatException extends YapperException {
    public YapperFileFormatException(String message) {
        super(message);
    }
}
