package yapper.exceptions;

/**
 * Base exception class for all exceptions in the Yapper chatbot application.
 */
public class YapperException extends RuntimeException {
    public YapperException(String errorMessage) {
        super(errorMessage);
    }
}
