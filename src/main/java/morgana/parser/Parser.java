package morgana.parser;

import static morgana.common.Messages.MESSAGE_INVALID_TASK_NUMBER;
import static morgana.common.Messages.MESSAGE_MISSING_TASK_NUMBER;
import static morgana.util.DateTimeUtil.COMPACT_FORMATTER;

import java.time.LocalDateTime;

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
import morgana.task.Todo;

/**
 * The {@code Parser} class is responsible for interpreting user input and
 * creating the corresponding {@link Command} objects. It also provides utility
 * methods for parsing task indices and task strings into {@link Task} objects.
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
            case ListCommand.COMMAND_WORD -> new ListCommand();
            case FindCommand.COMMAND_WORD -> new FindCommand(args);
            case MarkCommand.COMMAND_WORD -> new MarkCommand(parseTaskIndex(args));
            case UnmarkCommand.COMMAND_WORD -> new UnmarkCommand(parseTaskIndex(args));
            case DeleteCommand.COMMAND_WORD -> new DeleteCommand(parseTaskIndex(args));
            case TodoCommand.COMMAND_WORD -> new TodoCommand(args);
            case DeadlineCommand.COMMAND_WORD -> new DeadlineCommand(args);
            case EventCommand.COMMAND_WORD -> new EventCommand(args);
            case ByeCommand.COMMAND_WORD -> new ByeCommand();
            default -> throw new MorganaException("Unknown command: " + cmd);
        };
    }

    /**
     * Parses the task index from the user's input.
     *
     * @param args The input string containing the task index.
     * @return The zero-based index of the task.
     * @throws MorganaException If the input is empty or the index is invalid.
     */
    public static int parseTaskIndex(String args) throws MorganaException {
        if (args.isEmpty()) {
            throw new MorganaException(MESSAGE_MISSING_TASK_NUMBER);
        }
        try {
            return Integer.parseInt(args) - 1;
        } catch (NumberFormatException e) {
            throw new MorganaException(MESSAGE_INVALID_TASK_NUMBER);
        }
    }

    /**
     * Parses a task string from a file and converts it into a {@link Task} object.
     *
     * @param line The input string representing a task, as stored in a file.
     * @return The {@code Task} object created from the input string.
     */
    public static Task parseTask(String line) throws MorganaException {
        String[] fields = line.split(" \\| ");
        assert fields.length >= 3 : "Expected at least 3 fields for a task, got: " + fields.length;

        String type = fields[0];
        boolean isDone = fields[1].equals("X");
        String description = fields[2];

        Task task = switch (type) {
            case "T" -> new Todo(description);
            case "D" -> {
                assert fields.length == 4 : "Expected 4 fields for a deadline, got: " + fields.length;
                yield new Deadline(description, LocalDateTime.parse(fields[3], COMPACT_FORMATTER));
            }
            case "E" -> {
                assert fields.length == 5 : "Expected 5 fields for an event, got: " + fields.length;
                LocalDateTime start = LocalDateTime.parse(fields[3], COMPACT_FORMATTER);
                LocalDateTime end = LocalDateTime.parse(fields[4], COMPACT_FORMATTER);
                yield new Event(description, start, end);
            }
            default -> throw new MorganaException("Invalid task type: " + type);
        };

        task.markAsDone(isDone);
        return task;
    }
}
