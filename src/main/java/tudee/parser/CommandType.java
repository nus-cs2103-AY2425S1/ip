package tudee.parser;

/**
 * Enum representing the different types of commands.
 */
public enum CommandType {
    LIST, BYE, TODO, DEADLINE, EVENT, MARK, UNMARK, DELETE, DATE, FIND, UNKNOWN;

    /**
     * Converts a string command into the corresponding CommandType enumerator.
     * If the command does not match any known command, it returns UNKNOWN.
     *
     * @param command the command as a string
     * @return the corresponding CommandType enum.
     */
    public static CommandType fromString(String command) {
        switch (command.toLowerCase()) {
            case "list":
                return LIST;
            case "bye":
                return BYE;
            case "todo":
                return TODO;
            case "deadline":
                return DEADLINE;
            case "event":
                return EVENT;
            case "mark":
                return MARK;
            case "unmark":
                return UNMARK;
            case "delete":
                return DELETE;
            case "date":
                return DATE;
            case "find":
                return FIND;
            default:
                return UNKNOWN;
        }
    }
}