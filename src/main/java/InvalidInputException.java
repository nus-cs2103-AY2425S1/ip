/**
 * This exception class indicates that the input keyed in by the user is incorrect.
 */
public class InvalidInputException extends Exception{
    
    /**
     * Creates the InvalidInputException exception object.
     * @param message The error message to be displayed to the user.
     */
    InvalidInputException (String message) {

        super(message);
    }
}
