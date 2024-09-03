package duck.command;

/**
 * Exception thrown to indicate that a command is invalid.
 */
public class InvalidCommandException extends Exception {
    /**
     * Constructs new InvalidCommandException with specified message.
     */
    public InvalidCommandException(String message) {
        super(message);
    }
}
