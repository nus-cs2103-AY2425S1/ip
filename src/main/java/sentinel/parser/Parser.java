package sentinel.parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import sentinel.SentinelException;
import sentinel.task.Deadline;
import sentinel.task.Event;
import sentinel.task.Task;
import sentinel.task.Todo;

/**
 * Parser for Sentinel chatbot.
 */
public class Parser {
    public static final String DATE_INPUT_PATTERN = "MMM d yyy";
    public static final String DATE_OUTPUT_PATTERN = "dd/M/yyy";

    /**
     * Parses a string in a task format into its Task representation.
     *
     * @param string String in task format.
     * @return Task represented by the string.
     */
    public static Task parseStringToTask(String string) {
        Matcher m = matchTaskString(string);

        if (m.find()) {
            String taskType = m.group("taskType"); // sentinel.task.Task type (e.g., "T")
            boolean isDone = m.group("status").trim().equals("X"); // sentinel.task.Task completion status
            String taskName = m.group("name").trim(); // sentinel.task.Task name
            String fromTime = m.group("from"); // From timeframe (if present)
            String toTime = m.group("to"); // To timeframe (if present)
            String byTime = m.group("by"); // By timeframe (if present)

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_OUTPUT_PATTERN);

            return makeNewTaskFromData(taskType, taskName, isDone, byTime, formatter, fromTime, toTime);
        }

        return null;
    }

    /**
     * Maps each task type string representation to its maker function.
     *
     * @param taskType Type of the task.
     * @param taskName Name of the task.
     * @param isDone Whether the task is done.
     * @param byTime By timeframe (for deadlines).
     * @param fromTime From timeframe (for events).
     * @param toTime To timeframe (for events).
     * @param formatter Formatter to use when formatting the dates.
     * @return Task represented by the string.
     */
    private static Task makeNewTaskFromData(String taskType, String taskName, boolean isDone, String byTime,
                                            DateTimeFormatter formatter, String fromTime, String toTime) {
        return switch (taskType) {
        case "T" -> makeNewTodo(taskName, isDone);
        case "D" -> makeNewDeadline(taskName, isDone, byTime, formatter);
        case "E" -> makeNewEvent(taskName, isDone, formatter, fromTime, toTime);
        default -> null;
        };
    }

    /**
     * Makes a new event from the given arguments.
     *
     * @param taskName Name of the task.
     * @param isDone Whether the task is done.
     * @param fromTime From timeframe (for events).
     * @param toTime To timeframe (for events).
     * @param formatter Formatter to use when formatting the dates.
     * @return Event represented by the string.
     */
    private static Event makeNewEvent(String taskName, boolean isDone, DateTimeFormatter formatter, String fromTime,
                                      String toTime) {
        LocalDate formattedStartTime = LocalDate.parse(fromTime, formatter);
        LocalDate formattedEndTime = LocalDate.parse(toTime, formatter);

        Event newEvent = new Event(taskName, formattedStartTime, formattedEndTime);
        if (isDone) {
            newEvent.markAsDone();
        }
        return newEvent;
    }

    /**
     * Makes a new deadline from the given arguments.
     *
     * @param taskName Name of the task.
     * @param isDone Whether the task is done.
     * @param byTime By timeframe (for deadlines).
     * @param formatter Formatter to use when formatting the dates.
     * @return Deadline represented by the string.
     */
    private static Deadline makeNewDeadline(String taskName, boolean isDone, String byTime,
                                            DateTimeFormatter formatter) {
        LocalDate formattedTime = LocalDate.parse(byTime, formatter);

        Deadline newDeadline = new Deadline(taskName, formattedTime);
        if (isDone) {
            newDeadline.markAsDone();
        }
        return newDeadline;
    }

    /**
     * Makes a new todo from the given arguments.
     *
     * @param taskName Name of the task.
     * @param isDone Whether the task is done.
     * @return Todo represented by the string.
     */
    private static Todo makeNewTodo(String taskName, boolean isDone) {
        Todo newTodo = new Todo(taskName);
        if (isDone) {
            newTodo.markAsDone();
        }
        return newTodo;
    }

    /**
     * Matches a given string to the task regex.
     *
     * @param string String representation of task to be matched.
     * @return Matcher.
     */
    private static Matcher matchTaskString(String string) {
        String regex = "\\d+\\. \\[(?<taskType>[A-Z])]\\[(?<status>[ X])]"
                + " (?<name>[^(]+)(?:\\(by: (?<by>[^)]+)\\))?(?:\\(from: (?<from>[^)]+) to: (?<to>[^)]+)\\))?";

        Pattern r = Pattern.compile(regex);
        return r.matcher(string);
    }

    /**
     * Parses a string in a date format into its LocalDate representation.
     *
     * @param stringDate String in date format.
     * @return LocalDate represented by the string.
     * @throws SentinelException if there was an error parsing the date time.
     */
    public static LocalDate parseStringToDate(String stringDate) throws SentinelException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_INPUT_PATTERN);
            return LocalDate.parse(stringDate.trim(), formatter);
        } catch (DateTimeParseException e) {
            throw new SentinelException("Invalid date time format, I can only read formats in dd/M/yyyy pattern!");
        }
    }
}
