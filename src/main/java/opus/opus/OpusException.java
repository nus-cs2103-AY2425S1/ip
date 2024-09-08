package opus;

/**
 * Represents a custom exception specific to the Opus application.
 * This serves as the base exception class for all other Opus-related exceptions.
 */
public class OpusException extends Exception {

    /**
     * Constructs an OpusException with a specific error message.
     *
     * @param message The error message that describes the exception.
     */
    public OpusException(String message) {
        super(message);
    }
}
