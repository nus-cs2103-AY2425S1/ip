package exception;

/**
 * The InvalidCommandException is thrown when a user enters an unrecognized or invalid command.
 * It is a specific type of ScheduloException related to invalid user inputs.
 */
public class InvalidCommandException extends ScheduloException {

    /**
     * Constructs an InvalidCommandException with a default error message.
     * The exception message indicates that the command entered is invalid.
     */
    public InvalidCommandException() {
        super("Invalid command, please try again.");
    }
}
