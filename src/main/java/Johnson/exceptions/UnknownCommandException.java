package Johnson.exceptions;

/**
 * Exception thrown when user inputs an unknown command.
 */
public class UnknownCommandException extends Exception {

    public static final String UNKNOWN_COMMAND_MESSAGE = "Har har Chief. I don't know what that means. Try again.";
    public UnknownCommandException(String message) {
        super(message);
    }
}
