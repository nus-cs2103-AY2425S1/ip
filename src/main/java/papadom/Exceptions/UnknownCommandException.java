package papadom.Exceptions;
/**
 * Exception thrown when the user enters an unrecognized command.
 */
public class UnknownCommandException extends Exception {
    /**
     * Constructs an UnknownCommandException with a message indicating that
     * the command entered is not understood.
     */
    public UnknownCommandException() {
        super(" Sorry, I'm not smart enough to understand what you're saying now...");
    }
}
