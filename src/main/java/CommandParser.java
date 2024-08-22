public class CommandParser {
    public enum CommandType {
        LIST, BYE, MARK, UNMARK, ADD
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
        } else {
            return CommandType.ADD;
        }
    }
}
