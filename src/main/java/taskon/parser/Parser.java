package taskon.parser;

import static taskon.common.Messages.MESSAGE_DATE_MISSING;
import static taskon.common.Messages.MESSAGE_DESCRIPTION_MISSING;
import static taskon.common.Messages.MESSAGE_HELP;
import static taskon.common.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static taskon.common.Messages.MESSAGE_INVALID_DATE_FORMAT;
import static taskon.common.Messages.MESSAGE_INVALID_INTEGER;

import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import taskon.commands.ByeCommand;
import taskon.commands.Command;
import taskon.commands.DeadlineCommand;
import taskon.commands.DeleteCommand;
import taskon.commands.EventCommand;
import taskon.commands.FindCommand;
import taskon.commands.IncorrectCommand;
import taskon.commands.ListCommand;
import taskon.commands.MarkCommand;
import taskon.commands.OnCommand;
import taskon.commands.TodoCommand;
import taskon.commands.UnmarkCommand;
import taskon.exception.TaskonException;

/**
 * Parses user input into commands for execution.
 *
 * The `Parser` class interprets the user's input and translates it
 * into the appropriate command that the application can execute.
 */
public class Parser {

    /**
     * Regex pattern used for initial separation of command word and args.
     */
    public static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses the given user input and returns the corresponding command.
     *
     * @param input Full user input string.
     * @return The command based on the user input.
     * @throws TaskonException If the user input does not conform to the expected format.
     */
    public static Command parse(String input) throws TaskonException {

        assert input != null : "Input string should not be null";

        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(input.trim());
        if (!matcher.matches()) {
            return new IncorrectCommand(MESSAGE_INVALID_COMMAND_FORMAT + MESSAGE_HELP);
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");

        assert !commandWord.isEmpty() : "Command word should not be empty";

        switch (commandWord) {

        case ByeCommand.COMMAND_WORD:
            return new ByeCommand();

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case MarkCommand.COMMAND_WORD:
            return prepareMark(arguments);

        case UnmarkCommand.COMMAND_WORD:
            return prepareUnmark(arguments);

        case DeleteCommand.COMMAND_WORD:
            return prepareDelete(arguments);

        case TodoCommand.COMMAND_WORD:
            return prepareTodo(arguments);

        case DeadlineCommand.COMMAND_WORD:
            return prepareDeadline(arguments);

        case EventCommand.COMMAND_WORD:
            return prepareEvent(arguments);

        case OnCommand.COMMAND_WORD:
            return new OnCommand(arguments.trim());

        case FindCommand.COMMAND_WORD:
            return new FindCommand(arguments.trim());

        default:
            return new IncorrectCommand(MESSAGE_INVALID_COMMAND_FORMAT + MESSAGE_HELP);

        }
    }

    /**
     * Prepares the delete command by parsing the user input.
     *
     * @param arguments User input for the delete command.
     * @return The delete command.
     * @throws TaskonException If the task number is not a valid integer.
     */
    private static Command prepareDelete(String arguments) throws TaskonException {
        assert arguments != null : "Arguments for delete command should not be empty";
        try {
            int index = Integer.parseInt(arguments.trim());
            return new DeleteCommand(index - 1);
        } catch (NumberFormatException e) {
            throw new TaskonException(MESSAGE_INVALID_INTEGER);
        }
    }

    /**
     * Prepares the unmark command by parsing the user input.
     *
     * @param arguments User input for the unmark command.
     * @return The unmark command.
     * @throws TaskonException If the task number is not a valid integer.
     */
    private static Command prepareUnmark(String arguments) throws TaskonException {
        assert arguments != null : "Arguments for unmark command should not be empty";
        try {
            int index = Integer.parseInt(arguments.trim());
            return new UnmarkCommand(index - 1);
        } catch (NumberFormatException e) {
            throw new TaskonException(MESSAGE_INVALID_INTEGER);
        }
    }

    /**
     * Prepares the mark command by parsing the user input.
     *
     * @param arguments User input for the mark command.
     * @return The mark command.
     * @throws TaskonException If the task number is not a valid integer.
     */
    private static Command prepareMark(String arguments) throws TaskonException {
        assert arguments != null : "Arguments for mark command should not be empty";
        try {
            int index = Integer.parseInt(arguments.trim());
            return new MarkCommand(index - 1);
        } catch (NumberFormatException e) {
            throw new TaskonException(MESSAGE_INVALID_INTEGER);
        }
    }

    /**
     * Prepares the todo command by parsing the user input.
     *
     * @param args User input for the todo command.
     * @return The todo command.
     * @throws TaskonException If the description is missing.
     */
    private static Command prepareTodo(String args) throws TaskonException {
        assert args != null : "Arguments for todo command should not be empty";
        if (args.trim().isEmpty()) {
            throw new TaskonException(MESSAGE_DESCRIPTION_MISSING);
        }
        return new TodoCommand(args.trim());
    }

    /**
     * Prepares the deadline command by parsing the user input.
     *
     * @param args User input for the deadline command.
     * @return The deadline command.
     * @throws TaskonException If the date is missing or invalid.
     */
    private static Command prepareDeadline(String args) throws TaskonException {
        assert args != null : "Arguments for deadline command should not be empty";
        if (!args.contains("/by")) {
            throw new TaskonException(MESSAGE_DATE_MISSING);
        }
        try {
            String[] parts = args.split("/by", 2);
            String description = parts[0].trim();
            String date = parts[1].trim();
            if (description.isEmpty()) {
                throw new TaskonException(MESSAGE_DESCRIPTION_MISSING);
            }
            return new DeadlineCommand(description, date);
        } catch (DateTimeParseException e) {
            throw new TaskonException(MESSAGE_INVALID_DATE_FORMAT);
        }
    }

    /**
     * Prepares the event command by parsing the user input.
     *
     * @param args User input for the event command.
     * @return The event command.
     * @throws TaskonException If the command format is incorrect or if the date is invalid.
     */
    private static Command prepareEvent(String args) throws TaskonException {
        assert args != null : "Arguments for event command should not be empty";

        String[] partsFrom = args.split("/from", 2);
        if (partsFrom.length < 2) {
            throw new TaskonException(MESSAGE_INVALID_COMMAND_FORMAT + MESSAGE_HELP);
        }

        String description = partsFrom[0].trim();
        String[] partsTo = partsFrom[1].split("/to", 2);
        if (partsTo.length < 2) {
            throw new TaskonException(MESSAGE_INVALID_COMMAND_FORMAT + MESSAGE_HELP);
        }

        String from = partsTo[0].trim();
        String to = partsTo[1].trim();

        try {
            return new EventCommand(description, from, to);
        } catch (DateTimeParseException e) {
            throw new TaskonException(MESSAGE_INVALID_DATE_FORMAT);
        }
    }
}
