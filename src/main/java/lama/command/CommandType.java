package lama.command;

/**
 * The CommandType enum represents the various valid commands that can be used
 * in the application. Each command has a corresponding string representation,
 * and this enum provides methods to retrieve and validate commands.
 */
public enum CommandType {
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    LIST("list"),
    MARK("mark"),
    UNMARK("unmark"),
    DELETE("delete"),
    FIND("find"),
    BYE("bye"),
    ALIAS("alias"),
    ALIASES("aliases"),
    REMOVE("remove");

    private final String command;

    /**
     * Constructs a CommandType enum with the specified command.
     *
     * @param command The string representation of the command.
     */
    CommandType(String command) {
        this.command = command;
    }

    /**
     * Retrieves the string representation of the command.
     *
     * @return The string command associated with this enum constant.
     */
    private String getCommand() {
        return command;
    }

    /**
     * Checks if the given command string matches any valid command in the enum.
     *
     * @param input The string command to check.
     * @return True if the command is valid (i.e., present in the enum), false otherwise.
     */
    public static boolean isValidCommand(String input) {
        for (CommandType type : CommandType.values()) {
            if (type.getCommand().equalsIgnoreCase(input)) {
                return true;
            }
        }
        return false;
    }
}
