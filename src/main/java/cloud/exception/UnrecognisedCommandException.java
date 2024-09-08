package cloud.exception;

/**
 * Exception for handling unrecognised commands
 */
public class UnrecognisedCommandException extends CloudException {
    public UnrecognisedCommandException(String message) {
        super(message);
    }

    public UnrecognisedCommandException() {
        super("I did not understand that, please try again.");
    }
}
