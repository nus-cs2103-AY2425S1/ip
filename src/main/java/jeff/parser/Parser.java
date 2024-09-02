package jeff.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import jeff.command.AddCommand;
import jeff.command.Command;
import jeff.command.DateCommand;
import jeff.command.DeleteCommand;
import jeff.command.ExitCommand;
import jeff.command.FindCommand;
import jeff.command.ListCommand;
import jeff.command.MarkCommand;
import jeff.command.UnmarkCommand;
import jeff.exception.JeffException;
import jeff.task.Task;

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
            return LocalDateTime.parse(input, DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm a"));
        } catch (DateTimeParseException e) {
            return LocalDateTime.parse(input, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        }
    }

    /**
     * Returns the string representation of the given list of tasks.
     *
     * @param list Given list of tasks.
     * @return String representation of the given list.
     */
    public static String listToString(List<Task> list) {
        // Initialise a StringBuilder
        StringBuilder listString = new StringBuilder();

        // Loop through the inputList and add it to the StringBuilder
        for (int i = 0; i < list.size(); i++) {
            listString.append(Integer.toString(i + 1)).append(".").append(list.get(i).toString());

            // Only add a new line when it is not the last task in the list
            if (i != list.size() - 1) {
                listString.append("\n");
            }

        }

        return listString.toString();
    }

    /**
     * Adds a space in front of each line in the text.
     *
     * @param text Given text.
     * @return Same text but with a space in front of each line.
     */
    public static String prettyText(String text) {
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
