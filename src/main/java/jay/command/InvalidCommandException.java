package jay.command;

/**
 * Represents an exception thrown when an invalid Jay.command is entered by the user.
 */
public class InvalidCommandException extends Exception {
    public InvalidCommandException(String message) {
        super(message);
    }
}
