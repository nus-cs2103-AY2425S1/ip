package taskon.parser;

import taskon.commands.*;
import taskon.commands.Command;
import taskon.commands.IncorrectCommand;
import taskon.exception.TaskonException;

import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static taskon.common.Messages.*;

public class Parser {

    /**
     * Used for initial separation of command word and args.
     */
    public static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    public static Command parse(String input) throws TaskonException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(input.trim());
        if (!matcher.matches()) {
            return new IncorrectCommand(MESSAGE_INVALID_COMMAND_FORMAT);
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");

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
                return new OnCommand(arguments);

            default:
                return new IncorrectCommand(MESSAGE_INVALID_COMMAND_FORMAT);

        }
    }

    private static Command prepareDelete(String arguments) throws TaskonException {
        try {
            int index = Integer.parseInt(arguments.trim());
            return new DeleteCommand(index - 1);
        } catch (NumberFormatException e) {
            throw new TaskonException(MESSAGE_INVALID_INTEGER);
        }
    }

    private static Command prepareUnmark(String arguments) throws TaskonException {
        try {
            int index = Integer.parseInt(arguments.trim());
            return new UnmarkCommand(index - 1);
        } catch (NumberFormatException e) {
            throw new TaskonException(MESSAGE_INVALID_INTEGER);
        }
    }

    private static Command prepareMark(String arguments) throws TaskonException {
        try {
            int index = Integer.parseInt(arguments.trim());
            return new MarkCommand(index - 1);
        } catch (NumberFormatException e) {
            throw new TaskonException(MESSAGE_INVALID_INTEGER);
        }
    }

    private static Command prepareTodo(String args) throws TaskonException {
        if (args.trim().isEmpty()) {
            throw new TaskonException(MESSAGE_DESCRIPTION_MISSING);
        }
        return new TodoCommand(args.trim());
    }

    private static Command prepareDeadline(String args) throws TaskonException {
        if (!args.contains("/by")) {
            throw new TaskonException(MESSAGE_DATE_MISSING);
        }
        try {
            String[] parts = args.split("/by", 2);
            String description = parts[0].trim();
            String date = parts[1].trim();
            return new DeadlineCommand(description, date);
        } catch (DateTimeParseException e) {
            throw new TaskonException(MESSAGE_INVALID_DATE_FORMAT);
        }
    }

    private static Command prepareEvent(String args) throws TaskonException {
        String[] partsFrom = args.split("/from", 2);
        if (partsFrom.length < 2) {
            throw new TaskonException(MESSAGE_INVALID_COMMAND_FORMAT);
        }

        String description = partsFrom[0].trim();
        String[] partsTo = partsFrom[1].split("/to", 2);
        if (partsTo.length < 2) {
            throw new TaskonException(MESSAGE_INVALID_COMMAND_FORMAT);
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
