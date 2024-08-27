package megamind.exception;

/**
 * Exception thrown when a missing parameter is encountered.
 */
public class MissingParameterException extends Exception {

    /**
     * Constructor for MissingParameterException
     *
     * @param message the message to be displayed
     */
    public MissingParameterException(String message) {
        super(message);
    }
}
