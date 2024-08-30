package parser;

import task.Task;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import exceptions.TaskNotFoundException;
import exceptions.MissingDeadlineException;
import exceptions.MissingEventTimeException;

/**
 * Handles parsing and processing of user commands and data in the BeeBot application.
 * <p>
 * Provides methods for retrieving tasks, concatenating strings, and formatting dates.
 * </p>
 */
public class Parser {

    /**
     * Prints a message to the console, enclosed in lines for emphasis.
     *
     * @param cmd The message to be displayed to the user.
     */
    public static void speak(String cmd) {
        System.out.println("________________________\n"
                + cmd
                + "________________________\n");
    }

    /**
     * Retrieves a task from the list by its number.
     *
     * @param taskList The list of tasks from which to retrieve the task.
     * @param taskNum The number of the task to retrieve (1-based index).
     * @return The task corresponding to the given number.
     * @throws TaskNotFoundException If the task number is invalid or out of range.
     */
    public static Task getTask(ArrayList<Task> taskList, int taskNum) throws TaskNotFoundException {
        if (taskNum <= 0 || taskNum > taskList.size()) {
            throw new TaskNotFoundException("Task not found.\n");
        }
        return taskList.get(taskNum - 1);
    }

    /**
     * Concatenates parts of a string array starting from a specified index.
     *
     * @param parts The array of string parts to concatenate.
     * @param start The starting index in the array for concatenation.
     * @return The concatenated string.
     */
    public static String concatenate(String[] parts, int start) {
        StringBuilder result = new StringBuilder();
        for (int i = start; i < parts.length - 1; i++) {
            result.append(parts[i]).append(" ");
        }
        result.append(parts[parts.length - 1]);
        return result.toString();
    }

    /**
     * Concatenates parts of a string array until a specified delimiter is found.
     *
     * @param parts The array of string parts to concatenate.
     * @param delimiter The delimiter indicating the end of concatenation.
     * @return The concatenated string.
     * @throws MissingDeadlineException If the delimiter is missing or the format is incorrect
     */
    public static String concatenateUntil(String[] parts, String delimiter) throws MissingDeadlineException {
        StringBuilder result = new StringBuilder();
        int i = 1;
        while (!parts[i].equals(delimiter)) {
            result.append(parts[i]).append(" ");
            i++;
            if (i == parts.length) {
                throw new MissingDeadlineException("Missing `" + delimiter + "` or incorrect format.\n");
            }
        }
        return result.toString().trim();
    }

    /**
     * Retrieves the date or time information following a specified delimiter.
     *
     * @param parts The array of strings containing the date or time information.
     * @param delimiter The delimiter indicating where the date/time starts.
     * @return The concatenated date/time string following the delimiter.
     * @throws MissingEventTimeException If the delimiter is missing or no date/time follows.
     */
    public static String getFollowingDate(String[] parts, String delimiter) throws MissingEventTimeException {
        int i = 0;
        while (!parts[i].equals(delimiter)) {
            i++;
            if (i == parts.length) {
                throw new MissingEventTimeException("Missing `" + delimiter + "` or incorrect format.\n");
            }
        }
        i++;
        if (i == parts.length) {
            throw new MissingEventTimeException("Please provide a date/time after `" + delimiter + "`.\n");
        }

        return concatenate(parts, i);
    }

    /**
     * Retrieves the date or time information between two specified delimiters.
     *
     * @param parts The array of strings containing the date or time information.
     * @param delimiter The delimiter indicating where the date/time starts.
     * @param stopDelimiter The delimiter indicating where the date/time ends.
     * @return The concatenated date/time string between the delimiters.
     * @throws MissingEventTimeException If the delimiter or stop delimiter is missing or no date/time is provided.
     */
    public static String getFollowingDate(String[] parts, String delimiter, String stopDelimiter)
            throws MissingEventTimeException {
        int i = 0;
        while (!parts[i].equals(delimiter)) {
            i++;
            if (i == parts.length) {
                throw new MissingEventTimeException("Missing `" + delimiter + "` or incorrect format.\n");
            }
        }
        i++;
        if (i == parts.length || parts[i].equals(stopDelimiter)) {
            throw new MissingEventTimeException("Please provide a date/time after `" + delimiter + "`.\n");
        }

        StringBuilder date = new StringBuilder();
        while (i < parts.length && !parts[i].equals(stopDelimiter)) {
            date.append(parts[i]).append(" ");
            i++;
        }
        return date.toString().trim();
    }

    /**
     * Converts a date string from the format "yyyy-MM-dd" to "dd MMMM yyyy".
     *
     * @param date The date string in "yyyy-MM-dd" format.
     * @return The formatted date string in "dd MMMM yyyy" format.
     */
    public static String dateConverter(String date) {
        String[] words = date.split("\\s+");
        LocalDate parsedDate = LocalDate.parse(words[0]);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
        String formattedDate = parsedDate.format(formatter);
        words[0] = formattedDate;
        return String.join(" ", words);
    }
}
