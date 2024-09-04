package optimus.exceptions;

/**
 * Exception for when deadline date is of incorrect format
 */
public class InvalidDateFormatException extends OptimusExceptions {
    /**
     * Sets error message
     *
     * @param message - error message
     */
    public InvalidDateFormatException(String message) {
        super(message);
    }
}
