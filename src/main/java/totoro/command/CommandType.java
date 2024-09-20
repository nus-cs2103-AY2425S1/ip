package totoro.command;

/**
 * Represents the type of commands available
 * This enum defines all the possible command types
 */
public enum CommandType {
    BYE, LIST, MARK, UNMARK, DELETE, FIND, SCHEDULE, TODO, DEADLINE, EVENT, HELP;

    /**
     * Checks if the given string is a valid command
     * Converts the input command to uppercase and verifies if it exists in the {@code CommandType} enum
     *
     * @param command The string representation of the command to check
     * @return {@code true} if the command is valid, {@code false} otherwise
     */
    public static boolean isValidCommand(String command) {
        try {
            CommandType.valueOf(command.toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
