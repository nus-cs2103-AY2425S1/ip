package johncena.exceptions;

/**
 * This class represents an exception that is thrown when an invalid event is encountered.
 */
public class CenaInvalidEventException extends CenaException {

    /**
     * Constructs a new exceptions.CenaInvalidEventException with the specified detail message.
     *
     * @param message the detail message. The detail message is saved for later retrieval by the Throwable.getMessage() method.
     */
    public CenaInvalidEventException(String message) {
        super(message);
    }
}