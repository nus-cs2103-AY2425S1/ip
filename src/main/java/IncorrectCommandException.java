/**
 * The IncorrectCommandException class is a custom exception that is thrown when a user
 * enters a command that is not valid or does not meet the expected format.
 */
public class IncorrectCommandException extends Exception {

    /**
     * Constructs a new IncorrectCommandException with the specified detail message.
     * @param message the detail message explaining the reason for the exception
     */
    public IncorrectCommandException(String message) {
        super(message);
    }
}
