package evan.exception;

/**
 * Represents an exception that is thrown when the user's input is invalid.
 */
public class InvalidUserInputException extends Exception {
    /**
     * Instantiates an InvalidUserInputException object.
     *
     * @param message Message that describes why the user's input is invalid.
     */
    public InvalidUserInputException(String message) {
        super("Oops! That won't work - " + message);
    }
}
