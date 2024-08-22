/**
 * Exception for handling unrecognised commands
 */
public class UnrecognisedException extends CloudException {
    public UnrecognisedException(String message) {
        super(message);
    }
}
