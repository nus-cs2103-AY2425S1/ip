package bottle;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import bottle.command.AddDeadlineTask;
import bottle.command.AddEventTask;
import bottle.command.AddTodoTask;
import bottle.command.CheckClashCommand;
import bottle.command.Command;
import bottle.command.DeleteCommand;
import bottle.command.ExitCommand;
import bottle.command.FindCommand;
import bottle.command.InvalidCommand;
import bottle.command.ListCommand;
import bottle.command.MarkCommand;
import bottle.command.UnMarkCommand;
import bottle.task.Deadline;
import bottle.task.Event;
import bottle.task.Task;
import bottle.task.Todo;

/**
 * The Parser class interprets user input commands and converts them into Command objects.
 * It supports parsing for different task types such as ToDo, Deadline, and Event.
 */
public class Parser {
    private static final String TODO_TASK = "T ";
    private static final String DEADLINE_TASK = "D ";
    private static final String EVENT_TASK = "E ";
    /**
     * Parses a date-time string into a LocalDateTime object.
     *
     * @param dateTimeStr the date-time string to parse
     * @return the corresponding LocalDateTime object
     * @throws IllegalArgumentException if the date-time format is incorrect
     */
    private static LocalDateTime parseDateTime(String dateTimeStr) {
        assert dateTimeStr != null : "dateTimeStr shouldn't be null";
        dateTimeStr = dateTimeStr.trim();
        DateTimeFormatter[] formatters = new DateTimeFormatter[]{
                DateTimeFormatter.ofPattern("d/M/yyyy HHmm"),
                DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"),
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"),
                DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm a"),
                DateTimeFormatter.ofPattern("d/M/yyyy"), // New format for date only
                DateTimeFormatter.ofPattern("dd/MM/yyyy"), // New format for date only
        };

        // Check for just date, month, and year
        if (dateTimeStr.matches("\\d{1,2}/\\d{1,2}/\\d{4}")) {
            dateTimeStr += " 2359"; // Append time if only date is provided
        }

        for (DateTimeFormatter formatter : formatters) {
            try {
                return LocalDateTime.parse(dateTimeStr.trim(), formatter);
            } catch (DateTimeParseException ignored) {
                // Continue trying other formats
            }
        }

        throw new IllegalArgumentException("OOPS!!! The date format is incorrect. "
                + "Please use one of these formats: d/M/yyyy HHmm, dd/MM/yyyy HHmm, d/M/yyyy, dd/MM/yyyy, etc.");
    }

    /**
     * Parses the user input command and returns the corresponding Command object.
     *
     * @param input the user input command
     * @return the Command object corresponding to the input
     */
    public Command parseCommand(String input) {
        assert input != null : "input shouldn't be null";
        input = input.trim();
        try {
            if (input.equalsIgnoreCase("bye")) {
                return new ExitCommand();
            } else if (input.equalsIgnoreCase("list")) {
                return new ListCommand();
            } else if (input.startsWith("mark ")) {
                return parseMarkCommand(input);
            } else if (input.startsWith("unmark ")) {
                return parseUnmarkCommand(input);
            } else if (input.startsWith("todo ")) {
                return parseTodoCommand(input);
            } else if (input.startsWith("deadline ")) {
                return parseDeadlineCommand(input);
            } else if (input.startsWith("event ")) {
                return parseEventCommand(input);
            } else if (input.startsWith("delete ")) {
                return parseDeleteCommand(input);
            } else if (input.startsWith("find ")) {
                return parseFindCommand(input);
            } else if (input.startsWith("check clash")) {
                return parseCheckClashCommand();
            }
            else {
                throw new RuntimeException("OOPS!!! Something went wrong.");
            }
        } catch (RuntimeException e) {
            System.out.println("Error parsing input: " + e.getMessage());
            return new InvalidCommand();
        }
    }
    private Command parseCheckClashCommand() {
        return new CheckClashCommand();
    }
    /**
     * Parses the mark command and returns the corresponding MarkCommand object.
     *
     * @param input the user input command
     * @return the MarkCommand object
     */
    private Command parseMarkCommand(String input) {
        String taskIndexStr = input.substring(5);
        assert !taskIndexStr.isEmpty() : "taskIndexStr shouldn't be empty";
        int taskIndex = Integer.parseInt(taskIndexStr) - 1;
        return new MarkCommand(taskIndex);
    }

