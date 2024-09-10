package echobot.command;

import echobot.exception.DeadlineEmptyException;
import echobot.exception.EchoBotException;
import echobot.exception.EventStartEndDateEmptyException;
import echobot.exception.InvalidDeadlineFormatException;
import echobot.exception.TaskNameEmptyException;
import echobot.exception.TaskNotFoundException;
import echobot.exception.UnknownCommandException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandParser {
    public Command parse(String input) throws EchoBotException {
        input = input.trim();
        final String REGEX = "(?<command>\\S+)(?<arguments>.*)";
        Pattern pattern = Pattern.compile(REGEX);
        Matcher commandMatcher = pattern.matcher(input);

        if (!commandMatcher.find()) {
            throw new UnknownCommandException();
        }
        String command = commandMatcher.group("command");
        String arguments = "";
        if (!ListCommand.COMMAND.equals(input)) {
            arguments = commandMatcher.group("arguments").trim();
        }

        return switch (command) {
            case AddCommand.COMMAND -> this.getAddCommand(arguments);
            case ListCommand.COMMAND -> this.getListCommand(arguments);
            case MarkCommand.COMMAND -> this.getMarkCommand(arguments);
            case UnMarkCommand.COMMAND -> this.getUnmarkCommand(arguments);
            case DeleteCommand.COMMAND -> this.getDeleteCommand(arguments);
            case FindCommand.COMMAND -> this.getFindCommand(arguments);
            case ExitCommand.COMMAND -> this.getExitCommand();
            default -> throw new UnknownCommandException(command);
        };
    }

    private AddCommand getAddCommand(String arguments) throws EchoBotException {
        final String REGEX = "(?<type>\\S+)\s?(?<description>.*?( /|$))";
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(arguments);

        if (matcher.groupCount() == 1) {
            throw new TaskNameEmptyException();
        }
        if (!matcher.find()) {
            throw new UnknownCommandException();
        }

        String type = matcher.group("type");
        String description = matcher.group("description");
        return switch (type) {
            case AddCommand.TODO_COMMAND -> this.getAddToDoCommand(description);
            case AddCommand.DEADLINE_COMMAND -> this.getAddCommand(arguments, description);
            case AddCommand.EVENT_COMMAND -> this.getAddEventCommand(arguments, description);
            default -> throw new UnknownCommandException();
        };
    }

    private AddCommand getAddEventCommand(String arguments, String description) throws InvalidDeadlineFormatException, TaskNameEmptyException, EventStartEndDateEmptyException {
        Matcher matcher;
        Pattern pattern;
        description = description.substring(0, description.length() - 1).trim();
        final String REGEX = ".*?/from (?<from>\\d{2}-\\d{2}-\\d{4} \\d{2}:\\d{2}) /to (?<to>\\d{2}-\\d{2}-\\d{4} \\d{2}:\\d{2})";
        pattern = Pattern.compile(REGEX);
        matcher = pattern.matcher(arguments);
        if (!matcher.find()) {
            final String DATE_FORMAT = "dd-MM-yyyy HH:ss";
            throw new InvalidDeadlineFormatException(DATE_FORMAT);
        }
        String from = matcher.group("from");
        String to = matcher.group("to");
        return new AddCommand(description, from, to);
    }

    private AddCommand getAddCommand(String arguments, String description) throws InvalidDeadlineFormatException, TaskNameEmptyException, DeadlineEmptyException {
        final String REGEX = ".*?/by (?<deadline>\\d{2}-\\d{2}-\\d{4} \\d{2}:\\d{2})";
        Matcher matcher;
        Pattern pattern;
        description = description.substring(0, description.length() - 1).trim();
        pattern = Pattern.compile(REGEX);
        matcher = pattern.matcher(arguments);
        if (!matcher.find()) {
            final String DATE_FORMAT = "dd-MM-yyyy HH:ss";
            throw new InvalidDeadlineFormatException(DATE_FORMAT);
        }
        String deadline = matcher.group("deadline");
        return new AddCommand(description, deadline);
    }

    private AddCommand getAddToDoCommand(String description) throws TaskNameEmptyException {
        return new AddCommand(description);
    }

    public ListCommand getListCommand(String arguments) throws InvalidDeadlineFormatException {
        if (arguments.isBlank()) {
            return new ListAllCommand();
        }
        final String REGEX = "/on (?<on>\\d{2}-\\d{2}-\\d{4})";
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(arguments);
        if (!matcher.find()) {
            final String DATE_FORMAT = "dd-MM-yyyy";
            throw new InvalidDeadlineFormatException(DATE_FORMAT);
        }
        String on = matcher.group("on");
        return new ListByDateCommand(on);
    }

    private MarkCommand getMarkCommand(String arguments) throws TaskNotFoundException {
        try {
            int index = Integer.parseInt(arguments);
            return new MarkCommand(index);
        } catch (NumberFormatException e) {
            throw new TaskNotFoundException();
        }
    }

    private UnMarkCommand getUnmarkCommand(String arguments) throws TaskNotFoundException {
        try {
            int index = Integer.parseInt(arguments);
            return new UnMarkCommand(index);
        } catch (NumberFormatException e) {
            throw new TaskNotFoundException();
        }
    }

    private DeleteCommand getDeleteCommand(String arguments) throws TaskNotFoundException {
        try {
            int index = Integer.parseInt(arguments);
            return new DeleteCommand(index);
        } catch (NumberFormatException e) {
            throw new TaskNotFoundException();
        }
    }

    private FindCommand getFindCommand(String arguments) {
        return new FindCommand(arguments);
    }

    private ExitCommand getExitCommand() {
        return new ExitCommand();
    }
}
