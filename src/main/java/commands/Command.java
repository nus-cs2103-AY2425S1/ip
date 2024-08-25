package commands;

public enum Command {
    MARK, UNMARK, DELETE, TODO, DEADLINE, EVENT, INVALID, LIST, BYE;

    public static Command stringToCommand(String s) {
        switch (s) {
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
            case "list":
                return LIST;
            case "bye":
                return BYE;
            default:
                return INVALID;
        }
    }

}
