package stan.exceptions;

/**
 * Represents an exception thrown when an invalid command is entered by the user.
 */
public class StanInvalidCommandException extends StanException {
    /**
     * Constructs a StanInvalidCommandException with a default detail message.
     */
    public StanInvalidCommandException() {
        super("I'm sorry, but I don't understand that command.");
    }
}
