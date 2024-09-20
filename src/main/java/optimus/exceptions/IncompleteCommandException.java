package optimus.exceptions;

/**
 * Exception for when command is left incomplete
 */
public class IncompleteCommandException extends OptimusExceptions {
    /**
     * Sets error message
     *
     * @param message - error message
     */
    public IncompleteCommandException(String message) {
        super(message);
    }
}
