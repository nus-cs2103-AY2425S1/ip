package justbot.command;

/**
 * Represents the types of commands that can be executed in the Justbot application.
 * Each command type corresponds to a specific action that can be performed on the task list.
 */
public enum CommandType {
    BYE("bye"),
    LIST("list"),
    MARK("mark"),
    UNMARK("unmark"),
    DEADLINE("deadline"),
    EVENT("event"),
    TODO("todo"),
    DELETE("delete"),
    UNKNOWN("");

    private final String commandString;

    /**
     * Constructs a CommandType with the specified command string.
     *
     * @param commandString The string representation of the command type.
     */
    CommandType(String commandString) {
        this.commandString = commandString;
    }

    /**
     * Returns the string representation of the command type.
     *
     * @return The string representation of the command type.
     */
    public String getCommandString() {
        return this.commandString;
    }

    /**
     * Returns the CommandType corresponding to the given string.
     * If the string does not match any known command, the UNKNOWN command type is returned.
     *
     * @param commandString The string representation of the command type.
     * @return The CommandType corresponding to the given string, or UNKNOWN if no match is found.
     */
    public static CommandType fromString(String commandString) {
        for (CommandType command : CommandType.values()) {
            if (command.getCommandString().equals(commandString)) {
                return command;
            }
        }
        return UNKNOWN;
    }
}
