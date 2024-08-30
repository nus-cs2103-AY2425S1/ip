import java.time.LocalDate;

/**
 * Parser class deals with making sense of the full command.
 */
public class Parser {
    public static Command parse(String fullCommand) throws InvalidCommandException {
        String commandType = Parser.getCommandType(fullCommand);
        return switch (commandType) {
            case "todo", "deadline", "event" -> new TaskCommand(fullCommand, commandType);
            case "mark" -> new MarkCommand(fullCommand);
            case "unmark" -> new UnmarkCommand(fullCommand);
            case "delete" -> new DeleteCommand(fullCommand);
            case "list" -> new ListCommand(fullCommand);
            default -> throw new InvalidCommandException("Invalid command: " + commandType);
        };
    }

    public static String getCommandType(String fullCommand) {
        String[] parts = fullCommand.split(" ", 2);
        return parts[0].toLowerCase();
    }
}
