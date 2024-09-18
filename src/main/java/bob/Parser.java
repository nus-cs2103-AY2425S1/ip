package bob;

import java.time.LocalDate;
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
    /** The date-time format used for parsing and formatting. */
    // private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HHmm";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

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
     * Parses a string representation of a task to create and return a Task object.
     * The string should be in a specific format, with task details separated by commas. The task type
     * determines the type of Task to be created (ToDo, Deadline, or Event).
     *
     * @param line The string representation of the task.
     * @return A Task object corresponding to the parsed details.
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
     * Parses the task number from the user input.
     *
     * @param taskDetails The string containing the task number.
     * @return The parsed task number as an integer.
     * @throws BobException If the task number is invalid or missing.
     */
    public static int parseTaskNumber(String taskDetails) throws BobException {
        if (taskDetails.isEmpty()) {
            throw new BobException("Please provide a task number.");
        }
        try {
            return Integer.parseInt(taskDetails);
        } catch (NumberFormatException e) {
            throw new BobException("The task number provided is invalid.");
        }
    }

    /**
     * Parses a date string into a LocalDate object.
     *
     * @param dateStr The date string to be parsed.
     * @return The parsed LocalDate object.
     * @throws BobException If the date format is invalid.
     */
    public static LocalDate parseDate(String dateStr) throws BobException {
        try {
            return LocalDate.parse(dateStr, DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new BobException("Invalid date format. Required format: relevant yyyy-MM-dd");
        }
    }

    /**
     * Parses a string representation of date and time into a LocalDateTime object.
     * The expected format is "yyyy-MM-dd HHmm".
     *
     * @param dateTimeStr The string representation of the date and time.
     * @return A LocalDateTime object representing the parsed date and time.
     * @throws BobException If incorrect data or time format.
     */
    public static LocalDateTime parseDateTime(String dateTimeStr) throws BobException {
        try {
            LocalDateTime dateTime = LocalDateTime.parse(dateTimeStr, DATE_TIME_FORMATTER);
            if (dateTime.isBefore(LocalDateTime.now())) {
                throw new BobException("Oops! The date you provided is in the past. Kindly provide a future date.");
            }
            return dateTime;
        } catch (DateTimeParseException e) {
            throw new BobException(
                    "Please provide the correct date and 24-hour time format: yyyy-mm-dd HHmm\n"
                    + "Eg. 2024-08-27 1530 for Aug 27 2024 03.30pm");
        }
    }

    /**
     * Converts a LocalDateTime object to its string representation.
     *
     * @param dateTimeStr The LocalDateTime object to be formatted.
     * @return The string representation of the date and time.
     */
    public static String getDateTimeStr(LocalDateTime dateTimeStr) {
        assert dateTimeStr != null : "DateTime object should not be null.";
        return dateTimeStr.format(DATE_TIME_FORMATTER);
    }
}
