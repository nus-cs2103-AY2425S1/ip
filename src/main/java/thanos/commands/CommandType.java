package thanos.commands;

/**
 * Enum representing the various types of commands that can be executed within the application.
 */
public enum CommandType {
    BYE,
    DATE,
    DEADLINE,
    DELETE,
    EVENT,
    FIND,
    INVALID,
    LIST,
    MARK,
    TODO,
    UNMARK;

    /**
     * Retrieves the {@code CommandType} associated with the given command string.
     * <p>
     * This method maps command strings to their corresponding {@code CommandType} enum values.
     * If the command string does not match any recognized command, {@code INVALID} is returned.
     * </p>
     *
     * @param command the command string to be mapped to a {@code CommandType}.
     * @return the {@code CommandType} corresponding to the provided command string,
     *         or {@code INVALID} if the command string does not match any known commands.
     */
    public static CommandType getCommandType(String command) {
        switch (command) {
        case "bye":
            return BYE;
        case "date":
            return DATE;
        case "deadline":
            return DEADLINE;
        case "delete":
            return DELETE;
        case "event":
            return EVENT;
        case "find":
            return FIND;
        case "list":
            return LIST;
        case "mark":
            return MARK;
        case "todo":
            return TODO;
        case "unmark":
            return UNMARK;
        default:
            return INVALID;
        }
    }
}
