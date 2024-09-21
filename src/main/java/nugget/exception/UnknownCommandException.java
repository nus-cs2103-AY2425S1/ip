package nugget.exception;

/**
 * Represents an exception that is thrown when the user enters an unknown or invalid command.
 * The {@code UnknownCommandException} indicates that the command entered is not recognized.
 */
public class UnknownCommandException extends NuggetException {

    /**
     * Constructs an {@code UnknownCommandException} with a predefined error message.
     * This message prompts the user to enter valid commands.
     */
    public UnknownCommandException() {
        super("OOPS!!! Please enter valid commands!");
    }
}
