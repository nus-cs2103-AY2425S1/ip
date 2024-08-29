package denim;

import denim.commands.*;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    public static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");
    public static final Pattern TODO_ARGUMENT_FORMAT = Pattern.compile("(?<taskDescription>.+)");
    public static final Pattern EVENT_ARGUMENT_FORMAT = Pattern.compile("(?<taskDescription>.+) /from "
            + "(?<startTime>\\d{2}/\\d{2}/\\d{4} \\d{4}) /to (?<endTime>\\d{2}/\\d{2}/\\d{4} \\d{4})");

    public static final Pattern DEADLINE_ARGUMENT_FORMAT = Pattern.compile("(?<taskDescription>.+) /by "
            + "(?<dateTime>\\d{2}/\\d{2}/\\d{4} \\d{4})");
    public static final Pattern MARK_UNMARK_DELETE_ARGUMENT_FORMAT = Pattern.compile("(?<taskNumber>\\d+)");


    public Command parseCommand(String userInput) {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());

        if (!matcher.matches()) {
            return new InvalidCommand("Invalid Denim.Commands.Command",
                    "Denim.Commands.Command for help: help");
        }

        final String commandWord = matcher.group("commandWord").trim();
        final String arguments = matcher.group("arguments").trim();

        switch (commandWord) {
        case TodoCommand.COMMAND_WORD:
            return prepareTodo(arguments);

        case EventCommand.COMMAND_WORD:
            return prepareEvent(arguments);

        case DeadlineCommand.COMMAND_WORD:
            return prepareDeadline(arguments);

        case MarkCommand.COMMAND_WORD:
            return prepareMark(arguments);

        case UnmarkCommand.COMMAND_WORD:
            return prepareUnmark(arguments);

        case DeleteCommand.COMMAND_WORD:
            return prepareDelete(arguments);

        case ListCommand.COMMAND_WORD:
            return prepareList();

        case ExitCommand.COMMAND_WORD:
            return prepareBye();

        case HelpCommand.COMMAND_WORD:
            return prepareHelp();

        default:
            return new InvalidCommand("Invalid Denim.Commands.Command",
                    "Denim.Commands.Command for help: help");
        }
    }

    private Command prepareDeadline(String args) {
        final Matcher matcher = DEADLINE_ARGUMENT_FORMAT.matcher(args);

        if (!matcher.matches()) {
            return new InvalidCommand("Wrong format for deadline command", DeadlineCommand.COMMAND_USAGE);
        }

        String taskDescription = matcher.group("taskDescription");
        String dateTime = matcher.group("dateTime");
        DateTimeFormatter deadlineFormatter = DateTimeFormatter.ofPattern("dd/MM/yyy HHmm");

        if (!isValidMonthOfYear(deadlineFormatter, dateTime)) {
            return new InvalidCommand("Invalid Date Time Format", "Format Accepted: dd/MM/yyyy HHmm");
        }

        LocalDateTime deadline = LocalDateTime.parse(dateTime, deadlineFormatter);
        return new DeadlineCommand(taskDescription, deadline);
    }

    private Command prepareEvent(String args) {
        final Matcher matcher = EVENT_ARGUMENT_FORMAT.matcher(args);

        if (!matcher.matches()) {
            return new InvalidCommand("Wrong format for event command", EventCommand.COMMAND_USAGE);
        }

        DateTimeFormatter eventFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");


        String taskDescription = matcher.group("taskDescription");
        String startTime = matcher.group("startTime");
        String endTime = matcher.group("endTime");

        if (!isValidMonthOfYear(eventFormatter, startTime) && !isValidMonthOfYear(eventFormatter, endTime)) {
            return new InvalidCommand("Invalid Date Time Format", "Format Accepted: dd/MM/yyyy HHmm");
        }

        LocalDateTime startEvent = LocalDateTime.parse(startTime, eventFormatter);
        LocalDateTime endEvent = LocalDateTime.parse(endTime, eventFormatter);
        return new EventCommand(taskDescription, startEvent, endEvent);
    }

    private Command prepareTodo(String args) {
        final Matcher matcher = TODO_ARGUMENT_FORMAT.matcher(args);

        if (!matcher.matches()) {
            return new InvalidCommand("Wrong format for todo command", TodoCommand.COMMAND_USAGE);
        }
        String taskDescription = matcher.group("taskDescription");
        return new TodoCommand(taskDescription);
    }

    private Command prepareMark(String args) {
        final Matcher matcher = MARK_UNMARK_DELETE_ARGUMENT_FORMAT.matcher(args);

        if (!matcher.matches()) {
            return new InvalidCommand("Wrong format for mark command", MarkCommand.COMMAND_USAGE);
        }
        int index = Integer.parseInt(matcher.group("taskNumber"));
        return new MarkCommand(index - Ui.indexOffset);
    }

    private Command prepareUnmark(String args) {
        final Matcher matcher = MARK_UNMARK_DELETE_ARGUMENT_FORMAT.matcher(args);

        if (!matcher.matches()) {
            return new InvalidCommand("Wrong format for unmark command", UnmarkCommand.COMMAND_USAGE);
        }
        int index = Integer.parseInt(matcher.group("taskNumber"));
        return new UnmarkCommand(index - Ui.indexOffset);
    }

    private Command prepareList() {
        return new ListCommand();
    }

    private Command prepareDelete(String args) {
        final Matcher matcher = MARK_UNMARK_DELETE_ARGUMENT_FORMAT.matcher(args);

        if (!matcher.matches()) {
            return new InvalidCommand("Wrong format for delete command", DeleteCommand.COMMAND_USAGE);
        }
        int index = Integer.parseInt(matcher.group("taskNumber"));
        return new DeleteCommand(index - Ui.indexOffset);
    }

    private Command prepareBye() {
        return new ExitCommand();
    }

    private Command prepareHelp() {
        return new HelpCommand();
    }

    private boolean isValidMonthOfYear(DateTimeFormatter formatter, String args) {
        try {
            LocalDateTime.parse(args, formatter);
        } catch (DateTimeException e) {
            return false;
        }
        return true;
    }
}
