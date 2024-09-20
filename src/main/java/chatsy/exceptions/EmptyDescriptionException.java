package chatsy.exceptions;

/**
 * Represents an exception that is thrown when a description is empty.
 */
public class EmptyDescriptionException extends ChatsyException {

    /**
     * Constructs an {@code EmptyDescriptionException} with a default error message.
     */
    public EmptyDescriptionException() {
        super("The description cannot be empty.");
    }

    /**
     * Constructs an {@code EmptyDescriptionException} with a custom error message.
     *
     * @param message The custom error message.
     */
    public EmptyDescriptionException(String message) {
        super(message);
    }
}
