package command;

/**
 * Represents an exception thrown when an invalid command is entered by the user.
 */
public class InvalidCommandException extends Exception {
    public InvalidCommandException(String message) {
        super(message);
    }
}
