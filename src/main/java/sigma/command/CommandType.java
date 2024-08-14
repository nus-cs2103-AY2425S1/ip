package sigma.command;

/**
 * Represents the various command types that can be issued in the Sigma application.
 * Each command corresponds to a specific action the application can perform.
 */
public enum CommandType {
    BYE, LIST, TODO, DEADLINE, EVENT, MARK, UNMARK, DELETE;

    /**
     * Checks if a given command word is a valid command.
     *
     * @param commandWord the command word to be checked
     * @return {@code true} if the command word is valid, {@code false} otherwise
     */
    public static boolean isValidCommand(String commandWord) {
        try {
            CommandType.valueOf(commandWord.toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
