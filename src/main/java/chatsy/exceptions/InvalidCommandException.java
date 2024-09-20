package chatsy.exceptions;

/**
 * Represents an exception that is thrown when an invalid command is encountered.
 */
public class InvalidCommandException extends ChatsyException {

    /**
     * Constructs an {@code InvalidCommandException} with a default error message.
     */
    public InvalidCommandException() {
        super("I'm sorry, but I don't know what that means :-(");
    }

    /**
     * Constructs an {@code InvalidCommandException} with a custom error message.
     *
     * @param message The custom error message.
     */
    public InvalidCommandException(String message) {
        super(message);
    }
}
