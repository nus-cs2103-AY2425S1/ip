package jay.parser;

/**
 * Represents an exception thrown when the date provided is invalid.
 */
public class InvalidDateException extends Exception {
    public InvalidDateException(String message) {
        super(message);
    }
}
