package cow.exceptions;

/**
 * Represents an exception thrown when a command is missing parameters.
 */
public class MissingParametersException extends Exception {
    /**
     * Creates a MissingParametersException instance.
     *
     * @param command  the command that has missing parameters.
     * @param expected the expected parameters for the command.
     */
    public MissingParametersException(String command, String expected) {
        super(command + " has missing parameters! Example: " + expected);
    }
}
