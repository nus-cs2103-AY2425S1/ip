package johncena.exceptions;

/**
 * This class represents an exception that is thrown when an invalid deadline is encountered.
 */
public class CenaInvalidDeadlineException extends CenaException {

    /**
     * Constructs a new exceptions.CenaInvalidDeadlineException with the specified detail message.
     *
     * @param message the detail message. The detail message is saved for later retrieval by the Throwable.getMessage()
     *               method.
     */
    public CenaInvalidDeadlineException(String message) {
        super(message);
    }
}
