package duck.parser;

import duck.command.Command;
import duck.command.DeleteCommand;
import duck.command.FindCommand;
import duck.command.InvalidCommandException;
import duck.command.ListCommand;
import duck.command.MarkCommand;
import duck.command.TaskCommand;
import duck.command.UnmarkCommand;

/**
 * Parser parses command inputs to determine the type of command
 * and creates the corresponding Command object.
 */
public class Parser {

    /**
     * Parses the full command string and returns the corresponding Command object.
     *
     * @param fullCommand the full command string to parse
     * @return the Command object corresponding to the parsed command
     * @throws InvalidCommandException if the command type is invalid
     */
    public static Command parse(String fullCommand) throws InvalidCommandException {
        String commandType = Parser.getCommandType(fullCommand);

        return switch (commandType) {
        case "todo", "deadline", "event" -> new TaskCommand(fullCommand, commandType);
        case "mark" -> new MarkCommand(fullCommand);
        case "unmark" -> new UnmarkCommand(fullCommand);
        case "delete" -> new DeleteCommand(fullCommand);
        case "list" -> new ListCommand(fullCommand);
        case "find" -> new FindCommand(fullCommand);
        default -> throw new InvalidCommandException("Invalid command: " + commandType);
        };
    }

    /**
     * Gets the command type from the command string.
     *
     * @param fullCommand the full command string to analyze
     * @return the command type extracted from the input
     */
    public static String getCommandType(String fullCommand) {
        String[] parts = fullCommand.split(" ", 2);
        return parts[0].toLowerCase();
    }
}
