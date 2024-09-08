package morgana.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import morgana.commands.ByeCommand;
import morgana.commands.Command;
import morgana.commands.DeadlineCommand;
import morgana.commands.DeleteCommand;
import morgana.commands.EventCommand;
import morgana.commands.FindCommand;
import morgana.commands.ListCommand;
import morgana.commands.MarkCommand;
import morgana.commands.TodoCommand;
import morgana.commands.UnmarkCommand;
import morgana.exceptions.MorganaException;
import morgana.task.Deadline;
import morgana.task.Event;
import morgana.task.Task;
import morgana.task.TaskList;
import morgana.task.Todo;

/**
 * The {@code Parser} class is responsible for interpreting user input and
 * creating the corresponding {@link Command} objects. It also provides utility
 * methods for parsing task indices and date/time strings.
 */
public class Parser {
    /**
     * Parses the user's input and returns the corresponding {@link Command}.
     *
     * @param input The input string entered by the user.
     * @return The {@code Command} corresponding to the user's input.
     * @throws MorganaException If the command is not recognized.
     */
    public static Command parse(String input) throws MorganaException {
        String[] tokens = input.trim().split(" ", 2);
        String cmd = tokens[0];
        String args = tokens.length > 1 ? tokens[1].trim() : "";

        return switch (cmd) {
            case "list" -> new ListCommand();
            case "find" -> new FindCommand(args);
            case "mark" -> new MarkCommand(args);
            case "unmark" -> new UnmarkCommand(args);
            case "delete" -> new DeleteCommand(args);
            case "todo" -> new TodoCommand(args);
            case "deadline" -> new DeadlineCommand(args);
            case "event" -> new EventCommand(args);
            case "bye" -> new ByeCommand();
            default -> throw new MorganaException("Unknown command: " + cmd);
        };
    }

    /**
     * Parses the task index from the user's input.
     *
     * @param args The input string containing the task index.
     * @param tasks The {@code TaskList} containing the tasks.
     * @return The zero-based index of the task.
     * @throws MorganaException If the input is empty or the index is invalid.
     */
    public static int parseTaskIndex(String args, TaskList tasks) throws MorganaException {
        if (args.isEmpty()) {
            throw new MorganaException("Please enter a task number.");
        }
        try {
            int index = Integer.parseInt(args) - 1;
            if (index < 0 || index >= tasks.size()) {
                throw new IndexOutOfBoundsException();
            }
            return index;
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new MorganaException("Please enter a valid task number.");
        }
    }

    /**
     * Parses a date/time string into a {@link LocalDateTime} object.
     *
     * @param dateTime The input string containing the date and time in the format 'yyyy-MM-dd HHmm'.
     * @return The parsed {@code LocalDateTime} object.
     * @throws MorganaException If the input string is in an invalid format.
     */
    public static LocalDateTime parseDateTime(String dateTime) throws MorganaException {
        try {
            return LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        } catch (DateTimeParseException e) {
            throw new MorganaException("Invalid date/time format. Please use 'yyyy-MM-dd HHmm'.");
        }
    }

    /**
     * Parses a string representation of a task from a file and converts it into a {@link Task} object.
     *
     * @param line The input string representing a task, as stored in a file.
     * @return The {@code Task} object created from the input string.
     */
    public static Task parseTaskFromString(String line) throws MorganaException {
        String[] fields = line.split(" \\| ");
        assert fields.length >= 3 : "Expected at least 3 fields for a task, got: " + fields.length;

        String type = fields[0];
        boolean isDone = fields[1].equals("X");
        String description = fields[2];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

        Task task = switch (type) {
            case "T" -> new Todo(description);
            case "D" -> {
                assert fields.length == 4 : "Expected 4 fields for a Deadline task, got: " + fields.length;
                yield new Deadline(description, LocalDateTime.parse(fields[3], formatter));
            }
            case "E" -> {
                assert fields.length == 5 : "Expected 5 fields for an Event task, got: " + fields.length;
                LocalDateTime start = LocalDateTime.parse(fields[3], formatter);
                LocalDateTime end = LocalDateTime.parse(fields[4], formatter);
                yield new Event(description, start, end);
            }
            default -> throw new MorganaException("Invalid task type: " + type);
        };

        task.markAsDone(isDone);
        return task;
    }
}
