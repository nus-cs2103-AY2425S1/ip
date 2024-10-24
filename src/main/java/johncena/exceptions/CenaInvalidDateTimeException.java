package johncena.exceptions;

import java.time.format.DateTimeParseException;

/**
 * This class represents an exception that is thrown when an invalid date time is encountered.
 */
public class CenaInvalidDateTimeException extends Exception {

    /**
     * Constructs a new exceptions.CenaInvalidDateTimeException with the specified detail message.
     *
     * @param message the detail message. The detail message is saved for later retrieval by the Throwable.getMessage()
     *               method.
     */
    public CenaInvalidDateTimeException(String message) {
        super(message);
    }

    /**
     * Constructs a new exceptions.CenaInvalidDateTimeException with the specified detail message and cause.
     *
     * @param message the detail message. The detail message is saved for later retrieval by the Throwable.getMessage()
     *               method.
     * @param cause the cause of the exception (which is saved for later retrieval by the Throwable.getCause() method).
     */
    public CenaInvalidDateTimeException(String message, DateTimeParseException cause) {
        super(message, cause);
    }
}
