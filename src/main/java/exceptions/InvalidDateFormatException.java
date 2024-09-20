package exceptions;

/**
 * The InvalidDateFormatException is thrown when a String cannot be formatted to DateTime.
 * This exception is typically used in commands that require Integer input.
 */
public class InvalidDateFormatException extends Exception {

    /**
     * Constructs a new InvalidDateFormatException with the specified detail message.
     *
     * @param message The detail message explaining the reason for the exception.
     */
    public InvalidDateFormatException(String message) {
        super(message);
    }
}
