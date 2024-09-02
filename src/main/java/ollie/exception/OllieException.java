package ollie.exception;

/**
 * OllieException is an exception that is thrown when an error occurs.
 */
public class OllieException extends Exception {

    /**
     * Constructs an ollie.exception.OllieException
     * The message can be specified to describe the error.
     *
     * @param message the message describing the error
     */
    public OllieException(String message) {
        super(message);
    }
}
