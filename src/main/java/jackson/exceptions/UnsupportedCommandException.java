package jackson.exceptions;

/**
 * Thrown when unrecognised command and input is given.
 */
public class UnsupportedCommandException extends JacksonException {
    public UnsupportedCommandException() {
        super("unsupported command");
    }
}
