package johncena.exceptions;

/**
 * This class represents an exception that is thrown when an invalid task index is encountered.
 */
public class CenaInvalidTaskIndexException extends CenaException {

    /**
     * Constructs a new exceptions.CenaInvalidTaskIndexException with the specified detail message.
     *
     * @param message the detail message. The detail message is saved for later retrieval by the Throwable.getMessage()
     *               method.
     */
    public CenaInvalidTaskIndexException(String message) {
        super(message);
    }
}
