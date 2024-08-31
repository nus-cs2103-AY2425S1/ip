import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Parser {

    private static final Pattern BASIC_COMMAND_PATTERN = Pattern.compile("(?<command>\\S+)(?<arguments>.*)");
    private static final Pattern DATETIME_COMMAND_PATTERN =
            Pattern.compile("(?<task>[^/]+)\\s*/\\s*(?<date>\\d{2}/\\d{2}/\\d{4})\\s+(?<time>\\d{2}:\\d{2})");
    private static final Pattern DATE_COMMAND_PATTERN =
            Pattern.compile("(?<task>[^/]+)\\s*/\\s*(?<date>\\d{2}/\\d{2}/\\d{4})\\s");
    private static final Pattern INDEXED_COMMAND_PATTERN = Pattern.compile("(?<index>\\d+)");

    public Parser() {
    }

    public Command parseCommand(String userInput) {
        Matcher matcher = BASIC_COMMAND_PATTERN.matcher(userInput.trim());

        if (!matcher.matches()) {
            //Invalid command exception
            return null;
        }
        String command = matcher.group("command");
        String arguments = matcher.group("arguments");

        return switch (command) {
            case ToDoCommand.COMMAND_WORD -> new ToDoCommand(arguments);
            case ListCommand.COMMAND_WORD -> new ListCommand();
            case DeadlineCommand.COMMAND_WORD -> prepDeadline(arguments);
            case MarkCommand.COMMAND_WORD -> new MarkCommand(prepIndexedCommand(arguments));
            case UnmarkCommand.COMMAND_WORD -> new UnmarkCommand(prepIndexedCommand(arguments));
            case EventCommand.COMMAND_WORD -> prepEvent(arguments);
            case ExitCommand.COMMAND_WORD -> new ExitCommand();
            default ->
                //placeholder for future; should default to help
                    null;
        };
    }

    private Command prepDeadline(String arguments) {
        Matcher deadlineMatcher = DATETIME_COMMAND_PATTERN.matcher(arguments);
        if (deadlineMatcher.matches()) {
            String task = deadlineMatcher.group("task");
            String date = deadlineMatcher.group("date");
            String time = deadlineMatcher.group("time");
            return new DeadlineCommand(task, date, time);
        } else {
            deadlineMatcher = DATE_COMMAND_PATTERN.matcher(arguments);
            if (deadlineMatcher.matches()) {
                String task = deadlineMatcher.group("task");
                String date = deadlineMatcher.group("date");
                return new DeadlineCommand(task, date);
            } else {
                //throw exception
                return null;
            }
        }
    }

    private Command prepEvent(String arguments) {
        Matcher eventMatcher = DATETIME_COMMAND_PATTERN.matcher(arguments);
        if (eventMatcher.matches()) {
            String task = eventMatcher.group("task");
            String date = eventMatcher.group("date");
            String time = eventMatcher.group("time");
            return new EventCommand(task, date, time);
        } else {
            eventMatcher = DATE_COMMAND_PATTERN.matcher(arguments);
            if (eventMatcher.matches()) {
                String task = eventMatcher.group("task");
                String date = eventMatcher.group("date");
                return new EventCommand(task, date);
            } else {
                //throw exception
                return null;
            }
        }
    }

    private int prepIndexedCommand(String arguments) {
        Matcher indexMatcher = INDEXED_COMMAND_PATTERN.matcher(arguments);
        if (indexMatcher.matches()) {
            String index = indexMatcher.group("index");
            return Integer.parseInt(index);
        } else {
            //throw invalid format exception
            return -1;
        }
    }
}
