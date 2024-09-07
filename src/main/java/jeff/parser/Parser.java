package jeff.parser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import jeff.command.AddDeadlineCommand;
import jeff.command.AddEventCommand;
import jeff.command.AddToDoCommand;
import jeff.command.Command;
import jeff.command.DateCommand;
import jeff.command.DeleteCommand;
import jeff.command.ExitCommand;
import jeff.command.FindCommand;
import jeff.command.ListCommand;
import jeff.command.MarkCommand;
import jeff.command.UnmarkCommand;
import jeff.exception.JeffException;

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
        } else if (input.startsWith("todo")) {
            return new AddToDoCommand(input);
        } else if (input.startsWith("deadline")) {
            return new AddDeadlineCommand(input);
        } else if (input.startsWith("event")) {
            return new AddEventCommand(input);
        } else if (input.startsWith("date")) {
            return new DateCommand(input);
        } else if (input.startsWith("find")) {
            return new FindCommand(input);
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
            return LocalDateTime.parse(input, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        } catch (DateTimeParseException e) {
            return LocalDateTime.parse(input, DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm a"));
        }
    }

    /**
     * Returns the string representation of a LocalDate object.
     *
     * @param date LocalDate object.
     * @return String representation of the given date.
     */
    public static String toDateString(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
    }

    /**
     * Returns the string representation of a LocalDateTime object.
     *
     * @param dateTime LocalDateTime object.
     * @return String representation of the given date and time.
     */
    public static String toDateTimeString(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a"));
    }

    /**
     * Adds a space in front of each line in the text.
     *
     * @param text Given text.
     * @return Same text but with a space in front of each line.
     */
    public static String addSpaceInFrontOfEachLine(String text) {
        // Split the text into lines
        String[] lines = text.split("\n");

        // Initialise a StringBuilder
        StringBuilder resultText = new StringBuilder();

        // Add a space in front each line
        for (String line : lines) {
            resultText.append(" ").append(line).append("\n");
        }

        // Convert the StringBuilder back to a String
        return resultText.toString();
    }
}
