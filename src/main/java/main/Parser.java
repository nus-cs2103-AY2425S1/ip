package main;

import command.Command;
import command.ByeCommand;
import command.DeadlineCommand;
import command.DeleteCommand;
import command.EventCommand;
import command.FindCommand;
import command.ListCommand;
import command.MarkCommand;
import command.TodoCommand;
import command.UnknownCommand;
import command.UnmarkCommand;

import exception.DashException;
import exception.EmptyDescriptionException;
import exception.IncorrectCommandUseException;
import exception.UnknownCommandException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Parses user input and returns the corresponding Command object.
 * Handles command parsing and validation, and manages exceptions.
 */
public class Parser {
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("d-M-yyyy HHmm");

    /**
     * Parses the given input string and returns the corresponding Command object.
     *
     * @param input The user input string to be parsed.
     * @return The Command object corresponding to the parsed input.
     * @throws DashException If the input command is malformed or invalid.
     */
    public static Command parse(String input) throws DashException {
        try {
            if (input.equals("bye")) {
                return new ByeCommand();
            } else if (input.equals("list")) {
                return new ListCommand();
            } else if (input.startsWith("mark")) {
                return new MarkCommand(input);
            } else if (input.startsWith("unmark")) {
                return new UnmarkCommand(input);
            } else if (input.startsWith("todo")) {
                return parseTodoCommand(input);
            } else if (input.startsWith("deadline")) {
                return parseDeadlineCommand(input);
            } else if (input.startsWith("event")) {
                return parseEventCommand(input);
            } else if (input.startsWith("delete")) {
                return new DeleteCommand(input);
            } else if (input.startsWith("find")) {
                return new FindCommand(input);
            } else {
                return new UnknownCommand();
            }
        } catch (DashException e) {
            Ui.showError(e.getMessage());
        } catch (DateTimeParseException e) {
            Ui.showDateTimeParseError(e.getMessage());
        } catch (Exception e) {
            Ui.showUnexpectedError(e.getMessage());
        }
        return new UnknownCommand();
    }

    /**
     * Parses a todo command from the input string.
     *
     * @param input The user input string for the todo command.
     * @return The TodoCommand object corresponding to the parsed input.
     * @throws DashException If the input is missing the description.
     */
    private static Command parseTodoCommand(String input) throws DashException {
        String[] parts = input.split(" ", 2);
        if (parts.length == 1) {
            throw new EmptyDescriptionException("Description of todo command cannot be empty.");
        }
        String description = parts[1];
        return new TodoCommand(description);
    }

    /**
     * Parses a deadline command from the input string.
     *
     * @param input The user input string for the deadline command.
     * @return The DeadlineCommand object corresponding to the parsed input.
     * @throws DashException If the input is missing parts or has an incorrect format.
     */
    private static Command parseDeadlineCommand(String input) throws DashException {
        String[] parts = input.split("/", 2);
        if (parts.length == 1) {
            throw new EmptyDescriptionException("Description of deadline command cannot be empty. Do remember to include '/'.");
        }
        String[] byParts = parts[1].split(" ", 2);
        if (!byParts[0].equals("by")) {
            throw new IncorrectCommandUseException("Please include the /by command.");
        }
        if (byParts.length == 1) {
            throw new IncorrectCommandUseException("Please include deadline!");
        }
        LocalDateTime deadline = LocalDateTime.parse(byParts[1], INPUT_FORMATTER);
        String[] descriptionParts = parts[0].split(" ", 2);
        String description = descriptionParts[1];
        return new DeadlineCommand(description, deadline);
    }

    /**
     * Parses an event command from the input string.
     *
     * @param input The user input string for the event command.
     * @return The EventCommand object corresponding to the parsed input.
     * @throws DashException If the input is missing parts or has an incorrect format.
     */
    private static Command parseEventCommand(String input) throws DashException {
        String[] parts = input.split("/", 3);
        if (parts.length == 1) {
            throw new EmptyDescriptionException("Description of event command cannot be empty. Do remember to include '/'.");
        } else if (parts.length == 2) {
            throw new IncorrectCommandUseException("Incorrect use of event command!");
        }
        String[] fromParts = parts[1].split(" ", 2);
        if (fromParts.length == 1) {
            throw new IncorrectCommandUseException("Incorrect use of event command!");
        }
        if (!fromParts[0].equals("from")) {
            throw new IncorrectCommandUseException("Please include the /from command.");
        }
        String[] toParts = parts[2].split(" ", 2);
        if (toParts.length == 1) {
            throw new IncorrectCommandUseException("Incorrect use of event command!");
        }
        if (!toParts[0].equals("to")) {
            throw new IncorrectCommandUseException("Please include the /to command.");
        }
        LocalDateTime from = LocalDateTime.parse(fromParts[1], INPUT_FORMATTER);
        LocalDateTime to = LocalDateTime.parse(toParts[1], INPUT_FORMATTER);
        String[] descriptionParts = parts[0].split(" ", 2);
        String description = descriptionParts[1];
        return new EventCommand(description, from, to);
    }
}
