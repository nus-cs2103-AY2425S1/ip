package yapper.exceptions;

/**
 * Exception thrown when there is an invalid format in the Yapper chatbot application.
 */
public class YapperFormatException extends YapperException {
    public YapperFormatException(String message) {
        super("Invalid format detected " + message);
    }
}
