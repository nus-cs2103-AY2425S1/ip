package Buu;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The Parser class is responsible for interpreting user input and converting it
 * into executable commands.
 */
public class Parser {
    // Define the formatter to match 'yyyy-MM-dd HH:mm'
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    /**
     * Parses the user input and returns the corresponding Command object.
     * This method identifies the type of command based on the input and creates the appropriate Command subclass.
     *
     * @param input The user input string to be parsed.
     * @return The corresponding Command object based on the input.
     * @throws TaskException If the input cannot be parsed into a valid command.
     */
    public static Command parseCommand(String input) throws TaskException {
        if (input.equalsIgnoreCase("bye")) {
            return new ExitCommand();
        } else if (input.equalsIgnoreCase("list")) {
            return new ListCommand();
        } else if (input.startsWith("mark")) {
            return new MarkCommand(input);
        } else if (input.startsWith("unmark")) {
            return new UnmarkCommand(input);
        } else if (input.startsWith("priority")) {
            return new SetPriorityCommand(input);
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
     *
     * @param dateTimeStr The date and time string to be parsed.
     * @return A LocalDateTime object if parsing is successful.
     * @throws DateTimeParseException if the date-time string cannot be parsed.
     */
    public static LocalDateTime parseDateTime(String dateTimeStr) {
        try {
            return LocalDateTime.parse(dateTimeStr, DATE_TIME_FORMATTER);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date/time format. Please use 'yyyy-MM-dd HH:mm'.");
            throw e;
        }
    }
}
