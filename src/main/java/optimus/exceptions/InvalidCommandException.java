package optimus.exceptions;

/**
 * Exception for when command information is incompatible
 */
public class InvalidCommandException extends OptimusExceptions {
    /**
     * Sets error message
     *
     * @param message - error message
     */
    public InvalidCommandException(String message) {
        super(message);
    }
}
