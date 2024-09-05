package neko;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import neko.task.Deadline;
import neko.task.Event;
import neko.task.Task;
import neko.task.Todo;

/**
 * The Parser class is responsible for parsing input strings to create Task objects
 * and handle date-time formatting. .
 */
public class Parser {

    /** Formatter for parsing task deadlines and event times from a string. */
    private static final DateTimeFormatter parseFormatter =
            DateTimeFormatter.ofPattern("eee, d MMM uuuu h:mma");

    /** Formatter for parsing date-time inputs in the 'yyyy-MM-dd'T'HH:mm' format. */
    private static final DateTimeFormatter dateFormatter =
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

    /**
     * Parses a string representing a task and converts it into a Task object.
     * Supports parsing of Todo, Deadline, and Event tasks.
     *
     * @param str The string representation of a task.
     * @return A Task object corresponding to the string input, or null if the string format is invalid.
     */
    public static Task parseTask(String str) {
        char c = str.charAt(0);
        Task task = null;
        String[] parts;
        boolean isDone = false;
        String taskName;

        switch (c) {
        case 'T':
            parts = str.split("\\|", 3);
            isDone = parts[1].trim().equals("1");
            taskName = parts[2].trim();
            task = new Todo(taskName);
            break;
        case 'D':
            parts = str.split("\\|", 4);
            isDone = parts[1].trim().equals("1");
            taskName = parts[2].trim();
            String deadline = parts[3].trim();
            task = new Deadline(taskName, LocalDateTime.parse(deadline, parseFormatter));
            break;
        case 'E':
            parts = str.split("\\|", 5);
            isDone = parts[1].trim().equals("1");
            taskName = parts[2].trim();
            String start = parts[3].trim();
            String end = parts[4].trim();
            task = new Event(taskName, LocalDateTime.parse(start, parseFormatter),
                    LocalDateTime.parse(end, parseFormatter));
            break;
        default:
            break;
        }

        // Mark task as done if the string indicates it has been completed.
        if (task != null && isDone) {
            task.markAsDone();
        }
        return task;
    }

    /**
     * Extracts the command keyword from a user's input string.
     * The command is assumed to be the first word in the input.
     *
     * @param input The user's input string.
     * @return The command keyword as a string.
     */
    public static String parseCommand(String input) {
        return input.split(" ")[0];
    }

    /**
     * Parses a date-time string in the 'yyyy-MM-dd'T'HH:mm' format into a LocalDateTime object.
     *
     * @param input The string representation of a date and time.
     * @return A LocalDateTime object corresponding to the input string.
     */
    public static LocalDateTime parseTime(String input) {
        return LocalDateTime.parse(input, dateFormatter);
    }
}
