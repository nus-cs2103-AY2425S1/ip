package dudu.exception;

/**
 * Represents a custom checked exception where the user inputs a wrong format of command
 */
public class InvalidFormatException extends Exception {
    /**
     * Constructs an InvalidFormatException with the specified detail message.
     *
     * @param message The detail message which explains the reason for the exception.
     */
    public InvalidFormatException(String message) {
        super(message);
    }
}
