package bibi.exception;

/**
 * Represents the RuntimeException that is thrown when an invalid or unknown command is parsed.
 */
public class BibiInvalidCommandException extends RuntimeException {
    public BibiInvalidCommandException(String s) {
        super(s);
    }
}
