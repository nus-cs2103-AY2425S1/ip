package bibi.exception;

/**
 * Represents the RuntimeException that is thrown when a valid command with improper syntax is parsed.
 */
public class BibiInvalidSyntaxException extends RuntimeException {
    public BibiInvalidSyntaxException(String msg) {
        super(msg);
    }
}
