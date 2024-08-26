package thanos.commands;

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
