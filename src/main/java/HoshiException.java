/**
 * Exception class for handling errors specific to Hoshi.
 */
public class HoshiException extends Exception {

    /**
     * Constructs a new instance of HoshiException with the specified error message.
     *
     * @param message The error message which will be shown if user encounters the relevant error
     */
    public HoshiException(String message) {
        super(message);
    }

}
