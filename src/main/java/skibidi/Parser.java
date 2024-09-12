package skibidi;

import commands.AddDeadlineCommand;
import commands.AddEventCommand;
import commands.AddTodoCommand;
import commands.DeleteCommand;
import commands.EchoCommand;
import commands.ExitCommand;
import commands.FindCommand;
import commands.ListCommand;
import commands.MarkCommand;
import commands.UnmarkCommand;

/**
 * Represents a parser to parse user input.
 */
public class Parser {
    /**
     * Parses the user input and returns the corresponding command.
     *
     * @param input The user input.
     * @return The corresponding command.
     * @throws SkibidiException If the user input is invalid.
     */
    public Command parse(String input) throws SkibidiException {

        String normalizedInput = input.trim().toLowerCase();

        switch (normalizedInput.split(" ")[0]) {
        case "echo":
            return new EchoCommand(input);
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "todo":
            return new AddTodoCommand(input);
        case "deadline":
            return new AddDeadlineCommand(input);
        case "event":
            return new AddEventCommand(input);
        case "mark":
            return new MarkCommand(input);
        case "unmark":
            return new UnmarkCommand(input);
        case "delete":
            return new DeleteCommand(input);
        case "find":
            return new FindCommand(input);
        default:
            throw new SkibidiException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
