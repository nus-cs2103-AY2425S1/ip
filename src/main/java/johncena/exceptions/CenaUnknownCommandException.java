package johncena.exceptions;

/**
 * This class represents an exception that is thrown when an unknown command is encountered.
 */
public class CenaUnknownCommandException extends CenaException {

    /**
     * Constructs a new exceptions.CenaUnknownCommandException with the specified detail message.
     *
     * @param message the detail message. The detail message is saved for later retrieval by the Throwable.getMessage() method.
     */
    public CenaUnknownCommandException(String message) {
        super(message);
    }
}