public class InvalidCommandException extends RuntimeException {
    private String userCmd;

    /**
     * Constructor for InvalidCommandException.
     * @param userCmd a String representation of the user command
     */
    public InvalidCommandException(String userCmd) {
        this.userCmd = userCmd;
    }

    /**
     * Returns a detailed message of the invalid command from the user
     * and how to look for the list of valid commands
     * @return a String message to help users
     */
    @Override
    public String toString() {
        return String.format("\"%s\" is not a valid command.\n" +
                "Type /help to see the list of available commands.", userCmd);
    }
}
