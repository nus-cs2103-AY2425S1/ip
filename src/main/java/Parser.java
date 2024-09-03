public class Parser {
    private enum CommandType {
        TODO,
        DEADLINE,
        EVENT,
        LIST,
        MARK,
        UNMARK,
        DELETE,
        UNKNOWN;
    }

    /**
     * Returns the CommandType based on the user input.
     * @param command the user input
     * @return the CommandType based on the user input
     */
    public static Command getCommandType(String command) {
        CommandType commandType = CommandType.UNKNOWN;
        String[] splitCommand = command.split(" ", 2);
        String description = splitCommand.length < 2? "" : splitCommand[1];

        switch (splitCommand[0]) {
        case "todo":
            commandType = CommandType.TODO;
            break;
        case "deadline":
            commandType = CommandType.DEADLINE;
            break;
        case "event":
            commandType = CommandType.EVENT;
            break;
        case "list":
            commandType = CommandType.LIST;
            break;
        case "mark":
            commandType = CommandType.MARK;
            break;
        case "unmark":
            commandType = CommandType.UNMARK;
            break;
        case "delete":
            commandType = CommandType.DELETE;
            break;
        default:
            commandType = CommandType.UNKNOWN;
        }

        return handleCommand(commandType, description);
    }

    private static Command handleCommand (CommandType commandType, String description) {
        switch (commandType) {
        case TODO:
            return new ToDoCommand(description);
        case DEADLINE:
            return new DeadlineCommand(description);
        case EVENT:
            return new EventsCommand(description);
        case LIST:
            return new ListCommand();
        case MARK:
            return new MarkDoneCommand(description);
        case UNMARK:
            return new MarkUndoneCommand(description);
        case DELETE:
            return new DeleteCommand(description);
        default:
            return new UnknownCommand();
        }
    }
}
