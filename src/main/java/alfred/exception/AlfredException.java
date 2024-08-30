package alfred.exception;

/**
 * Represents an exception specific to the Alfred application.
 */
public class AlfredException extends Exception {

    /**
     * Constructs an AlfredException with the specified detail message.
     *
     * @param msg The detail message, which provides information about the cause of the exception.
     */
    public AlfredException(String msg) {
        super(msg);
    }
}
