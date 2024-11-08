package cow.exceptions;

/**
 * Represents an exception thrown when a command is not found.
 */
public class UnknownCommandException extends Exception {
    /**
     * Creates an UnknownCommandException instance.
     */
    public UnknownCommandException() {
        super("Command not found!");
    }
}
