package duke.parser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import duke.commands.AddTaskCommand;
import duke.commands.Command;
import duke.commands.DeleteTaskCommand;
import duke.commands.FilterTaskCommand;
import duke.commands.ListTaskCommand;
import duke.commands.MarkTaskCommand;

/**
 * The Parser class is responsible for interpreting user input and converting it
 * into appropriate commands for the DailyTasks application. It also contains methods
 * for parsing dates and times in different formats.
 */
public class Parser {

    /** A list of accepted date and time formatters for parsing user input. */
    private static final List<DateTimeFormatter> inputFormatters = new ArrayList<>();

    // Static initializer block to populate the inputFormatters list
    static {
        inputFormatters.add(DateTimeFormatter.ofPattern("d/M/yyyy HHmm")); // e.g., "2/12/2019 1800"
        inputFormatters.add(DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a")); // e.g., "Dec 2 2019, 6:00 PM"
        inputFormatters.add(DateTimeFormatter.ofPattern("d/M/yyyy")); // e.g., "2/12/2019"
        inputFormatters.add(DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a")); // e.g., "Jul 2 2019, 5:00 pm");
    }

    /**
     * Parses the user's input string and returns the corresponding command.
     * The parser determines the type of command (e.g., list, mark, unmark, delete, filter, or add task)
     * based on the input.
     *
     * @param userInput The raw input string from the user.
     * @return The command object representing the action to be taken.
     */
    public static Command parseUserInput(String userInput) {
        if (userInput.equals("list")) {
            return new ListTaskCommand();
        } else if (userInput.startsWith("unmark")) {
            return new MarkTaskCommand(false, Parser.parseTaskIndex(userInput));
        } else if (userInput.startsWith("mark")) {
            return new MarkTaskCommand(true, Parser.parseTaskIndex(userInput));
        } else if (userInput.startsWith("delete")) {
            return new DeleteTaskCommand(Parser.parseTaskIndex(userInput));
        } else if (userInput.startsWith("filter")) {
            String dateString = userInput.split(" ", 2)[1];
            LocalDateTime dateTime = Parser.parseDateTime(dateString);
            return new FilterTaskCommand(dateTime);
        } else { // we try to add a task (todos/deadline/event)
            return new AddTaskCommand(userInput);
        }
    }

    /**
     * Extracts the task index from a user command string.
     *
     * @param userInput The raw input string from the user, typically containing the command and a task index.
     * @return The task index (0-based), or -1 if the index could not be parsed.
     */
    public static int parseTaskIndex(String userInput) {
        try {
            return Integer.parseInt(userInput.split(" ", 2)[1]) - 1;
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid task number provided.");
            return -1;
        }
    }

    /**
     * Parses a date string into a LocalDateTime object using predefined formats.
     * It attempts to match the date string with different formats until one is successful.
     *
     * @param dateTimeStr The date string to be parsed.
     * @return The parsed LocalDateTime object, or null if no valid format was found.
     */
    public static LocalDateTime parseDateTime(String dateTimeStr) {
        for (DateTimeFormatter formatter : Parser.inputFormatters) {
            try {
                // Check if the formatter is for date-only format and handle it separately
                if (Objects.equals(formatter, DateTimeFormatter.ofPattern("d/M/yyyy"))) {
                    LocalDate parsedDate = LocalDate.parse(dateTimeStr, formatter);
                    return parsedDate.atStartOfDay(); // Default to midnight if only the date is provided
                } else {
                    return LocalDateTime.parse(dateTimeStr, formatter);
                }
            } catch (DateTimeParseException e) {
                // If the format doesn't match, continue trying the next formatter
            }
        }
        return null; // If no format matched, return null
    }
}