    /**
     * Parses the unmark command and returns the corresponding UnMarkCommand object.
     *
     * @param input the user input command
     * @return the UnMarkCommand object
     */
    private Command parseUnmarkCommand(String input) {
        String taskIndexStr = input.substring(7);
        assert !taskIndexStr.isEmpty() : "taskIndexStr shouldn't be empty";
        int taskIndex = Integer.parseInt(taskIndexStr) - 1;
        return new UnMarkCommand(taskIndex);
    }

    /**
     * Parses the todo command and returns the corresponding AddTodoTask object.
     *
     * @param input the user input command
     * @return the AddTodoTask object
     */
    private Command parseTodoCommand(String input) {
        assert input.length() > 5 : "Todo task description shouldn't be empty";
        return new AddTodoTask(input.substring(5));
    }

    /**
     * Parses the deadline command and returns the corresponding AddDeadlineTask object.
     *
     * @param input the user input command
     * @return the AddDeadlineTask object
     * @throws IllegalArgumentException if the format is incorrect
     */
    private Command parseDeadlineCommand(String input) {
        String[] parts = input.split(" /by ");
        if (parts.length != 2) {
            throw new IllegalArgumentException("OOPS!!! The deadline format is incorrect. "
                    + "Use: deadline <description> /by <dd/MM/yyyy HHmm>");
        }
        String description = parts[0].substring(9);
        assert !description.isEmpty() : "description shouldn't be empty";
        LocalDateTime by = parseDateTime(parts[1]);
        return new AddDeadlineTask(description, by);
    }

    /**
     * Parses the event command and returns the corresponding AddEventTask object.
     *
     * @param input the user input command
     * @return the AddEventTask object
     * @throws IllegalArgumentException if the format is incorrect
     */
    private Command parseEventCommand(String input) {
        String[] parts = input.split(" /from | /to ");
        if (parts.length != 3) {
            throw new IllegalArgumentException("OOPS!!! The event format is incorrect. "
                    + "Use: event <description> /from <dd/MM/yyyy HHmm> /to <dd/MM/yyyy HHmm>");
        }
        String description = parts[0].substring(6);
        assert !description.isEmpty() : "description shouldn't be empty";
        LocalDateTime from = parseDateTime(parts[1]);
        LocalDateTime to = parseDateTime(parts[2]);
        return new AddEventTask(description, from, to);
    }

    /**
     * Parses the delete command and returns the corresponding DeleteCommand object.
     *
     * @param input the user input command
     * @return the DeleteCommand object
     */
    private Command parseDeleteCommand(String input) {
        String indexStr = input.substring(7);
        assert !indexStr.isEmpty() : "index shouldn't be empty";
        int taskIndex = Integer.parseInt(indexStr) - 1;
        return new DeleteCommand(taskIndex);
    }

    /**
     * Parses the find command and returns the corresponding FindCommand object.
     *
     * @param input the user input command
     * @return the FindCommand object
     */
    private Command parseFindCommand(String input) {
        String filterString = input.substring(5);
        assert !filterString.isEmpty() : "index shouldn't be empty";
        return new FindCommand(filterString);
    }

    /**
     * Parses a task from the provided input string.
     *
     * @param input the input string containing task data
     * @return the corresponding Task object
     */
    public Task parseTask(String input) {
        assert input != null : "input string shouldn't be null";
        String[] parts = input.split("\\|");
        assert parts.length >= 3 : "Task input should have at least 3 parts";

        Task task = createTask(parts);
        setTaskMarkStatus(task, parts[1]);

        return task;
    }

    /**
     * Creates a Task object based on the provided parts of the input string.
     *
     * @param parts the split input string parts
     * @return the corresponding Task object
     */
    private Task createTask(String[] parts) {
        return switch (parts[0]) {
        case TODO_TASK -> new Todo(parts[2]);
        case DEADLINE_TASK -> new Deadline(parts[2], parseDateTime(parts[3]));
        case EVENT_TASK -> new Event(parts[2], parseDateTime(parts[3]), parseDateTime(parts[4]));
        default -> throw new IllegalArgumentException("Wrong input format");
        };
    }

    /**
     * Sets the mark status of a task based on the provided status string.
     *
     * @param task   the task to set the status for
     * @param status the mark status string
     */
    private void setTaskMarkStatus(Task task, String status) {
        assert status.equals(" 0 ") || status.equals(" 1 ") : "Task mark status should be 0 or 1";
        if (status.equals(" 1 ")) {
            task.mark();
        } else if (status.equals(" 0 ")) {
            task.unMark();
        } else {
            throw new IllegalArgumentException("Wrong isMarked input format");
        }
    }
}
