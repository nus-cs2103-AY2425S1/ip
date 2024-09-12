package sage;

import sage.Command.AddCommand;
import sage.Command.Command;
import sage.Command.DeleteCommand;
import sage.Command.ExitCommand;
import sage.Command.FindCommand;
import sage.Command.ListCommand;
import sage.Command.MarkCommand;

/**
 * Parses the input string and returns the corresponding Command object based on the command type.
 */
public class Parser {
    /**
     * Parses the input string and returns the appropriate Command.
     *
     * @param input The input string to parse.
     * @return The Command object.
     * @throws SageException If the command is invalid.
     */
    public static Command parse(String input) throws SageException {
        String[] fullCommand = input.split(" ", 2);
        String commandType = fullCommand[0];

        return switch (commandType) {
        case "list" -> new ListCommand();
        case "mark" -> new MarkCommand(fullCommand[1], true);
        case "todo" -> new AddCommand("todo", fullCommand[1]);
        case "deadline" -> new AddCommand("deadline", fullCommand[1]);
        case "event" -> new AddCommand("event", fullCommand[1]);
        case "find" -> new FindCommand(fullCommand[1]);
        case "delete" -> new DeleteCommand(fullCommand[1]);
        case "bye" -> new ExitCommand();
        default -> throw new SageException("Invalid Command");
        };
    }
}
