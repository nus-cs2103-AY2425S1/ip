package exceptions;

/**
 * InvalidInputException class to indicate invalid inputs.
 */
public class InvalidInputException extends Exception {
    /**
     * Constructor for InvalidInputException class.
     *
     * @param message Feedback for the user.
     */
    public InvalidInputException(String message) {
        super(message);
    }
}
