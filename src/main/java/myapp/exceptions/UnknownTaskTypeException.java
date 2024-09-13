package myapp.exceptions;

/**
 * Represents an exception thrown when an unknown task type is encountered.
 * This exception is a subclass of {@link BingBongException}.
 */
public class UnknownTaskTypeException extends BingBongException {

    /**
     * Constructs an {@code UnknownTaskTypeException} with the specified detail message.
     *
     * @param s the detail message
     */
    public UnknownTaskTypeException(String s) {
        super(s);
    }
}
