package barney.data.exception;

/**
 * Represents an exception that is thrown when an invalid argument is passed to
 * a method or constructor. This exception is a subclass of the
 * `BarneyException` class.
 *
 * @param message The detail message that describes the specific invalid
 *                argument.
 */
public class InvalidArgumentException extends BarneyException {
    public InvalidArgumentException(String message) {
        super(message);
    }
}
