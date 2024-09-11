package friday.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import friday.command.AddCommand;
import friday.command.Command;
import friday.command.DeleteCommand;
import friday.command.ExitCommand;
import friday.command.FindCommand;
import friday.command.InvalidCommand;
import friday.command.ListCommand;
import friday.command.MarkCommand;
import friday.command.OnCommand;
import friday.command.UnmarkCommand;
import friday.task.Deadline;
import friday.task.Event;
import friday.task.Todo;

/**
 * The Parser class handles parsing user input and converting it into commands that
 * the application can execute. It supports various command types such as adding tasks,
 * marking tasks, and listing tasks.
 */
public class Parser {

    /**
     * Parses the user input and returns the corresponding Command object.
     *
     * @param fullCommand The full command string entered by the user.
     * @return A Command object corresponding to the parsed user input.
     */
    public static Command parse(String fullCommand) {
        assert fullCommand != null : "Full command should not be null";
        String[] parts = fullCommand.split(" ", 2);

        String command = parts[0];
        String arguments = parts.length > 1 ? parts[1] : "";

        switch (command) {
        case "todo":
            return parseTodo(arguments);
        case "deadline":
            return parseDeadline(arguments);
        case "event":
            return parseEvent(arguments);
        case "list":
            return new ListCommand();
        case "mark":
            return parseMark(arguments);
        case "unmark":
            return parseUnmark(arguments);
        case "delete":
            return parseDelete(arguments);
        case "on":
            return parseOn(arguments);
        case "find":
            return parseFind(arguments);
        case "bye":
            return new ExitCommand();
        default:
            return new InvalidCommand("I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Parses the arguments for a todo command and returns an AddCommand object.
     *
     * @param arguments The arguments string containing the task description.
     * @return An AddCommand object for the todo task.
     */
    private static Command parseTodo(String arguments) {
        assert arguments != null : "Arguments should not be null";
        if (arguments.isEmpty()) {
            return new InvalidCommand("OOPS!!! The description of a todo cannot be empty.");
        }
        return new AddCommand(new Todo(arguments));
    }

    /**
     * Parses the arguments for a deadline command and returns an AddCommand object.
     *
     * @param arguments The arguments string containing the task description and deadline.
     * @return An AddCommand object for the deadline task.
     */
    private static Command parseDeadline(String arguments) {
        assert arguments != null : "Arguments should not be null";
        String[] splitArguments = arguments.split(" /by ");
        if (splitArguments.length < 2) {
            return new InvalidCommand("OOPS!!! The description or deadline cannot be empty.");
        }
        String description = splitArguments[0];
        String by = splitArguments[1];
        try {
            return new AddCommand(new Deadline(description, by));
        } catch (DateTimeParseException e) {
            return new InvalidCommand("OOPS!!! The date format for the deadline is incorrect. "
                    + "Please use yyyy-MM-dd HHmm.");
        }
    }

    /**
     * Parses the arguments for an event command and returns an AddCommand object.
     *
     * @param arguments The arguments string containing the task description and event date/time.
     * @return An AddCommand object for the event task.
     */
    private static Command parseEvent(String arguments) {
        assert arguments != null : "Arguments should not be null";
        String[] splitArguments = arguments.split(" /from ");
        if (splitArguments.length < 2) {
            return new InvalidCommand("OOPS!!! The description or date/time cannot be empty.");
        }
        String description = splitArguments[0];
        String[] dateTimeParts = splitArguments[1].split(" /to ");
        if (dateTimeParts.length < 2) {
            return new InvalidCommand("OOPS!!! The end date/time for the event cannot be empty.");
        }
        try {
            String from = dateTimeParts[0];
            String to = dateTimeParts[1];
            return new AddCommand(new Event(description, from, to));
        } catch (DateTimeParseException e) {
            return new InvalidCommand("OOPS!!! The date/time format for the event is incorrect. "
                    + "Please use yyyy-MM-dd HHmm.");
        }
    }

    /**
     * Parses the arguments for a mark command and returns a MarkCommand object.
     *
     * @param arguments The arguments string containing the task index.
     * @return A MarkCommand object for marking the task as done.
     */
    private static Command parseMark(String arguments) {
        assert arguments != null : "Arguments should not be null";
        try {
            int index = Integer.parseInt(arguments.trim()) - 1;
            return new MarkCommand(index);
        } catch (NumberFormatException e) {
            return new InvalidCommand("OOPS!!! The index for marking a task is invalid.");
        }
    }

    /**
     * Parses the arguments for an unmark command and returns an UnmarkCommand object.
     *
     * @param arguments The arguments string containing the task index.
     * @return An UnmarkCommand object for unmarking the task as done.
     */
    private static Command parseUnmark(String arguments) {
        assert arguments != null : "Arguments should not be null";
        try {
            int index = Integer.parseInt(arguments.trim()) - 1;
            return new UnmarkCommand(index);
        } catch (NumberFormatException e) {
            return new InvalidCommand("OOPS!!! The index for unmarking a task is invalid.");
        }
    }

    /**
     * Parses the arguments for a delete command and returns a DeleteCommand object.
     *
     * @param arguments The arguments string containing the task index.
     * @return A DeleteCommand object for deleting the task.
     */
    private static Command parseDelete(String arguments) {
        assert arguments != null : "Arguments should not be null";
        try {
            int index = Integer.parseInt(arguments.trim()) - 1;
            return new DeleteCommand(index);
        } catch (NumberFormatException e) {
            return new InvalidCommand("OOPS!!! The index for deleting a task is invalid.");
        }
    }

    /**
     * Parses the arguments for an on command and returns an OnCommand object.
     *
     * @param arguments The arguments string containing the date.
     * @return An OnCommand object for filtering tasks by the given date.
     */
    private static Command parseOn(String arguments) {
        assert arguments != null : "Arguments should not be null";
        try {
            LocalDate date = LocalDate.parse(arguments.trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            return new OnCommand(date);
        } catch (DateTimeParseException e) {
            return new InvalidCommand("OOPS!!! The date format is incorrect. Please use yyyy-MM-dd.");
        }
    }

    /**
     * Parses the arguments for a find command and returns a FindCommand object.
     *
     * @param arguments The arguments string containing the keyword to search for.
     * @return A FindCommand object for finding tasks that match the keyword.
     */
    private static Command parseFind(String arguments) {
        assert arguments != null : "Arguments should not be null";
        if (arguments.isEmpty()) {
            return new InvalidCommand("OOPS!!! The keyword for finding tasks cannot be empty.");
        }
        return new FindCommand(arguments);
    }
}
