package exceptions;

/**
 * InvalidCommandException class to indicate invalid commands.
 */
// Solution below inspired by https://www.javatpoint.com/custom-exception
public class InvalidCommandException extends Exception {
    /**
     * Constructor for InvalidCommandException class.
     *
     * @param message Feedback for the user.
     */
    public InvalidCommandException(String message) {
        super(message);
    }
}
