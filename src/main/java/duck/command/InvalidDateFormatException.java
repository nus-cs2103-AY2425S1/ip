package duck.command;

/**
 * Represents an exception thrown to indicate that a command is invalid.
 */
public class InvalidDateFormatException extends Exception {
    /**
     * Constructs a new InvalidDateFormatException with specified message.
     */
    public InvalidDateFormatException(String message) {
        super(message);
    }
}
