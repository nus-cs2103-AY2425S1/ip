package muller.parser;

import muller.command.*;
import muller.command.MullerException;

public class Parser {
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
            default:
                throw new MullerException("I'm sorry, but I don't know what that means :-(");
        }
    }
}
