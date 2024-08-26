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
        case "list":
            return LIST;
        case "mark":
            return MARK;
        case "unmark":
            return UNMARK;
        case "todo":
            return TODO;
        case "deadline":
            return DEADLINE;
        case "event":
            return EVENT;
        case "delete":
            return DELETE;
        case "date":
            return DATE;
        default:
            return INVALID;
        }
    }
}
