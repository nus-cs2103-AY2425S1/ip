package puke.exceptions;

/**
 * Base class for all exceptions thrown by the Puke application.
 */
public class PukeException extends Exception {

    /**
     * Constructs a PukeException with the specified detail message.
     *
     * @param message the detail message for the exception
     */
    public PukeException(String message) {
        super(message);
    }
}
