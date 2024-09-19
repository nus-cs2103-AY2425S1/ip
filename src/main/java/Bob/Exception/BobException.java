package bob.exception;

/**
 * Represents a custom exception specific to the Bob application.
 * This exception is used to handle errors and provide meaningful messages
 * when unexpected conditions occur within the application.
 */
public class BobException extends Exception {

    /**
     * Constructs a new BobException with the specified detail message.
     *
     * @param msg the detail message that describes the error.
     */
    public BobException(String msg) {
        super(msg);
    }
}
