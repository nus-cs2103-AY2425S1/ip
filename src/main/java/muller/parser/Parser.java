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
import muller.command.RemindCommand;
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
        // Programmer-level assumption: fullCommand should be non-null and non-empty
        assert fullCommand != null : "Command should not be null";
        assert !fullCommand.trim().isEmpty() : "Command should not be empty";

        String[] inputs = fullCommand.split(" ", 2);

        // Programmer-level assumption: inputs should always have at least one element (the command itself)
        assert inputs.length > 0 : "Command should not be empty";
        String commandWord = inputs[0].toLowerCase();

        switch (commandWord) {
        case "bye":
            return new ExitCommand();
        case "remind":
            return new RemindCommand(inputs);
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
