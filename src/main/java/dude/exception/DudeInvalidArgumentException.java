package dude.exception;

/**
 * Thrown to indicate that an invalid argument for specific command is provided in the input.
 */
public class DudeInvalidArgumentException extends DudeException {

    /**
     * Constructs a DudeInvalidArgumentException with detailed message
     * explaining that an invalid argument was provided for the specified command.
     *
     * @param command  The name of the command that received the invalid argument.
     * @param error    The invalid argument that was provided.
     * @param expected The expected format for the argument.
     */
    public DudeInvalidArgumentException(String command, String error, String expected) {
        super("\"" + error + "\" is not a valid format for \"" + command + "\". Use \"" + expected + "\"!");
    }
}
