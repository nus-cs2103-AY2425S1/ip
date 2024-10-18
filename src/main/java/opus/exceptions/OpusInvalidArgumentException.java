package opus.exceptions;

/**
 * Thrown when an argument provided by the user is invalid.
 */
public class OpusInvalidArgumentException extends OpusException {

    /**
     * Constructs a new OpusInvalidArgumentException with the specified detail message.
     *
     * @param message The detail message.
     */
    public OpusInvalidArgumentException(String message) {
        super(message);
    }
}
