package utils;

import commands.*;
import exception.DiegoException;
import exception.NoDescriptionException;
import exception.NoIndexException;
import exception.UnknownCommandException;

/**
 * Parses user input commands and returns the appropriate Command object.
 */
public class Parser {

    /**
     * Parses the user input and returns the corresponding Command object.
     *
     * @param input User input string.
     * @return Appropriate Command object based on the user input.
     * @throws DiegoException If the input is invalid or unknown.
     */
    public Command parseCommand(String input) throws DiegoException {
        if (input.equals("list")) {
            return new ListCommand();
        } else if (input.startsWith("mark")) {
            return new MarkCommand(parseIndex(input));
        } else if (input.startsWith("unmark")) {
            return new UnmarkCommand(parseIndex(input));
        } else if (input.startsWith("event")) {
            return parseEventCommand(input);
        } else if (input.startsWith("todo")) {
            return parseTodoCommand(input);
        } else if (input.startsWith("deadline")) {
            return parseDeadlineCommand(input);
        } else if (input.startsWith("delete")) {
            return new DeleteCommand(parseIndex(input));
        } else if (input.startsWith("find")) {
            return new FindCommand(input.substring(5).trim());
        } else if (input.startsWith("remind")) {
            return parseReminderCommand(input);
        } else if (input.equals("help")) {
            return new HelpCommand();
        } else {
            throw new UnknownCommandException();
        }
    }

    private Command parseEventCommand(String input) throws DiegoException {
        String[] data = input.split(" /from | /to ");
        if (data.length < 3) {
            throw new NoDescriptionException();
        }
        return new AddEventCommand(data[0].substring(6).trim(), data[1].trim(), data[2].trim());
    }

    private Command parseTodoCommand(String input) throws DiegoException {
        if (input.length() <= 5) {
            throw new NoDescriptionException();
        }
        return new AddTodoCommand(input.substring(5).trim());
    }

    private Command parseDeadlineCommand(String input) throws DiegoException {
        String[] data = input.split(" /by");
        if (data.length < 2) {
            throw new NoDescriptionException();
        }
        return new AddDeadlineCommand(data[0].substring(9).trim(), data[1].trim());
    }

    private Command parseReminderCommand(String input) throws DiegoException {
        String[] parts = input.split(" ");

        // Default to 7 days if no number is provided
        if (parts.length == 1) {
            return new ReminderCommand(7);
        }

        // Try to parse the days; handle invalid number input
        try {
            int days = Integer.parseInt(parts[1]);
            return new ReminderCommand(days);
        } catch (NumberFormatException e) {
            throw new DiegoException("Invalid number of days for remind command. Please provide a valid number.");
        }
    }


    private int parseDays(String input) throws DiegoException {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new DiegoException("Invalid number of days for remind command.");
        }
    }

    private int parseIndex(String input) throws DiegoException {
        try {
            return Integer.parseInt(input.split(" ")[1]) - 1;
        } catch (Exception e) {
            throw new NoIndexException();
        }
    }
}
