package jeff.parser;

import jeff.command.*;
import jeff.exception.JeffException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a filter to sort out the user's input.
 */
public class Parser {
    /**
     * Returns a Command based on the user's input.
     *
     * @param input User's input.
     * @return a Command.
     * @throws JeffException if the user's input is not a command available.
     */
    public static Command parse(String input) throws JeffException {
        // Filters the input based on the command given
        if (input.equals("list")) {
            return new ListCommand(input);
        } else if (input.equals("bye")) {
            return new ExitCommand(input);
        } else if (input.startsWith("mark")) {
            return new MarkCommand(input);
        } else if (input.startsWith("unmark")) {
            return new UnmarkCommand(input);
        } else if (input.startsWith("delete")) {
            return new DeleteCommand(input);
        } else if (input.startsWith("todo") || input.startsWith("deadline") || input.startsWith("event")) {
            return new AddCommand(input);
        } else if (input.startsWith("task")) {
            return new DateCommand(input);
        } else {
            throw new JeffException();
        }
    }

    /**
     * Returns a LocalDateTime object based on the user's input.
     *
     * @param input User's input.
     * @return a LocalDateTime object with the date and time specified by the user.
     * @throws DateTimeParseException if the user's input is in the wrong format.
     */
    public static LocalDateTime getLocalDateTime(String input) throws DateTimeParseException {
        try {
            return LocalDateTime.parse(input, DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm a"));
        } catch (DateTimeParseException e) {
            return LocalDateTime.parse(input, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        }
    }
}
