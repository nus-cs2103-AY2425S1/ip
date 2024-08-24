package Parser;

import commands.*;
import exceptions.CowExceptions;
import exceptions.MissingParametersException;
import message.Messages;
import tasks.Deadlines;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    public static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    public static Command parse(String userInput) throws CowExceptions {
        Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());

        if (!matcher.matches()) {
            return new IncorrectCommand(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT
                    , HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");

        switch (commandWord) {
            case ListCommand.COMMAND_WORD:
                return new ListCommand();
            case ByeCommand.COMMAND_WORD:
                return new ByeCommand();
            case TodoCommand.COMMAND_WORD:
                return prepareTodo(arguments);
            case DeadlineCommand.COMMAND_WORD:
                return prepareDeadline(arguments);
            case EventCommand.COMMAND_WORD:
                return prepareEvent(arguments);
            case MarkCommand.COMMAND_WORD:
                return new MarkCommand(getMarkUnmarkInt(arguments));
            case UnmarkCommand.COMMAND_WORD:
                return new UnmarkCommand(getMarkUnmarkInt(arguments));
            case DeleteCommand.COMMAND_WORD:
                return new DeleteCommand(getMarkUnmarkInt(arguments));
            case DueCommand.COMMAND_WORD:
                return prepDue(arguments);
            default:
                return new HelpCommand();
        }
    }

    private static int getMarkUnmarkInt(String args) throws CowExceptions {
        try {
            return Integer.parseInt(args.trim());
        } catch (NumberFormatException e) {
            throw new CowExceptions(Messages.INDEX_INVALID);
        }
    }

    private static Command prepDue(String args) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        try {
            LocalDate parsedDate = LocalDate.parse(args.trim(), formatter);
            return new DueCommand(parsedDate);
        } catch (DateTimeParseException e) {
            return new IncorrectCommand(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT
                    , DueCommand.MESSAGE_USAGE));
        }
    }

    private static Command prepareDeadline(String args) {
        Pattern pattern = Pattern.compile("^(?<desc>.+?)\\s*/by\\s*(?<by>.*)$");
        Matcher matcher = pattern.matcher(args.trim());
        if (!matcher.matches()) {
            return new IncorrectCommand(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT
                    , DeadlineCommand.MESSAGE_USAGE));
        }

        try {
            return new DeadlineCommand(matcher.group("desc"),
                    LocalDateTime.parse(matcher.group("by"), Deadlines.FORMAT));
        } catch (DateTimeParseException e) {
            return new IncorrectCommand(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT
                    , DeadlineCommand.MESSAGE_USAGE));
        }
    }

    private static Command prepareEvent(String args) {
        Pattern pattern = Pattern.compile("^(?<desc>.+?)\\s*/from\\s*(?<from>.+?)\\s*/to\\s*(?<to>.+)$");
        Matcher matcher = pattern.matcher(args.trim());
        if (!matcher.matches()) {
            return new IncorrectCommand(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT
                    , EventCommand.MESSAGE_USAGE));
        }

        return new EventCommand(matcher.group("desc"),
                matcher.group("from"), matcher.group("to"));
    }

    private static Command prepareTodo(String args) {
        if (args == null || args.isEmpty()) {
            return new IncorrectCommand(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT
                    , TodoCommand.MESSAGE_USAGE));
        }
        return new TodoCommand(args);
    }
}
