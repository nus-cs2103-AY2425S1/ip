package duck.command;

/**
 * Represents an exception thrown to indicate that a command is invalid.
 */
public class InvalidCommandException extends Exception {
    /**
     * Constructs a new InvalidCommandException with specified message.
     */
    public InvalidCommandException(String message) {
        super(message);
    }
}
