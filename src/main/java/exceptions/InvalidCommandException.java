package exceptions;

/**
 * The {@code InvalidCommandException} class represents an exception that is thrown
 * when an invalid command is entered by the user. This exception extends
 * {@code DownyException}.
 */
public class InvalidCommandException extends DownyException {

    /**
     * Constructs a new {@code InvalidCommandException} with a default error message
     * indicating that an invalid command was entered.
     */
    public InvalidCommandException() {
        super("Invalid command entered");
    }
}
