package bob;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import bob.task.Deadline;
import bob.task.Event;
import bob.task.Task;
import bob.task.ToDo;

/**
 * A utility class for parsing user input and task details in the Bob application.
 * The Parser class provides methods to parse commands and tasks, and to format date and time.
 */
public class Parser {

    /**
     * Parses the user input to obtain the corresponding command.
     *
     * @param userInput The user input string containing the command.
     * @return The corresponding command as an enum value of {@link Bob.Command}.
     * @throws BobException If the user input is empty or consists only of whitespaces.
     */
    public static Bob.Command parseCommand(String userInput) throws BobException {
        if (userInput.isEmpty() || userInput.equals(" ")) {
            throw new BobException("You did not key in anything...");
        }
        String commandStr = userInput.split(" ", 2)[0].toUpperCase();
        try {
            return Bob.Command.valueOf(commandStr);
        } catch (IllegalArgumentException e) {
            return Bob.Command.UNKNOWN;
        }
    }

    /**
     * Parses a string representation of a task to create and return a {@link Task} object.
     * The string should be in a specific format, with task details separated by commas. The task type
     * determines the type of {@link Task} to be created (ToDo, Deadline, or Event).
     *
     * @param line The string representation of the task.
     * @return A {@link Task} object corresponding to the parsed details.
     * @throws BobException If task type is unknown or incorrect format.
     */
    public static Task parseTask(String line) throws BobException {
        String[] details = line.split(",");
        String type = details[0];
        boolean isDone = details[1].equals("1");
        String description = details[2];

        assert type.equals("T") || type.equals("D") || type.equals("E") : "task type should be either T, D or E";
        switch (type) {
        case "T":
            return new ToDo(description, isDone);
        case "D":
            String byStr = details[3];
            LocalDateTime by = parseDateTime(byStr);
            return new Deadline(description, isDone, by);
        case "E":
            String fromStr = details[3];
            String toStr = details[4];
            LocalDateTime from = parseDateTime(fromStr);
            LocalDateTime to = parseDateTime(toStr);
            return new Event(description, isDone, from, to);
        default:
            throw new BobException("Unknown task type: " + type);
        }
    }

    /**
     * Extracts and returns the task details from the user input string.
     *
     * @param userInput The input string from the user.
     * @return The task details as a string.
     */
    public static String getTaskDetails(String userInput) {
        String[] args = userInput.split(" ", 2);
        if (args.length < 2) {
            return "";
        }
        return args[1].trim();
    }

    /**
     * Parses a string representation of date and time into a {@link LocalDateTime} object.
     * The expected format is "yyyy-MM-dd HHmm".
     *
     * @param dateTimeStr The string representation of the date and time.
     * @return A {@link LocalDateTime} object representing the parsed date and time.
     * @throws BobException If incorrect data or time format.
     */
    public static LocalDateTime parseDateTime(String dateTimeStr) throws BobException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            return LocalDateTime.parse(dateTimeStr, formatter);
        } catch (DateTimeParseException e) {
            throw new BobException(
                    "Please provide the correct date and 24-hour time format: yyyy-mm-dd HHmm\n"
                    + "Eg. 2024-08-27 1530 for Aug 27 2024 03.30pm");
        }
    }

    /**
     * Converts a {@link LocalDateTime} object to its string representation.
     *
     * @param dateTimeStr The {@link LocalDateTime} object to be formatted.
     * @return The string representation of the date and time.
     */
    public static String getDateTimeStr(LocalDateTime dateTimeStr) {
        assert dateTimeStr != null : "DateTime object should not be null.";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return dateTimeStr.format(formatter);
    }
}
