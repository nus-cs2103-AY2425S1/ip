package orion.orionexceptions;

/**
 * Custom exception class representing an error that occurs when an invalid date
 * format is encountered.
 *
 * @author Pradyumna
 */

public class InvalidDateFormatException extends OrionException {

    /**
     * Constructs a new InvalidDateFormatException with the specified error message.
     * The error message is appended with the required date format and a message
     * indicating that the date format should be in the format 'dd/MM/yyyy HHmm'.
     *
     * @param message the detail message. The detail message is saved for later
     *                retrieval by the {@link #getMessage()} method.
     */
    public InvalidDateFormatException(String message) {
        super("Invalid date format: " + message + ". Please use 'dd/MM/yyyy HHmm'.");
    }
}
