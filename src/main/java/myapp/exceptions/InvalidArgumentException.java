package myapp.exceptions;

/**
 * Represents an exception thrown when an invalid argument is provided.
 * This exception is a subclass of {@link BingBongException}.
 */
public class InvalidArgumentException extends BingBongException {

    /**
     * Constructs an {@code InvalidArgumentException} with the specified detail message.
     *
     * @param s the detail message
     */
    public InvalidArgumentException(String s) {
        super(s);
    }
}
