package puke.exceptions;

/**
 * Exception thrown when an unknown or unrecognized command is encountered.
 */
public class UnknownCommandException extends PukeException {

    /**
     * Constructs an UnknownCommandException with a default message.
     */
    public UnknownCommandException() {
        super("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
