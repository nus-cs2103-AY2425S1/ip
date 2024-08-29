package justbot.exception;

/**
 * Represents an exception specific to the Justbot application.
 * This exception is thrown when an error occurs within Justbot, typically due to invalid user input or other application-specific issues.
 */
public class JustbotException extends Exception {

    /**
     * Constructs a new JustbotException with the specified detail message.
     *
     * @param message The detail message that explains the reason for the exception.
     */
    public JustbotException(String message) {
        super(message);
    }
}
