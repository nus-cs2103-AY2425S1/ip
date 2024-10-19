package opus.exceptions;

/**
 * Represents an exception thrown when the description of a task is empty.
 */
public class OpusEmptyDescriptionException extends OpusException {
    public OpusEmptyDescriptionException(String message) {
        super(message);
    }
}
