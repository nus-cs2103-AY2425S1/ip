package Alex.Exceptions;

/**
 * Exception thrown when an unknown command is provided.
 */
public class UnknownCommandException extends AlexException {

    /**
     * Constructs an UnknownCommandException with a default message.
     */
    public UnknownCommandException() {
        super("I'm Sorry! I don't know what you mean.");
    }
}
