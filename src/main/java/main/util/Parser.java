package main.util;

import main.command.ByeCommand;
import main.command.Command;
import main.command.DeadlineCommand;
import main.command.DeleteCommand;
import main.command.EventCommand;
import main.command.FindCommand;
import main.command.FindExactCommand;
import main.command.ListCommand;
import main.command.MarkCommand;
import main.command.TodoCommand;
import main.command.UnmarkCommand;
import main.exceptions.PrinceException;

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
        case "findexact":
            return new FindExactCommand(input);
        default:
            throw new PrinceException("Sorry, I am not sure what '" + input
                    + "' means. Please try again!");
        }
    }
}
