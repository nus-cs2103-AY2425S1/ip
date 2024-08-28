class Parser {
    public Command parseCommand(String input) {
        if (input.equals("bye")) {
            return Command.BYE;
        } else if (input.equals("list")) {
            return Command.LIST;
        } else if (input.startsWith("mark ")) {
            return Command.MARK;
        } else if (input.startsWith("todo ")) {
            return Command.TODO;
        } else if (input.startsWith("deadline ")) {
            return Command.DEADLINE;
        } else if (input.startsWith("event ")) {
            return Command.EVENT;
        } else if (input.startsWith("delete ")) {
            return Command.DELETE;
        } else {
            return Command.UNKNOWN;
        }
    }
}

// Enum to represent the different command types
enum Command {
    BYE, LIST, MARK, TODO, DEADLINE, EVENT, DELETE, UNKNOWN
}