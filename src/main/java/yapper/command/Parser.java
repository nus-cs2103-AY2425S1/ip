package yapper.command;

/**
 * Interprets user commands and returns the corresponding {@link CommandType}.
 */
public class Parser {

    /**
     * Parses the user's full command and returns the corresponding {@link CommandType}.
     *
     * @param fullCommand The full command string entered by the user.
     * @return The {@link CommandType} corresponding to the user's command.
     */
    public static CommandType parseCommand(String fullCommand) {
        String[] userInputParts = fullCommand.split(" ", 2);
        return getCommandType(userInputParts[0]);
    }

    /**
     * Returns the {@link CommandType} based on the command string.
     *
     * @param command The command string to be interpreted.
     * @return The {@link CommandType} that matches the command string.
     */
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
        case "find":
            return CommandType.FIND;
        default:
            return CommandType.UNKNOWN;
        }
    }

}
