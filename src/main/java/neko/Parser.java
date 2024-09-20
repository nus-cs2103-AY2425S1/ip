package neko;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

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
    private static final DateTimeFormatter dateTimeFormatter =
            DateTimeFormatter.ofPattern("yyyyMMdd'T'HH:mm");

    private static final DateTimeFormatter dateFormatter =
            DateTimeFormatter.ofPattern("yyyyMMdd");

    /**
     * Parses a string representing a task and converts it into a Task object.
     * Supports parsing of Todo, Deadline, and Event tasks.
     *
     * @param str The string representation of a task.
     * @return A Task object corresponding to the string input, or null if the string format is invalid.
     */
    public static Task parseTask(String str) {
        char taskType = str.charAt(0);
        Task task = null;
        String[] parts;
        boolean isDone = false;
        String taskName;

        switch (taskType) {
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
     * Extracts the keyword to find from a user's input string.
     * The keyword is assumed to be the second word in the input.
     *
     * @param input The user's input string.
     * @return The keyword as a string.
     * @throws NekoException If the input is invalid.
     */
    public static String parseKeyword(String input) throws NekoException {
        try {
            return input.split(" ")[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new NekoException("Keyword cannot be empty meow!");
        }
    }

    /**
     * Parses the user's input command for adding a task.
     * The input string is split by spaces and returns an array of strings representing
     * the individual components of the command.
     *
     * @param input The full user input string for adding a task.
     * @return A string array where each element represents a part of the command, split by spaces.
     */
    public static String[] parseAddCommand(String input) {
        return input.split(" ");
    }

    /**
     * Parses a date-time string in the 'yyyyMMdd'T'HH:mm' format into a LocalDateTime object.
     *
     * @param input The string representation of a date and time.
     * @return A LocalDateTime object corresponding to the input string.
     */
    public static LocalDateTime parseTime(String input) throws NekoException {
        try {
            return LocalDateTime.parse(input, dateTimeFormatter);
        } catch (DateTimeParseException e) {
            throw new NekoException("Invalid date/time format!\n"
                    + "Please use 'yyyyMMddTHH:mm' format.");
        }
    }

    /**
     * Parses a date string in the 'yyyyMMdd' format into a LocalDate object.
     *
     * @param input The string representation of a date.
     * @return A LocalDate object corresponding to the input string.
     */
    public static LocalDate parseDate(String input) throws NekoException {
        try {
            return LocalDate.parse(input, dateFormatter);
        } catch (DateTimeParseException e) {
            throw new NekoException("Invalid date format!\n"
                    + "Please use 'yyyyMMdd' format.");
        }
    }

    /**
     * Parses the input string to extract task indices from the user command.
     * @param input The user input string containing the command followed by task numbers.
     * @return An array of integers representing the zero-based indices of the tasks.
     * @throws NekoException If no task numbers are provided or if the input contains invalid number formats.
     */
    public static Integer[] parseIndices(String input) throws NekoException {
        String[] parts = input.split(" ");
        if (parts.length < 2) {
            throw new NekoException("No task numbers provided meow!");
        }

        Integer[] indices = new Integer[parts.length - 1];
        try {
            for (int i = 1; i < parts.length; i++) {
                indices[i - 1] = Integer.parseInt(parts[i]) - 1;
            }
        } catch (NumberFormatException e) {
            throw new NekoException("Invalid task number format meow!");
        }

        return indices;
    }
}
