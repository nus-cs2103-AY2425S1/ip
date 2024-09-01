package cstwooneohthree.glados.exceptions;

/**
 * CommandNotFoundException class when command does not exist.
 *
 * @author jayjay19630
 */
public class CommandNotFoundException extends GladosException {
    /**
     * Constructs CommandNotFoundException by calling the parent class.
     */
    public CommandNotFoundException() {
        super("Unknown command. Please try again.");
    }
}
