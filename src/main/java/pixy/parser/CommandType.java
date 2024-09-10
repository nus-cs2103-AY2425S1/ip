package pixy.parser;

/**
 * Enum representing different types of commands.
 */
public enum CommandType {
    LIST("list"),
    BYE("bye"),
    MARK("mark"),
    UNMARK("unmark"),
    DELETE("delete"),
    FIND("find"),
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    UNKNOWN("unknown");

    private final String command;

    CommandType(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

    public static CommandType fromString(String command) {
        for (CommandType type : CommandType.values()) {
            if (type.command.equalsIgnoreCase(command)) {
                return type;
            }
        }
        return UNKNOWN;
    }
}
