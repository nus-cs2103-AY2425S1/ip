package arts;

/**
 * Represents custom exceptions specific to the Arts application.
 * It extends the standard Exception class to provide application-specific error handling.
 */
public class ArtsException extends Exception {

    /**
     * Constructs a new ArtsException with the specified detail message.
     *
     * @param message The detail message, which is saved for later retrieval by the getMessage() method.
     */
    public ArtsException(String message) {
        super(message);
    }
}
