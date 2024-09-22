package yapper.exception;

/**
 * Represents an exception specific to the Yapper application.
 */
public class YapperException extends Exception {

    /**
     * Constructs a new YapperException with the specified detail message.
     *
     * @param message the detail message
     */
    public YapperException(String message) {
        super(message);
    }
}
