package joe.exceptions;

/**
 * Represents an exception thrown when the user inputs an invalid command.
 */
public class InvalidCommandException extends RuntimeException {
    private final String userCmd;

    /**
     * Constructor for InvalidCommandException.
     *
     * @param userCmd a String representation of the user command
     */
    public InvalidCommandException(String userCmd) {
        this.userCmd = userCmd;
    }

    /**
     * Returns a detailed message of the invalid command from the user
     * and how to look for the list of valid commands
     *
     * @return a String message to help users
     */
    @Override
    public String getMessage() {
        return String.format("\"%s\" is not a valid command.\n"
                + "Type </help> to see the list of available commands.", userCmd);
    }
}
