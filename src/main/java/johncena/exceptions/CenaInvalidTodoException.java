package johncena.exceptions;

/**
 * This class represents an exception that is thrown when an invalid todo is encountered.
 */
public class CenaInvalidTodoException extends CenaException {

    /**
     * Constructs a new exceptions.CenaInvalidTodoException with the specified detail message.
     *
     * @param message the detail message. The detail message is saved for later retrieval by the Throwable.getMessage() method.
     */
    public CenaInvalidTodoException(String message) {
        super(message);
    }
}