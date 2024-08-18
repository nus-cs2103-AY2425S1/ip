public enum CommandType {
    BYE,
    DEADLINE,
    DELETE,
    EVENT,
    INVALID,
    LIST,
    MARK,
    TODO,
    UNMARK;

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
            default:
                return INVALID;
        }
    }
}
