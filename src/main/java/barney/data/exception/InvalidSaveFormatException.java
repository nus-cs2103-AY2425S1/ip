package barney.data.exception;

/**
 * Represents an exception that is thrown when an invalid save file format is
 * encountered. This exception is a subclass of the BarneyException class.
 *
 * @param message The detailed error message.
 */
public class InvalidSaveFormatException extends BarneyException {
    public InvalidSaveFormatException(String message) {
        super("Invalid Save File Format: " + message);
    }
}
