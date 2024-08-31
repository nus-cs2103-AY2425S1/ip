package Mario;

public enum CommandType {
    LIST("list"),
    EVENT("event"),
    TODO("todo"),
    DEADLINE("deadline"),
    MARK("mark"),
    UNMARK("unmark"),
    REMOVE("remove"),
    FIND("find"),
    BYE("bye");


    private final String commandString;

    CommandType(String commandString) {
        this.commandString = commandString;
    }

    public String getCommandString() {
        return commandString;
    }

    /**
     * Returns the Enum command type of the command string.
     *
     */
    public static CommandType fromString(String commandString) {
        for (CommandType command : CommandType.values()) {
            if (command.getCommandString().equals(commandString)) {
                return command;
            }
        }
        throw new IllegalArgumentException();
    }
}
