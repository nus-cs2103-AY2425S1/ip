package exceptions;

/**
 * The NotIntegerException is thrown when a String cannot be formatted to Integer.
 * This exception is typically used in commands that require Integer input.
 */
public class NotIntegerException extends Exception {

    /**
     * Constructs a new NotIntegerException with the specified detail message.
     */
    public NotIntegerException() {
        super("Input a valid Integer stoopid");
    }
}
