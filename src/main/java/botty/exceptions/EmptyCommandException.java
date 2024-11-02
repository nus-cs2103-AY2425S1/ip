package botty.exceptions;

/**
 * Exception thrown when command is empty
 */
public class EmptyCommandException extends BottyException {
    /**
     * Constructs a new {@code EmptyCommandException}.
     */
    public EmptyCommandException() {
        super("Command cannot be empty!");
    }
}
