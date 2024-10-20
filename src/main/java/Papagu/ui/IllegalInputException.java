package papagu.ui;

/**
 * Represents an exception thrown when the user inputs an invalid command
 */
public class IllegalInputException extends IllegalArgumentException {
    public IllegalInputException(String message) {
        super(message);
    }
}
