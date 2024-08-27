package duke.exception;

/**
 * Represents an exception specific to the Duke application.
 * This exception is thrown when the Duke application encounters an error.
 */
public class DukeException extends Exception {
    /**
     * Constructs a new DukeException with the specified detail message.
     *
     * @param message The detail message for this exception.
     */
    public DukeException(String message) {
        super(message);
    }
}
