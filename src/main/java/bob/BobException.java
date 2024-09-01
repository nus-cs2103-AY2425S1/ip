package bob;

/**
 * Represents an exception specific to the Bob application.
 * The BobException class allows custom error messages to be displayed to the user.
 */
public class BobException extends Exception{

    /**
     * Constructs a new BobException with the specified detail message.
     *
     * @param message Error message to be displayed to the user.
     */
    public BobException(String message) {
        super(message);
    }

    /**
     * Returns a string representation of this exception.
     *
     * @return Error message.
     */
    @Override
    public String toString() {
        return super.getMessage();
    }
}
