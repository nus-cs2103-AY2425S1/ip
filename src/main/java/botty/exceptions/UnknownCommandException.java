package botty.exceptions;

/**
 * Exception thrown when command is unknown
 */
public class UnknownCommandException extends BottyException {
    /**
     * Constructs an {@code UnknownCommandException}.
     * @param command the command that is inputted
     */
    public UnknownCommandException(String command) {
        super("I'm sorry, that is not a command I am familiar with. (" + command + ")");
    }
}
