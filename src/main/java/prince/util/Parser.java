package prince.util;

import prince.command.ByeCommand;
import prince.command.Command;
import prince.command.DeadlineCommand;
import prince.command.DeleteCommand;
import prince.command.EventCommand;
import prince.command.FindCommand;
import prince.command.ListCommand;
import prince.command.MarkCommand;
import prince.command.TodoCommand;
import prince.command.UnmarkCommand;
import prince.exceptions.PrinceException;

/**
 * Parser executes commands based on user input.
 */
public class Parser {
    /**
     * Returns a command based on the input of the user.
     * @param input Input by the user.
     * @return A command.
     * @throws PrinceException
     */
    public static Command parse(String input) throws PrinceException {
        String[] inputParts = input.split(" ", 2);
        String command = inputParts[0];
        switch (command) {
        case "bye":
            return new ByeCommand();
        case "list":
            return new ListCommand();
        case "unmark":
            return new UnmarkCommand(input);
        case "mark":
            return new MarkCommand(input);
        case "delete":
            return new DeleteCommand(input);
        case "find":
            return new FindCommand(input);
        case "todo":
            return new TodoCommand(input);
        case "deadline":
            return new DeadlineCommand(input);
        case "event":
            return new EventCommand(input);
        default:
            throw new PrinceException("    Sorry, I am not sure what '" + input
                    + "' means. Please try again!");
        }
    }
}
