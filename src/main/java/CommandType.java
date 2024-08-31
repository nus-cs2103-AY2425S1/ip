/**
 * Enum class to represent the different types of commands that the user can input.
 */
public enum CommandType {
    TODO,
    DEADLINE,
    EVENT,
    LIST,
    MARK,
    UNMARK,
    DELETE,
    UNKNOWN;

    /**
     * Returns the CommandType based on the user input.
     * @param command the user input
     * @return the CommandType based on the user input
     */
    public static CommandType getCommandType(String command) {
        switch (command.toLowerCase()) {
            case "todo":
                return TODO;
            case "deadline":
                return DEADLINE;
            case "event":
                return EVENT;
            case "list":
                return LIST;
            case "mark":
                return MARK;
            case "unmark":
                return UNMARK;
            case "delete":
                return DELETE;
            default:
                return UNKNOWN;
        }
    }
}
