package streams.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import streams.command.AddCommand;
import streams.command.AddTagCommand;
import streams.command.Command;
import streams.command.DeleteCommand;
import streams.command.ExitCommand;
import streams.command.FindCommand;
import streams.command.HelpCommand;
import streams.command.ListCommand;
import streams.command.ListDateCommand;
import streams.command.ListTagCommand;
import streams.command.ListWeekCommand;
import streams.command.MarkCommand;
import streams.command.RemoveTagCommand;
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
        try {
            assert fullCommand != null : "Full command cannot be null";
            assert !fullCommand.trim().isEmpty() : "Full command cannot be empty";
            String[] parts = fullCommand.split(" ", 2);
            String commandType = parts[0].toLowerCase();
            String rest = parts.length > 1 ? parts[1].trim() : "";
            switch (commandType) {
            case "/help":
                return new HelpCommand();
            case "list":
                assert rest.isEmpty() : "incorrect list command format";
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
            case "find":
                return new FindCommand(rest);
            case "tag":
                return new AddTagCommand(rest);
            case "list-tag":
                return new ListTagCommand(rest);
            case "tag-remove":
                return new RemoveTagCommand(rest);
            default:
                throw new StreamsException("incorrect command: " + commandType);
            }
        } catch (AssertionError e) {
            throw new StreamsException(e.getMessage());
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
        assert rest != null : "Deadline details cannot be null";
        String[] parts = rest.split(" /by ");
        assert parts.length == 2 : "Deadline command should have two parts separated by '/by'";
        String description = parts[0].trim();
        String byString = parts[1].trim();
        try {
            LocalDateTime by = LocalDateTime.parse(byString, INPUT_FORMATTER);
            return new AddCommand(new DeadlineTask(description, by));
        } catch (DateTimeParseException e) {
            throw new StreamsException("Invalid date format. Please use yyyy-MM-dd HH:mm");
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
        assert rest != null : "Event details cannot be null";
        String[] parts = rest.split(" /from ");
        assert parts.length == 2 : "Event command should have two parts separated by '/from'";
        String description = parts[0].trim();
        String[] timeParts = parts[1].split(" /to ");
        assert timeParts.length == 2 : "Event time should have two parts separated by '/to'";
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

