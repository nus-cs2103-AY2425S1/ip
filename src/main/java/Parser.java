import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    public static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");
    public static final Pattern TODO_ARGUMENT_FORMAT = Pattern.compile("(?<taskDescription>.+)");
    public static final Pattern EVENT_ARGUMENT_FORMAT = Pattern.compile("(?<taskDescription>.+) /from (?<startTime>\\d{2}/\\d{2}/\\d{4} \\d{4}) /to (?<endTime>\\d{2}/\\d{2}/\\d{4} \\d{4})");

    public static final Pattern DEADLINE_ARGUMENT_FORMAT = Pattern.compile("(?<taskDescription>.+) /by (?<dateTime>\\d{2}/\\d{2}/\\d{4} \\d{4})");
    public static final Pattern MARK_UNMARK_DELETE_FORMAT = Pattern.compile("(?<commandWord>mark|unmark) (?<taskNumber>\\d+)");


    public Command parseCommand(String userInput) {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());

        if (matcher.matches()) {
            return new InvalidCommand("invalid command", "for help, use /help lol noob");
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");

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
        default:
            return new InvalidCommand("invalid command, THIS SHOULD NOT EVER BE CREATED", "Test");
        }
    }

    private Command prepareDeadline(String args) {
        final Matcher matcher = DEADLINE_ARGUMENT_FORMAT.matcher(args);

        if (matcher.matches()) {
            return new InvalidCommand("wrong format deadline", "correct use deadline");
        }

        String taskDescription = matcher.group("taskDescription");
        String dateTime = matcher.group("dateTime");
        DateTimeFormatter deadlineFormatter = DateTimeFormatter.ofPattern("dd/MM/yyy HHmm");
        LocalDateTime deadline = LocalDateTime.parse(dateTime, deadlineFormatter);
        return new DeadlineCommand(taskDescription, deadline);
    }

    private Command prepareEvent(String args) {
        final Matcher matcher = EVENT_ARGUMENT_FORMAT.matcher(args);

        if (!matcher.matches()) {
            return new InvalidCommand("wrong format event", "correct use event");
        }

        DateTimeFormatter eventFormatter = DateTimeFormatter.ofPattern("dd/MM/yyy HHmm");
        String taskDescription = matcher.group("taskDescription");
        String startTime = matcher.group("startTime");
        String endTime = matcher.group("endTime");

        LocalDateTime startEvent = LocalDateTime.parse(startTime, eventFormatter);
        LocalDateTime endEvent = LocalDateTime.parse(endTime, eventFormatter);
        return new EventCommand(taskDescription, startEvent, endEvent);
    }

    private Command prepareTodo(String args) {
        final Matcher matcher = TODO_ARGUMENT_FORMAT.matcher(args);

        if (!matcher.matches()) {
            return new InvalidCommand("wrong format for todo", "correct use for todo");
        }
        String taskDescription = matcher.group("taskDescription");
        return new TodoCommand(taskDescription);
    }

    private Command prepareMark(String args) {
        final Matcher matcher = MARK_UNMARK_DELETE_FORMAT.matcher(args);

        if (!matcher.matches()) {
            return new InvalidCommand("wrong format for mark", "correct use for unmark");
        }
        int index = Integer.parseInt(matcher.group("taskNumber"));
        return new MarkCommand(index);
    }

    private Command prepareUnmark(String args) {
        final Matcher matcher = MARK_UNMARK_DELETE_FORMAT.matcher(args);

        if (!matcher.matches()) {
            return new InvalidCommand("wrong format for mark", "correct use for unmark");
        }
        int index = Integer.parseInt(matcher.group("taskNumber"));
        return new UnmarkCommand(index);
    }

    private Command prepareList() {
        return new ListCommand();
    }

    private Command prepareDelete(String args) {
        final Matcher matcher = MARK_UNMARK_DELETE_FORMAT.matcher(args);

        if (!matcher.matches()) {
            return new InvalidCommand("wrong format for delete", "correct use for delete");
        }
        int index = Integer.parseInt(matcher.group("taskNumber"));
        return new DeleteCommand(index);
    }
}
