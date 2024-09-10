package tohru.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import tohru.exception.TohruException;

/**
 * Parser represents the parsing of input prompt and determine which command to execute.
 */
public class Parser {

    /**
     * Parses the giving input and determine the corresponding commands.
     *
     * @param prompt The input from the user.
     * @return A command object representing the operation requested by the user.
     * @throws TohruException When an invalid command is passed in.
     */
    public static Command parse(String prompt) throws TohruException {

        String[] dissectedPrompt = prompt.trim().split(" ", 2);
        String command = dissectedPrompt[0];
        String arguments = null;
        if (dissectedPrompt.length == 2) {
            arguments = dissectedPrompt[1].trim();
        }

        switch (command) {
        case ByeCommand.COMMAND_PREFIX:
            return new ByeCommand(arguments);
        case ListCommand.COMMAND_PREFIX:
            return new ListCommand(arguments);
        case MarkCommand.COMMAND_PREFIX:
            return new MarkCommand(arguments);
        case UnmarkCommand.COMMAND_PREFIX:
            return new UnmarkCommand(arguments);
        case DeleteCommand.COMMAND_PREFIX:
            return new DeleteCommand(arguments);
        case AddTodoCommand.COMMAND_PREFIX:
            return new AddTodoCommand(arguments);
        case AddDeadlineCommand.COMMAND_PREFIX:
            return new AddDeadlineCommand(arguments);
        case AddEventCommand.COMMAND_PREFIX:
            return new AddEventCommand(arguments);
        case FindCommand.COMMAND_PREFIX:
            return new FindCommand(arguments);
        default:
            throw new TohruException("You have entered an invalid option! Please try again.");
        }

    }

    /**
     * Parses the datetime string passed in with the provided formatter.
     *
     * @param datetimeString The datetime string to be parsed.
     * @param formatter The format used to parse the datetime string.
     * @return A LocalDateTime object with the parsed date and time.
     * @throws TohruException When the datetime string does not follow the format specified.
     */
    public static LocalDateTime parseDateTimeString(String datetimeString, DateTimeFormatter formatter)
            throws TohruException {
        try {
            return LocalDateTime.parse(datetimeString, formatter);
        } catch (DateTimeParseException e) {
            throw new TohruException(String.format("Argument '%s' is in an invalid datetime format", datetimeString));
        }
    }

    /**
     * Parses the given string.
     *
     * @param arguments The integer string to be parsed.
     * @return The integer that is parsed.
     * @throws TohruException When the string does not have a integer to be parsed.
     */
    protected static int parseIntArgument(String arguments) throws TohruException {
        try {
            return Integer.parseInt(arguments) - 1;
        } catch (NumberFormatException e) {
            throw new TohruException(String.format("%s is not valid index", arguments));
        }
    }

}
