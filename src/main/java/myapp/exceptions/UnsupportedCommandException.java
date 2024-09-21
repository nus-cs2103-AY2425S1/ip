package myapp.exceptions;

/**
 * Represents an exception thrown when an unsupported command is encountered.
 * This exception is a subclass of {@link BingBongException}.
 */
public class UnsupportedCommandException extends BingBongException {

    /**
     * Constructs an {@code UnsupportedCommandException} with the specified detail message.
     *
     * @param s the detail message
     */
    public UnsupportedCommandException(String s) {
        super(s);
    }
}
