package bob.exception;

/**
 * Thrown by the parser when an input is not a known command.
 */
public class UnknownCommandException extends BobException {
    public UnknownCommandException() {
        super("WHOA! Not sure what you're aiming for,\n"
                + "but I can't help with that one.");
    }
}
