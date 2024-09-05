package jay.storage;

/**
 * Represents an exception thrown when the data format is invalid.
 */
public class InvalidDataFormatException extends Exception {
    public InvalidDataFormatException(String message) {
        super(message);
    }
}
