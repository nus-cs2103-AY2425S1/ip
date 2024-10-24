package johncena.exceptions;

/**
 * This class represents an exception that is thrown when an empty description is encountered.
 */
public class CenaEmptyDescriptionException extends CenaException {

    /**
     * Constructs a new exceptions.CenaEmptyDescriptionException with the specified detail message.
     *
     * @param message the detail message. The detail message is saved for later retrieval by the Throwable.getMessage()
     *                method.
     */
    public CenaEmptyDescriptionException(String message) {
        super(message);
    }
}
