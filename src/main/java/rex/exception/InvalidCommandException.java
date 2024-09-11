package rex.exception;

/**
 * The {@code InvalidCommandException} class is a specific type of {@code InvalidInputException}
 * that is thrown when a user inputs an unrecognized command in the Rex application.
 */
public class InvalidCommandException extends InvalidInputException {
    /**
     * Constructs a new {@code InvalidCommandException} with a default error message.
     * The message indicates that the command was not recognized and provides a suggestion
     * to the user to enter "help" for a list of valid commands.
     */
    public InvalidCommandException() {
        super("I don't know what that means!!!\nEnter \"help\" for a list of what I do know! rawr");
    }
}
