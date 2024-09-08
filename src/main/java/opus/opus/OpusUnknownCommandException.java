package opus;

/**
 * Represents an exception thrown when an unknown or unsupported command is entered by the user.
 */
public class OpusUnknownCommandException extends OpusException {

    /**
     * Constructs an OpusUnknownCommandException with a specific error message.
     *
     * @param message The error message that describes the exception.
     */
    public OpusUnknownCommandException(String message) {
        super(message);
    }
}
