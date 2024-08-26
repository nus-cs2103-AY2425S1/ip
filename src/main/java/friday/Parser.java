package friday;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import friday.command.AddCommand;
import friday.command.Command;
import friday.command.DeleteCommand;
import friday.command.ExitCommand;
import friday.command.InvalidCommand;
import friday.command.ListCommand;
import friday.command.MarkCommand;
import friday.command.OnCommand;
import friday.command.UnmarkCommand;
import friday.task.Deadline;
import friday.task.Event;
import friday.task.Todo;

public class Parser {
    public static Command parse(String fullCommand) {
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
            case "bye":
                return new ExitCommand();
            default:
                return new InvalidCommand("I'm sorry, but I don't know what that means :-(");
        }
    }

    private static Command parseTodo(String arguments) {
        if (arguments.isEmpty()) {
            return new InvalidCommand("OOPS!!! The description of a todo cannot be empty.");
        }
        return new AddCommand(new Todo(arguments));
    }

    private static Command parseDeadline(String arguments) {
        String[] splitArguments = arguments.split(" /by ");
        if (splitArguments.length < 2) {
            return new InvalidCommand("OOPS!!! The description or deadline cannot be empty.");
        }
        String description = splitArguments[0];
        String by = splitArguments[1];
        try {
            return new AddCommand(new Deadline(description, by));
        } catch (DateTimeParseException e) {
            return new InvalidCommand("OOPS!!! The date format for the deadline is incorrect.");
        }
    }

    private static Command parseEvent(String arguments) {
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
            return new InvalidCommand("OOPS!!! The date/time format for the event is incorrect.");
        }
    }

    private static Command parseMark(String arguments) {
        try {
            int index = Integer.parseInt(arguments.trim()) - 1;
            return new MarkCommand(index);
        } catch (NumberFormatException e) {
            return new InvalidCommand("OOPS!!! The index for marking a task is invalid.");
        }
    }

    private static Command parseUnmark(String arguments) {
        try {
            int index = Integer.parseInt(arguments.trim()) - 1;
            return new UnmarkCommand(index);
        } catch (NumberFormatException e) {
            return new InvalidCommand("OOPS!!! The index for unmarking a task is invalid.");
        }
    }

    private static Command parseDelete(String arguments) {
        try {
            int index = Integer.parseInt(arguments.trim()) - 1;
            return new DeleteCommand(index);
        } catch (NumberFormatException e) {
            return new InvalidCommand("OOPS!!! The index for deleting a task is invalid.");
        }
    }

    private static Command parseOn(String arguments) {
        try {
            LocalDate date = LocalDate.parse(arguments.trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            return new OnCommand(date);
        } catch (DateTimeParseException e) {
            return new InvalidCommand("OOPS!!! The date format is incorrect. Please use yyyy-MM-dd.");
        }
    }
}
