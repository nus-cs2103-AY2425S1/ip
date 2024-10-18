package opus.exceptions;

/**
 * Thrown when a required argument is missing in the user's command.
 */
public class OpusMissingArgumentException extends OpusException {

    /**
     * Constructs a new OpusMissingArgumentException with the specified detail message.
     *
     * @param message The detail message.
     */
    public OpusMissingArgumentException(String message) {
        super(message);
    }
}
