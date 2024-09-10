package Buu;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The Parser class is responsible for interpreting user input and converting it
 * into executable commands or parsing date and time strings into LocalDateTime objects.
 */
public class Parser {
    /**
     * Parses the user input and returns the corresponding Command object.
     * This method identifies the type of command based on the input and creates the appropriate Command subclass.
     *
     * @param input The user input string to be parsed.
     * @return The corresponding Command object based on the input.
     * @throws GPTException If the input cannot be parsed into a valid command.
     */
    public static Command parseCommand(String input) throws GPTException {
        if (input.equalsIgnoreCase("bye")) {
            return new ExitCommand();
        } else if (input.equalsIgnoreCase("list")) {
            return new ListCommand();
        } else if (input.startsWith("mark")) {
            return new MarkCommand(input);
        } else if (input.startsWith("unmark")) {
            return new UnmarkCommand(input);
        } else if (input.startsWith("delete")) {
            return new DeleteCommand(input);
        } else if (input.startsWith("todo")) {
            return new TodoCommand(input);
        } else if (input.startsWith("deadline")) {
            return new DeadlineCommand(input);
        } else if (input.startsWith("event")) {
            return new EventCommand(input);
        } else if (input.startsWith("find")) {
            return new FindCommand(input);
        } else {
            return new HelpCommand(); // Return a HelpCommand when an unrecognized input is entered
        }
    }

    /**
     * Parses a date and time string into a LocalDateTime object.
     * This method attempts to parse the input string using several predefined date and time formats.
     * If the input matches one of the formats, a corresponding LocalDateTime object is returned.
     *
     * @param dateTimeStr The date and time string to be parsed.
     * @return A LocalDateTime object representing the parsed date and time, or null if the input is invalid.
     */
    public static LocalDateTime parseDateTime(String dateTimeStr) {
        LocalDateTime dateTime = parseWithDateTimeFormatter(dateTimeStr, "yyyy-MM-dd HHmm");
        if (dateTime != null) {
            return dateTime;
        }

        dateTime = parseWithDateTimeFormatter(dateTimeStr, "d/M/yyyy HHmm");
        if (dateTime != null) {
            return dateTime;
        }

        dateTime = parseWithDateTimeFormatter(dateTimeStr, "yyyy-M-d HHmm");
        if (dateTime != null) {
            return dateTime;
        }

        LocalDate date = parseWithDateFormatter(dateTimeStr, "d/M/yyyy");
        if (date != null) {
            return date.atStartOfDay();
        }

        date = parseWithDateFormatter(dateTimeStr, "yyyy-MM-dd");
        if (date != null) {
            return date.atStartOfDay();
        }

        System.out.println("Invalid date format. Please use yyyy-MM-dd HHmm, d/M/yyyy HHmm, or yyyy-MM-dd format.");
        return null;
    }

    /**
     * Attempts to parse a date-time string with the given format.
     * @param dateTimeStr The date-time string to parse.
     * @param pattern The date-time pattern to use.
     * @return A LocalDateTime object if parsing is successful, or null if an exception occurs.
     */
    private static LocalDateTime parseWithDateTimeFormatter(String dateTimeStr, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        try {
            return LocalDateTime.parse(dateTimeStr, formatter);
        } catch (DateTimeParseException e) {
            return null; // Return null to indicate a parsing failure
        }
    }

    /**
     * Attempts to parse a date string with the given format.
     * @param dateStr The date string to parse.
     * @param pattern The date pattern to use.
     * @return A LocalDate object if parsing is successful, or null if an exception occurs.
     */
    private static LocalDate parseWithDateFormatter(String dateStr, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        try {
            return LocalDate.parse(dateStr, formatter);
        } catch (DateTimeParseException e) {
            return null; // Return null to indicate a parsing failure
        }
    }
}
