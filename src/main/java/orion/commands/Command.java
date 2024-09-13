package orion.commands;

/**
 * The Command enum represents the various commands that the application can
 * recognize and execute.
 * Each enum constant corresponds to a specific command that the user can input.
 */
public enum Command {
    LIST,
    MARK,
    UNMARK,
    TODO,
    DEADLINE,
    EVENT,
    DELETE,
    BYE,
    FIND,
    UNKNOWN;

    /**
     * Converts a string input into a corresponding Command enum value.
     * If the input does not match any of the defined commands, the method returns
     * UNKNOWN.
     *
     * @param command The string input representing the command.
     * @return The corresponding Command enum value, or UNKNOWN if the input is
     *         invalid.
     */
    public static Command fromString(String command) {
        try {
            return Command.valueOf(command.toUpperCase());
        } catch (IllegalArgumentException e) {
            return UNKNOWN;
        }
    }
}
