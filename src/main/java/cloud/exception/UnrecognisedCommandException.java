package cloud.exception;

/**
 * Represents an exception for handling unrecognised commands
 */
public class UnrecognisedCommandException extends CloudException {
    public UnrecognisedCommandException(String message) {
        super(message);
    }

    public UnrecognisedCommandException() {
        super("Sorry I did not understand that. Enter \"help <your command>\" to view details.");
    }
}
