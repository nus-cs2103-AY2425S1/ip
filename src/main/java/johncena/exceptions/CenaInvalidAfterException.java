package johncena.exceptions;

/**
 * Represents an exception thrown when an invalid format is used for an after task.
 */
public class CenaInvalidAfterException extends CenaException {
    /**
     * Constructor for CenaInvalidAfterException.
     *
     * @param message The error message.
     */
    public CenaInvalidAfterException(String message) {
        super(message);
    }
}
