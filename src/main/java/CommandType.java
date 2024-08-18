public enum CommandType {
    TODO, DEADLINE, EVENT, MARK, UNMARK, DELETE, LIST, BYE, INVALID;

    public static CommandType fromString(String command) {
        if (command.startsWith("todo")) {
            return TODO;
        } else if (command.startsWith("deadline")) {
            return DEADLINE;
        } else if (command.startsWith("event")) {
            return EVENT;
        } else if (command.startsWith("mark")) {
            return MARK;
        } else if (command.startsWith("unmark")) {
            return UNMARK;
        } else if (command.startsWith("delete")) {
            return DELETE;
        } else if (command.equalsIgnoreCase("list")) {
            return LIST;
        } else if (command.equalsIgnoreCase("bye")) {
            return BYE;
        } else {
            return INVALID;
        }
    }
}
