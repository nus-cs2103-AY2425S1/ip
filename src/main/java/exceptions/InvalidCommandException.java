package exceptions;

/**
 * InvalidCommandException class to indicate invalid commands.
 */
// Solution below inspired by https://www.javatpoint.com/custom-exception
public class InvalidCommandException extends Exception {
    /**
     * Constructor for InvalidCommandException class.
     */
    public InvalidCommandException(String message) {
        super(message);
    }
}
