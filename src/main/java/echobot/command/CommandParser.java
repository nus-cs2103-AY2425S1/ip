package echobot.command;

import echobot.exception.EchoBotException;
import echobot.exception.InvalidDeadlineFormatException;
import echobot.exception.TaskNameEmptyException;
import echobot.exception.TaskNotFoundException;
import echobot.exception.UnknownCommandException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandParser {
    public Command parse(String input) throws EchoBotException {
        input = input.trim();
        Pattern pattern = Pattern.compile("(?<command>\\S+)(?<arguments>.*)");
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
        Pattern pattern = Pattern.compile("(?<type>\\S+)\s?(?<description>.*?( /|$))");
        Matcher matcher = pattern.matcher(arguments);

        if (matcher.groupCount() == 1) {
            throw new TaskNameEmptyException();
        }
        if (!matcher.find()) {
            throw new UnknownCommandException();
        }

        String type = matcher.group("type");
        String description = matcher.group("description");
        switch (type) {
        case AddCommand.TODO_COMMAND: {
            return new AddCommand(description);
        }
        case AddCommand.DEADLINE_COMMAND: {
            description = description.substring(0, description.length() - 1).trim();
            pattern = Pattern.compile(".*?/by (?<deadline>\\d{2}-\\d{2}-\\d{4} \\d{2}:\\d{2})");
            matcher = pattern.matcher(arguments);
            if (!matcher.find()) {
                throw new InvalidDeadlineFormatException("dd-MM-yyyy HH:ss");
            }
            String deadline = matcher.group("deadline");
            return new AddCommand(description, deadline);
        }
        case AddCommand.EVENT_COMMAND: {
            description = description.substring(0, description.length() - 1).trim();
            pattern = Pattern.compile(".*?/from (?<from>\\d{2}-\\d{2}-\\d{4} \\d{2}:\\d{2}) /to (?<to>\\d{2}-\\d{2}-\\d{4} \\d{2}:\\d{2})");
            matcher = pattern.matcher(arguments);
            if (!matcher.find()) {
                throw new InvalidDeadlineFormatException("dd-MM-yyyy HH:ss");
            }
            String from = matcher.group("from");
            String to = matcher.group("to");
            return new AddCommand(description, from, to);
        }
        }
        throw new UnknownCommandException();
    }

    public ListCommand getListCommand(String arguments) throws InvalidDeadlineFormatException {
        if (arguments.isBlank()) {
            return new ListAllCommand();
        }
        Pattern pattern = Pattern.compile("/on (?<on>\\d{2}-\\d{2}-\\d{4})");
        Matcher matcher = pattern.matcher(arguments);
        if (!matcher.find()) {
            throw new InvalidDeadlineFormatException("dd-MM-yyyy");
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
