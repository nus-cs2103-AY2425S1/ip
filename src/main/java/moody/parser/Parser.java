package moody.parser;

import moody.exceptions.InvalidCommandException;
import moody.exceptions.TaskInputException;
import moody.exceptions.TaskOutOfBoundsException;
import moody.commands.*;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    public static Command parse(String commandString) throws InvalidCommandException, TaskInputException, TaskOutOfBoundsException {
        String[] parts = commandString.split(" ", 2); // Split into command and arguments

        String command = parts[0].toLowerCase(); // Get the command keyword
        String arguments = parts.length > 1 ? parts[1] : ""; // Get the arguments

        switch (command) {
        case "bye":
            return new ExitCommand();

        case "list":
            return new ListCommand(); // No arguments needed

        case "mark":
            return parseMarkCommand(arguments);

        case "unmark":
            return parseUnmarkCommand(arguments);

        case "todo":
            return parseTodoCommand(arguments);

        case "deadline":
            return parseDeadlineCommand(arguments);

        case "event":
            return parseEventCommand(arguments);

        case "delete":
            return parseDeleteCommand(arguments);

        default:
            throw new InvalidCommandException("""
            Error: Command not found
            
            Please input a valid command
            """);
        }
    }

    private static Command parseMarkCommand(String arguments) throws TaskInputException, TaskOutOfBoundsException {
        try {
            int taskIndex = Integer.parseInt(arguments.trim()) - 1;
            return new MarkCommand(taskIndex);
        } catch (NumberFormatException e) {
            throw new TaskInputException("Error: Invalid task number for mark command.\n");
        }
    }

    private static Command parseUnmarkCommand(String arguments) throws TaskInputException, TaskOutOfBoundsException {
        try {
            int taskIndex = Integer.parseInt(arguments.trim()) - 1;
            return new UnmarkCommand(taskIndex);
        } catch (NumberFormatException e) {
            throw new TaskInputException("Error: Invalid task number for unmark command.\n");
        }
    }

    private static Command parseTodoCommand(String arguments) throws TaskInputException {
        if (arguments.trim().isEmpty()) {
            throw new TaskInputException("Error: The description of a todo cannot be empty.\n");
        }
        return new AddTodoCommand(arguments.trim());
    }

    private static Command parseDeadlineCommand(String arguments) throws TaskInputException {
        String[] parts = arguments.split(" /by ", 2);
        if (parts.length != 2) {
            throw new TaskInputException("""
                    Error: The description and date of a deadline cannot be empty.
                    
                    Please use the following format: deadline <description> /by <date>
                    """);
        }

        String description = parts[0].trim();
        String dateTimeString = parts[1].trim();

        try {
            return new AddDeadlineCommand(description, dateTimeString);
        } catch (DateTimeParseException e) {
            throw new TaskInputException("Error: Invalid date format. Please use yyyy-MM-dd HHmm.\n");
        }
    }

    private static Command parseEventCommand(String arguments) throws TaskInputException {
        String[] parts = arguments.split(" /from | /to ", 3);
        if (parts.length != 3) {
            throw new TaskInputException("""
                    Error: The description and dates of an event cannot be empty.
                    
                    Please use the following format: event <name> /from <date> <time> /to <date> <time>
                    """);
        }

        String description = parts[0].trim();
        String fromString = parts[1].trim();
        String toString = parts[2].trim();

        return new AddEventCommand(description, fromString, toString);

    }

    private static Command parseDeleteCommand(String arguments) throws TaskInputException {
        try {
            int taskIndex = Integer.parseInt(arguments.trim()) - 1;
            return new DeleteCommand(taskIndex);
        } catch (NumberFormatException e) {
            throw new TaskInputException("Error: Invalid task number for delete command.\n");
        }
    }
}
