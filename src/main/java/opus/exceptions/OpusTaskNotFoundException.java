package opus.exceptions;

/**
 * Thrown when a task is not found in the task list, typically due to an invalid index.
 */
public class OpusTaskNotFoundException extends OpusException {

    /**
     * Constructs a new OpusTaskNotFoundException with the specified detail message.
     *
     * @param message The detail message.
     */
    public OpusTaskNotFoundException(String message) {
        super(message);
    }
}
