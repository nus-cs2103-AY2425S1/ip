package johncena.exceptions;

/**
 * This class represents a general exception for the Cena application.
 */
public class CenaException extends Exception {

    /**
     * Constructs a new exceptions.CenaException with the specified detail message.
     *
     * @param message the detail message. The detail message is saved for later retrieval by the Throwable.getMessage() method.
     */
    public CenaException(String message) {
        super(message);
    }
}