package nathanbot.commands;

/**
 * Cleaned up using Copilot to follow Google's Java Style Guide,
 * while keeping indentations to be 4 spaces.
 */
public enum CommandType {
    BREAK("bye"),
    DISPLAY_LIST("list"),
    MARK_DONE("mark "),
    MARK_UNDONE("unmark "),
    TODO("todo "),
    DEADLINE("deadline "),
    EVENT("event "),
    DELETE("delete "),
    UNKNOWN("");

    private final String command;

    CommandType(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

    public static CommandType fromInput(String input) {
        for (CommandType type : values()) {
            if (input.equals(type.command) || input.startsWith(type.command)) {
                return type;
            }
        }
        return UNKNOWN;
    }
}