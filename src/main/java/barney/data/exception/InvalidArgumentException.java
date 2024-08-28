package barney.data.exception;

/**
 * Represents an exception thrown when an invalid argument is provided to a
 * method.
 */
public class InvalidArgumentException extends BarneyException {

    /**
     * Constructs a new InvalidArgumentException with the specified detail message.
     *
     * @param message The detail message.
     */
    public InvalidArgumentException(String message) {
        super(message);
    }
}
