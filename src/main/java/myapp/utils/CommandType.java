package myapp.utils;

/**
 * The CommandType enum represents the different types of commands
 * that the BingBong application can recognize and execute.
 */
public enum CommandType {
    TODO, DEADLINE, EVENT, FIXED_DURATION, MARK, UNMARK, DELETE, FIND,
    LIST_ON , LIST, HELP, BYE, INVALID;

    /**
     * Determines the CommandType based on the given command string.
     * This method checks the command string against known command prefixes and returns
     * the corresponding CommandType. If the command does not match any known
     * type, it returns {@code INVALID}.
     *
     * @param command the command string to be evaluated.
     * @return the CommandType that corresponds to the command string.
     */
    public static CommandType fromString(String command) {
        String lowerCaseCommand = command.toLowerCase();
        if (lowerCaseCommand.startsWith("todo")) {
            return TODO;
        } else if (lowerCaseCommand.startsWith("deadline")) {
            return DEADLINE;
        } else if (lowerCaseCommand.startsWith("event")) {
            return EVENT;
        } else if (lowerCaseCommand.startsWith("fixed")) {
            return FIXED_DURATION;
        } else if (lowerCaseCommand.startsWith("mark")) {
            return MARK;
        } else if (lowerCaseCommand.startsWith("unmark")) {
            return UNMARK;
        } else if (lowerCaseCommand.startsWith("delete")) {
            return DELETE;
        } else if (lowerCaseCommand.startsWith("find")) {
            return FIND;
        } else if (lowerCaseCommand.startsWith("list on")) {
            return LIST_ON;
        } else if (lowerCaseCommand.startsWith("list")) {
            return LIST;
        } else if (lowerCaseCommand.startsWith("help")) {
            return HELP;
        } else if (lowerCaseCommand.startsWith("bye")) {
            return BYE;
        } else {
            return INVALID;
        }
    }
}
