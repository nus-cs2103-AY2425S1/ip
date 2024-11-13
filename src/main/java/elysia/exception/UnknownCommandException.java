package elysia.exception;

/**
 * Represents an unknown command exception.
 */
public class UnknownCommandException extends ElysiaException {
    /**
     * Constructs an {@code UnknownCommandException} with a message indicating that the command is not recognised.
     */
    public UnknownCommandException() {
        super("Oh my! I'm so sorry,\n"
                + "but it seems I'm not sure what that means.\n"
                + "Let's figure it out together, shall we?");
    }
}
