package dudu.exception;

public class MissingDateTimeException extends Exception {
    /**
     * Constructs an MissingDateTimeException with the specified detail message.
     *
     * @param message The detail message which explains the reason for the exception.
     */
    public MissingDateTimeException(String message) {
        super(message);
    }
}
