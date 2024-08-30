package yapper.command;

public class Parser {

    public static CommandType parseCommand(String fullCommand) {
        String[] userInputParts = fullCommand.split(" ", 2);
        return getCommandType(userInputParts[0]);
    }

    private static CommandType getCommandType(String command) {
        switch (command.toLowerCase()) {
            case "bye":
                return CommandType.BYE;
            case "list":
                return CommandType.LIST;
            case "mark":
                return CommandType.MARK;
            case "unmark":
                return CommandType.UNMARK;
            case "todo":
                return CommandType.TODO;
            case "deadline":
                return CommandType.DEADLINE;
            case "event":
                return CommandType.EVENT;
            case "delete":
                return CommandType.DELETE;
            default:
                return CommandType.UNKNOWN;
        }
    }
}
