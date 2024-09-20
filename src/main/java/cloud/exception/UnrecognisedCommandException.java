package cloud.exception;

/**
 * Represents an exception for handling unrecognised commands
 */
public class UnrecognisedCommandException extends CloudException {
    public UnrecognisedCommandException(String message) {
        super(message);
    }

    public UnrecognisedCommandException() {
        super("Sorry I did not understand that. Type 'help' to see the list of available commands.");
    }
}
