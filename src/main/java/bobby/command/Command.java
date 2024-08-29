package bobby.command;

public enum Command {
    BYE, LIST, MARK, UNMARK, DELETE, TODO, DEADLINE, EVENT, UNKNOWN, FIND;

    /**
     *Returns the corresponding {@code Command} enum constant for a given input string.
     * The input string is expected to be a command word (e.g., "bye", "list", "mark").
     * If the input does not match any known command, {@code UNKNOWN} is returned.
     *
     * @param input the input string representing a command
     * @return the {@code Command} enum constant corresponding to the input string,
     *         or {@code UNKNOWN} if the input does not match any known command
     */
    public static Command fromString(String input) {
        String command = input.split(" ")[0].toLowerCase();
        switch (command) {
            case "bye":
                return BYE;
            case "list":
                return LIST;
            case "mark":
                return MARK;
            case "unmark":
                return UNMARK;
            case "delete":
                return DELETE;
            case "todo":
                return TODO;
            case "deadline":
                return DEADLINE;
            case "event":
                return EVENT;
            case "find":
                return FIND;
            default:
                return UNKNOWN;
        }
    }
}
