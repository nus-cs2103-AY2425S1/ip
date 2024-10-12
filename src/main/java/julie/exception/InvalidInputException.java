package julie.exception;

/**
 * An exception that is thrown when a command is called with invalid inputs.
 */
public class InvalidInputException extends JulieException {
    /**
     * The public constructor for the exception.
     *
     * @param message The error message for the exception.
     */
    public InvalidInputException(String message) {
        super(String.format("Oh no! There seems to be an error with the input for %s", message));
    }
}
