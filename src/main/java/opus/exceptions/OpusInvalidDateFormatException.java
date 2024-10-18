package opus.exceptions;

/**
 * Thrown when a date format is invalid or cannot be parsed.
 */
public class OpusInvalidDateFormatException extends OpusException {

    /**
     * Constructs a new OpusInvalidDateFormatException with the specified detail message.
     *
     * @param message The detail message.
     */
    public OpusInvalidDateFormatException(String message) {
        super(message);
    }
}
