package dude.exception;

/**
 * Thrown to indicate that an invalid command is provided in the input.
 */
public class DudeInvalidCommandException extends DudeException {

    /**
     * Constructs a DudeInvalidCommandException with no detail message.
     */
    public DudeInvalidCommandException() {
        super("I don't know what that means. What are you trying to do?");
    }
}
