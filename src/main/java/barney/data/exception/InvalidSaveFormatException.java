package barney.data.exception;

/**
 * Represents an exception thrown when the save file has an invalid format.
 */
public class InvalidSaveFormatException extends BarneyException {

    /**
     * Constructs a new InvalidSaveFormatException with the specified detail
     * message.
     *
     * @param message The detail message.
     */
    public InvalidSaveFormatException(String message) {
        super("Invalid Save File Format: " + message);
    }
}
