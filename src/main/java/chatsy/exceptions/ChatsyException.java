package chatsy.exceptions;

/**
 * Represents a general exception in the Chatsy application.
 * This class serves as the base class for all custom exceptions in Chatsy.
 */
public class ChatsyException extends Exception {

    /**
     * Constructs a {@code ChatsyException} with a default error message.
     */
    public ChatsyException() {
        super("Oops, an error has occurred.");
    }

    /**
     * Constructs a {@code ChatsyException} with a custom error message.
     *
     * @param message The custom error message.
     */
    public ChatsyException(String message) {
        super(message);
    }
}
