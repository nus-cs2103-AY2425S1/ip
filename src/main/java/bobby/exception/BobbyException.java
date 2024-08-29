package bobby.exception;

/**
 * BobbyException is an exception thrown when an error occurs while running Bobby.
 */
public class BobbyException extends Exception {

    /**
     * Constructs a BobbyException with a message.
     *
     * @param msg Message to describe the error.
     */
    public BobbyException (String msg) {
        super(msg);
    }
}
