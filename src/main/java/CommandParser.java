public class CommandParser {
    public enum CommandType {
        LIST, BYE, MARK, UNMARK, ADD, DEADLINE, EVENT, ERROR, DELETE
    }

    public static CommandType parseCommand(String userInput) {
        if (userInput.equalsIgnoreCase("bye")) {
            return CommandType.BYE;
        } else if (userInput.equalsIgnoreCase("list")) {
            return CommandType.LIST;
        } else if (userInput.startsWith("mark")) {
            return CommandType.MARK;
        } else if (userInput.startsWith("unmark")) {
            return CommandType.UNMARK;
        } else if (userInput.startsWith("deadline")) {
            return CommandType.DEADLINE;
        } else if (userInput.startsWith("event")) {
            return CommandType.EVENT;
        } else if (userInput.startsWith("todo")) {
            return CommandType.ADD;
        } else if (userInput.startsWith("delete")) {
            return CommandType.DELETE;
        }else {
            return CommandType.ERROR;
        }
    }
}
