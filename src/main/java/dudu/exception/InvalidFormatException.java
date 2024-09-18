package dudu.exception;

/**
 * Represents a custom checked exception where the user inputs a wrong format of command.
 */
public class InvalidFormatException extends Exception {
    /**
     * Constructs an InvalidFormatException.
     *
     * @param message Error message.
     */
    public InvalidFormatException(String message) {
        super(message);
    }
}
