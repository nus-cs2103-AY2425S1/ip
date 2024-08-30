package quack.exception;

/**
 * This exception class indicates that the date time entered is invalid.
 */
public class InvalidDateTimeException extends Exception {

    /**
     * Creates an InvalidDateTimeException exception object.
     * @param message The error message to be displayed to the user.
     */
    public InvalidDateTimeException (String message) {
        
        super(message);
    }
}
