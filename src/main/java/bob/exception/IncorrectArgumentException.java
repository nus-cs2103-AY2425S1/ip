package bob.exception;

/**
 * Thrown by commands when the provided arguments are not of the expected type or format.
 */
public class IncorrectArgumentException extends BobException {
    public IncorrectArgumentException(String expectedArgument) {
        super("WHOA! That's not quite right.\n"
                + "I need " + expectedArgument + " to do this.");
    }
}
