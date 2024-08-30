package duke.exception;

/**
 * Represents an exception that is thrown when an unknown or unrecognized command is encountered.
 */
public class UnknownCommandException extends Exception {

    /**
     * Constructs an UnknownCommandException with a specific command name.
     *
     * @param command The command that is unrecognized (not used in the message but passed for context).
     */
    public UnknownCommandException(String command) {
        super("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}