package opus.exceptions;

/**
 * Base exception class for all Opus-related exceptions.
 */
public class OpusException extends Exception {

    /**
     * Constructs a new OpusException with the specified detail message.
     *
     * @param message The detail message.
     */
    public OpusException(String message) {
        super(message);
    }
}
