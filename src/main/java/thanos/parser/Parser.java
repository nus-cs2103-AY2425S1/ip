package thanos.parser;

import thanos.commands.ByeCommand;
import thanos.commands.Command;
import thanos.commands.CommandType;
import thanos.commands.DateCommand;
import thanos.commands.DeadlineCommand;
import thanos.commands.DeleteCommand;
import thanos.commands.EventCommand;
import thanos.commands.FindCommand;
import thanos.commands.ListCommand;
import thanos.commands.MarkCommand;
import thanos.commands.TodoCommand;
import thanos.commands.UnmarkCommand;
import thanos.exceptions.InvalidCommandException;

/**
 * The {@code Parser} class is responsible for parsing user input into appropriate {@code Command} objects.
 */
public class Parser {
    /**
     * Parses the provided input string and returns the corresponding {@code Command} object.
     * <p>
     * The input string is split into a command and its arguments. The command is matched to a {@code CommandType},
     * and a new instance of the appropriate {@code Command} subclass is created with the given arguments.
     * If the input is invalid or unrecognized, an {@code InvalidCommandException} is thrown.
     * </p>
     *
     * @param input the user input string to be parsed. It should contain the command and optional arguments.
     * @return a {@code Command} object corresponding to the input string.
     * @throws InvalidCommandException if the input is empty or if the command is not recognized.
     */
    public static Command parse(String input) throws InvalidCommandException {
        if (input.trim().isEmpty()) {
            throw new InvalidCommandException("No input provided. Please enter a command.");
        }

        String[] inputArr = getInputArr(input);
        CommandType commandType = CommandType.getCommandType(inputArr[0].toLowerCase());
        String argument = getArgument(inputArr);

        switch (commandType) {
        case BYE:
            return new ByeCommand(argument);
        case DATE:
            return new DateCommand(argument);
        case DEADLINE:
            return new DeadlineCommand(argument);
        case DELETE:
            return new DeleteCommand(argument);
        case EVENT:
            return new EventCommand(argument);
        case FIND:
            return new FindCommand(argument);
        case LIST:
            return new ListCommand(argument);
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

    /**
     * Extracts the second part of the input array as the argument.
     *
     * @param inputArr the input array of strings, usually split by space.
     * @return the second element of the array as the argument, or an empty string if there is no second element.
     */
    private static String getArgument(String[] inputArr) {
        return inputArr.length == 1 ? "" : inputArr[1].trim();
    }

    /**
     * Splits the input string into an array of two elements.
     *
     * @param input the input string to be split.
     * @return an array containing up to two elements: the command and the argument (if any).
     */
    private static String[] getInputArr(String input) {
        return input.trim().split(" ", 2);
    }
}
