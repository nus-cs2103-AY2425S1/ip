package mittens;

/**
 * Represents an exception when the cause of the error is unknown.
 */
public class UnknownException extends MittensException {
    private static final String MITTENS_MESSAGE = "Meow?! Something went wrong...";
    private static final String HELP_MESSAGE = "I'm not sure what went wrong. You can try again," +
            " or type 'help' to see a list of commands.";

    public UnknownException(String message) {
        super(message, MITTENS_MESSAGE, HELP_MESSAGE);
    }
}
