package myapp.utils;

/**
 * The CommandType enum represents the different types of commands
 * that the BingBong application can recognize and execute.
 */
public enum CommandType {
    TODO, DEADLINE, EVENT, FIXED_DURATION, MARK, UNMARK, DELETE, FIND, LIST_ON , LIST, BYE, INVALID;

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
        if (command.startsWith("todo")) {
            return TODO;
        } else if (command.startsWith("deadline")) {
            return DEADLINE;
        } else if (command.startsWith("event")) {
            return EVENT;
        } else if (command.startsWith("fixed")) {
            return FIXED_DURATION;
        } else if (command.startsWith("mark")) {
            return MARK;
        } else if (command.startsWith("unmark")) {
            return UNMARK;
        } else if (command.startsWith("delete")) {
            return DELETE;
        } else if (command.startsWith("find")) {
            return FIND;
        } else if (command.startsWith("list on")) {
            return LIST_ON;
        } else if (command.equalsIgnoreCase("list")) {
            return LIST;
        } else if (command.equalsIgnoreCase("bye")) {
            return BYE;
        } else {
            return INVALID;
        }
    }
}
