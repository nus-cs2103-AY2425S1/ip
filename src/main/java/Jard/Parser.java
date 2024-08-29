package Jard;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The Parser class is responsible for parsing user input commands, tasks, and dates.
 * It converts user input strings into corresponding task objects or other necessary data types.
 */
public class Parser {

    /**
     * Parses the user input command into a command and its arguments.
     *
     * @param input The user input string.
     * @return A string array where the first element is the command and the second element is the arguments.
     */
    public static String[] parseCommand(String input) {
        return input.split(" ", 2);
    }

    /**
     * Parses the user input to create a corresponding Task object.
     *
     * @param input The user input string.
     * @return A Task object corresponding to the input command.
     * @throws JardException If the input command is invalid.
     */
    public static Task parseTask(String input) throws JardException {
        String[] inputParts = input.split(" ", 2);
        String command = inputParts[0];

        if (command.equals("todo")) {
            if (inputParts.length > 1) {
                return new Todo(inputParts[1]);
            } else {
                throw new JardException("The description of a todo cannot be empty.");
            }
        } else if (command.equals("deadline")) {
            if (inputParts.length > 1) {
                String[] details = inputParts[1].split("/by", 2);
                if (details.length < 2) {
                    throw new JardException("Invalid format! Deadline should be /by.");
                }
                try {
                    LocalDateTime byDateTime = parseDateTime(details[1].trim());
                    return new Deadline(details[0].trim(), byDateTime.toString());
                } catch (Exception e) {
                    throw new JardException("Invalid date and time format for deadline! Use 'yyyy-MM-dd HHmm'.");
                }
            } else {
                throw new JardException("The description of a deadline cannot be empty.");
            }
        } else if (command.equals("event")) {
            if (inputParts.length > 1) {
                String[] details = inputParts[1].split("/from|/to");
                if (details.length != 3) {
                    throw new JardException("Invalid format! Events should be /from and /to.");
                }
                try {
                    LocalDateTime fromDateTime = parseDateTime(details[1].trim());
                    LocalDateTime toDateTime = parseDateTime(details[2].trim());
                    return new Event(details[0].trim(), fromDateTime.toString(), toDateTime.toString());
                } catch (Exception e) {
                    throw new JardException("Invalid date and time format for event! Use 'yyyy-MM-dd HHmm'.");
                }
            } else {
                throw new JardException("The description of an event cannot be empty.");
            }
        } else {
            throw new JardException("Invalid command!");
        }
    }

    /**
     * Parses the task index from a user input string.
     *
     * @param input The user input string representing the task index.
     * @param taskListSize The size of the current task list.
     * @return The zero-based index of the task.
     * @throws JardException If the input is not a valid number or the index is out of bounds.
     */
    public static int parseTaskIndex(String input, int taskListSize) {
        try {
            int taskIndex = Integer.parseInt(input) - 1;
            if (taskIndex < 0 || taskIndex >= taskListSize) {
                throw new JardException("Task number does not exist!");
            }
            return taskIndex;
        } catch (NumberFormatException e) {
            throw new JardException("That's not a number!");
        }
    }

    /**
     * Parses a date and time string into a LocalDateTime object.
     *
     * @param dateTimeStr The date and time string in the format "yyyy-MM-dd HHmm".
     * @return A LocalDateTime object representing the parsed date and time.
     */
    public static LocalDateTime parseDateTime(String dateTimeStr) {
        return LocalDateTime.parse(dateTimeStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }
}
