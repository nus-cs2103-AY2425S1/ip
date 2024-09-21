package myapp.exceptions;

/**
 * Represents an exception thrown when an invalid format is encountered.
 * This exception is a subclass of {@link BingBongException}.
 */
public class InvalidFormatException extends BingBongException {

    /**
     * Constructs an {@code InvalidFormatException} with the specified detail message.
     *
     * @param s the detail message
     */
    public InvalidFormatException(String s) {
        super(s);
    }
}
