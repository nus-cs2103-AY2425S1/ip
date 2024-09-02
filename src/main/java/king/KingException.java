package king;

/**
 * The KingException class represents custom exceptions that are specific to the King application.
 */
public class KingException extends Exception {

    /**
     * Constructs a new KingException with the specified detail message.
     *
     * @param message The detail message which is saved for later retrieval by the {@link #getMessage()} method.
     */
    public KingException(String message) {
        super(message);
    }
}
