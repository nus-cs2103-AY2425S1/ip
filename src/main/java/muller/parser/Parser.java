package muller.parser;

import muller.command.AddCommand;
import muller.command.Command;
import muller.command.DeleteCommand;
import muller.command.ExitCommand;
import muller.command.FindCommand;
import muller.command.ListCommand;
import muller.command.MarkCommand;
import muller.command.MullerException;
import muller.command.OnCommand;
import muller.command.UnmarkCommand;

/**
 * Parses user input into commands for execution.
 */
public class Parser {
    /**
     * Parses the user input into a Command object.
     *
     * @param fullCommand The full command entered by the user.
     * @return The Command object corresponding to the user input.
     * @throws MullerException If the command is not recognized.
     */
    public Command parse(String fullCommand) throws MullerException {
        String[] inputs = fullCommand.split(" ", 2);
        String commandWord = inputs[0].toLowerCase();

        switch (commandWord) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "mark":
            return new MarkCommand(inputs);
        case "unmark":
            return new UnmarkCommand(inputs);
        case "todo":
            return new AddCommand(inputs, "T");
        case "deadline":
            return new AddCommand(inputs, "D");
        case "event":
            return new AddCommand(inputs, "E");
        case "delete":
            return new DeleteCommand(inputs);
        case "on":
            return new OnCommand(inputs);
        case "find":
            return new FindCommand(inputs);
        default:
            throw new MullerException("I'm sorry, but I don't know what that means :-(");
        }
    }
}
