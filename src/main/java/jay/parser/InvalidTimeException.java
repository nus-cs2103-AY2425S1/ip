package jay.parser;

/**
 * Represents an exception thrown when the time format is invalid.
 */
public class InvalidTimeException extends Exception {
    public InvalidTimeException(String message) {
        super(message);
    }
}
