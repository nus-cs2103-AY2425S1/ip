package parser;

import commands.ByeCommand;
import commands.Command;
import commands.CommandType;
import commands.DateCommand;
import commands.DeadlineCommand;
import commands.DeleteCommand;
import commands.EventCommand;
import commands.ListCommand;
import commands.MarkCommand;
import commands.TodoCommand;
import commands.UnmarkCommand;
import exceptions.InvalidCommandException;

public class Parser {
    public static Command parse(String input) throws InvalidCommandException {
        if (input.trim().isEmpty()) {
            throw new InvalidCommandException("No input provided. Please enter a command.");
        }

        String[] inputArr = input.trim().split(" ", 2);
        CommandType commandType = CommandType.getCommandType(inputArr[0].toLowerCase());
        String argument = inputArr.length == 1 ? "" : inputArr[1].trim();

        switch (commandType) {
        case BYE:
            return new ByeCommand();
        case DATE:
            return new DateCommand(argument);
        case DEADLINE:
            return new DeadlineCommand(argument);
        case DELETE:
            return new DeleteCommand(argument);
        case EVENT:
            return new EventCommand(argument);
        case LIST:
            return new ListCommand();
        case MARK:
            return new MarkCommand(argument);
        case TODO:
            return new TodoCommand(argument);
        case UNMARK:
            return new UnmarkCommand(argument);
        default:
            throw new InvalidCommandException(
                    "Oops! I don't recognise the command you entered. Please enter a valid command."
            );
        }
    }
}
