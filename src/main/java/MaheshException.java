/**
 * Custom exception class for handling specific errors in the Mahesh application.
 */
public class MaheshException extends Exception {

    /**
     * Constructs a new MaheshException with the specified detail message.
     *
     * @param errString The detail message for the exception.
     */
    public MaheshException(String errString) {
        super(errString);
    }
}
