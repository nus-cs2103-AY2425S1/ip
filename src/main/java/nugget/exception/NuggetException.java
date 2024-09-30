package nugget.exception;

/**
 * Represents an exception specific to the Nugget application.
 */
public class NuggetException extends Exception {

    /**
     * Constructs a new NuggetException with the specified detail message.
     *
     * @param message The detail message that describes the exception.
     */
    public NuggetException(String message) {
        super(message);
    }
}
