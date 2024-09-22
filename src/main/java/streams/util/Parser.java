package streams.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import streams.command.AddCommand;
import streams.command.Command;
import streams.command.DeleteCommand;
import streams.command.ExitCommand;
import streams.command.ListCommand;
import streams.command.ListDateCommand;
import streams.command.ListWeekCommand;
import streams.command.MarkCommand;
import streams.command.SortDeadlineCommand;
import streams.exception.StreamsException;
import streams.task.DeadlineTask;
import streams.task.EventTask;
import streams.task.ToDoTask;

/**
 * Parses user input into commands.
 */
public class Parser {
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    /**
     * Parses the user input into a Command object.
     *
     * @param fullCommand The user's input string.
     * @return A Command object based on the user's input.
     * @throws StreamsException If the input is invalid or cannot be parsed.
     */
    public static Command parse(String fullCommand) throws StreamsException {
        String[] parts = fullCommand.split(" ", 2);
        String commandType = parts[0].toLowerCase();
        String rest = parts.length > 1 ? parts[1].trim() : "";

        switch (commandType) {
            case "list":
                return new ListCommand();
            case "done":
            case "undone":
                return new MarkCommand(rest, commandType.equals("done"));
            case "todo":
                return new AddCommand(new ToDoTask(rest));
            case "deadline":
                return parseDeadline(rest);
            case "event":
                return parseEvent(rest);
            case "delete":
                return new DeleteCommand(rest);
            case "list-date":
                return new ListDateCommand(rest);
            case "list-week":
                return new ListWeekCommand();
            case "sort-deadline":
                return new SortDeadlineCommand();
            case "bye":
                return new ExitCommand();
            default:
                throw new StreamsException("incorrect command: " + commandType);
        }
    }

    /**
     * Parses a deadline task from the user input.
     *
     * @param rest The part of the input string after the command word.
     * @return An AddCommand for a DeadlineTask.
     * @throws StreamsException If the input format is invalid.
     */
    private static Command parseDeadline(String rest) throws StreamsException {
        String[] parts = rest.split(" /by ");
        if (parts.length != 2) {
            throw new StreamsException("the format for deadlines is 'deadline [description] /by yyyy-MM-dd HH:mm'");
        }
        String description = parts[0].trim();
        String byString = parts[1].trim();
        try {
            LocalDateTime by = LocalDateTime.parse(byString, INPUT_FORMATTER);
            return new AddCommand(new DeadlineTask(description, by));
        } catch (DateTimeParseException e) {
            throw new StreamsException("invalid date format. Please use yyyy-MM-dd HH:mm");
        }
    }

    /**
     * Parses an event task from the user input.
     *
     * @param rest The part of the input string after the command word.
     * @return An AddCommand for an EventTask.
     * @throws StreamsException If the input format is invalid.
     */
    private static Command parseEvent(String rest) throws StreamsException {
        String[] parts = rest.split(" /from ");
        if (parts.length != 2) {
            throw new StreamsException("the format for events is 'event [description] "
                    + "/from yyyy-MM-dd HH:mm /to yyyy-MM-dd HH:mm'");
        }
        String description = parts[0].trim();
        String[] timeParts = parts[1].split(" /to ");
        if (timeParts.length != 2) {
            throw new StreamsException("the format for events is 'event [description] "
                    + "/from yyyy-MM-dd HH:mm /to yyyy-MM-dd HH:mm'");
        }
        String fromString = timeParts[0].trim();
        String toString = timeParts[1].trim();
        try {
            LocalDateTime from = LocalDateTime.parse(fromString, INPUT_FORMATTER);
            LocalDateTime to = LocalDateTime.parse(toString, INPUT_FORMATTER);
            return new AddCommand(new EventTask(description, from, to));
        } catch (DateTimeParseException e) {
            throw new StreamsException("invalid date format. Please use yyyy-MM-dd HH:mm");
        }
    }
}

