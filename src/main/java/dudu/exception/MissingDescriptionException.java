package dudu.exception;

public class MissingDescriptionException extends Exception {
    /**
     * Constructs an MissingDescriptionException with the specified detail message.
     *
     * @param message The detail message which explains the reason for the exception.
     */
    public MissingDescriptionException(String message) {
        super(message);
    }
}
