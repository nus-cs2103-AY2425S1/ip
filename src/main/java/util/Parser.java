package util;

import command.ByeCommand;
import command.Command;
import command.DeadlineCommand;
import command.DeleteCommand;
import command.EventCommand;
import command.FindCommand;
import command.ListCommand;
import command.MarkCommand;
import command.TodoCommand;
import command.UnmarkCommand;
import exceptions.PrinceException;

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
        if (input.equals("bye")) {
            return new ByeCommand();
        } else if (input.equals("list")) {
            return new ListCommand();
        } else if (input.contains("unmark")) {
            return new UnmarkCommand(input);
        } else if (input.contains("mark")) {
            return new MarkCommand(input);
        } else if (input.contains("delete")) {
            return new DeleteCommand(input);
        } else if (input.contains("find")) {
            return new FindCommand(input);
        } else if (input.contains("todo")) {
            return new TodoCommand(input);
        } else if (input.contains("deadline")) {
            return new DeadlineCommand(input);
        } else if (input.contains("event")) {
            return new EventCommand(input);
        } else {
            throw new PrinceException("    Sorry, I am not sure what '" + input
                    + "' means. Please try again!");
        }
    }
}
