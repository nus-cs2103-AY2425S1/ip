package elysia.exception;

/**
 * Represents an unknown Command exception.
 */
public class UnknownCommandException extends ElysiaException {
    public UnknownCommandException() {
        super("Oh my! I'm so sorry,\n" +
                "but it seems I'm not sure what that means.\n" +
                "Let's figure it out together, shall we?");
    }
}
