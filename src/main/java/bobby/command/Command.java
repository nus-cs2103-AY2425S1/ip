package bobby.command;

public enum Command {
    BYE, LIST, MARK, UNMARK, DELETE, TODO, DEADLINE, EVENT, UNKNOWN, FIND, SEARCHDATE;

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
            case "searchdate":
                return SEARCHDATE;
            default:
                return UNKNOWN;
        }
    }
}
