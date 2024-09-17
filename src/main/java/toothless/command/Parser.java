package toothless.command;

/**
 * Represents a parser to parse the user input.
 */
public class Parser {
    /**
     * Represents the type of command.
     */
    private enum CommandType {
        TODO,
        DEADLINE,
        EVENT,
        LIST,
        MARK,
        UNMARK,
        DELETE,
        FIND,
        HELP,
        UNKNOWN
    }

    /**
     * Returns the CommandType based on the user input.
     *
     * @param command the user input
     * @return the CommandType based on the user input
     */
    public static Command getCommandType(String command) {
        assert command != null : "Command cannot be null";
        CommandType commandType;
        String trimmedCommand = command.trim();
        String[] splitCommand = trimmedCommand.split(" ", 2);
        String description = splitCommand.length < 2 ? "" : splitCommand[1];

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
        case "find":
            commandType = CommandType.FIND;
            break;
        case "help":
            commandType = CommandType.HELP;
            break;
        default:
            commandType = CommandType.UNKNOWN;
        }

        return handleCommand(commandType, description);
    }

    /**
     * Returns the Command based on the CommandType and description.
     *
     * @param commandType the CommandType
     * @param description the description
     * @return the Command based on the CommandType and description
     */
    private static Command handleCommand(CommandType commandType, String description) {
        assert commandType != null : "CommandType cannot be null";
        assert description != null : "Description cannot be null";
        switch (commandType) {
        case TODO:
            return new ToDoCommand(description);
        case DEADLINE:
            return new DeadlineCommand(description);
        case EVENT:
            return new EventCommand(description);
        case LIST:
            return new ListCommand();
        case MARK:
            return new MarkDoneCommand(description);
        case UNMARK:
            return new MarkUndoneCommand(description);
        case DELETE:
            return new DeleteCommand(description);
        case FIND:
            return new FindCommand(description);
        case HELP:
            return new HelpCommand();
        default:
            return new UnknownCommand();
        }
    }
}
