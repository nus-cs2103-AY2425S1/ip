package exception;


/**
 * Exception thrown to indicate that an invalid index has been accessed in an array or collection.
 * This exception can be used to signal errors such as accessing an index that is out of bounds.
 */
public class InvalidIndexException extends Exception {

    /**
     * Constructs a new InvalidIndexException with {@code null} as its detail message.
     * The cause is not initialized and may subsequently be initialized by a call to {@link Throwable#initCause}.
     */
    public InvalidIndexException() {
        super();
    }

    /**
     * Constructs a new InvalidIndexException with the specified detail message.
     * The cause is not initialized and may subsequently be initialized by a call to {@link Throwable#initCause}.
     *
     * @param msg the detail message, saved for later retrieval by the {@link Throwable#getMessage()} method.
     */
    public InvalidIndexException(String msg) {
        super(msg);
    }
}

