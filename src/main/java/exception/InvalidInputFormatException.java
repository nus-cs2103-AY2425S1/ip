package exception;

/**
 * Exception thrown to indicate that an input has an invalid format.
 * This exception is typically used to signal errors when parsing or processing input that doesn't conform
 * to the expected format.
 */
public class InvalidInputFormatException extends Exception {

    /**
     * Constructs a new InvalidInputFormatException with {@code null} as its detail message.
     * The cause is not initialized and may subsequently be initialized by a call to {@link Throwable#initCause}.
     */
    public InvalidInputFormatException() {
        super();
    }

    /**
     * Constructs a new InvalidInputFormatException with the specified detail message.
     * The cause is not initialized and may subsequently be initialized by a call to {@link Throwable#initCause}.
     *
     * @param msg the detail message, saved for later retrieval by the {@link Throwable#getMessage()} method.
     */
    public InvalidInputFormatException(String msg) {
        super(msg);
    }
}
