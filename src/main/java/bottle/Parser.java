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
 * The type Parser.
 */
public class Parser {
    private static final String TODO_TASK = "T ";
    private static final String DEADLINE_TASK = "D ";
    private static final String EVENT_TASK = "E ";

    /**
     * Parse date time local date time.
     *
     * @param dateTimeStr the date time str
     * @return the local date time
     */
    private static LocalDateTime parseDateTime(String dateTimeStr) {
        assert dateTimeStr != null : "dateTimeStr shouldn't be null";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        try {
            return LocalDateTime.parse(dateTimeStr, formatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("OOPS!!! The date format is incorrect. Please use: dd/MM/yyyy HHmm");
        }
    }

    /**
     * Parse command.
     *
     * @param input the input
     * @return the command
     */
    public Command parseCommand(String input) {
        assert input != null : "input shouldn't be null";
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
     * Parse mark command command.
     *
     * @param input the input
     * @return the command
     */
    private Command parseMarkCommand(String input) {
        String taskIndexStr = input.substring(5);
        assert !taskIndexStr.isEmpty() : "taskIndexStr shouldn't be empty";
        int taskIndex = Integer.parseInt(taskIndexStr) - 1;
        return new MarkCommand(taskIndex);
    }

    /**
     * Parse unmark command command.
     *
     * @param input the input
     * @return the command
     */
    private Command parseUnmarkCommand(String input) {
        String taskIndexStr = input.substring(7);
        assert !taskIndexStr.isEmpty() : "taskIndexStr shouldn't be empty";
        int taskIndex = Integer.parseInt(taskIndexStr) - 1;
        return new UnMarkCommand(taskIndex);
    }

    /**
     * Parse todo command command.
     *
     * @param input the input
     * @return the command
     */
    private Command parseTodoCommand(String input) {
        assert input.length() > 5 : "Todo task description shouldn't be empty";
        return new AddTodoTask(input.substring(5));
    }

    /**
     * Parse deadline command command.
     *
     * @param input the input
     * @return the command
     */
    private Command parseDeadlineCommand(String input) {
        String[] parts = input.split(" /by ");
        if (parts.length != 2) {
            throw new IllegalArgumentException("OOPS!!! The deadline format is incorrect. "
                    + "Use: deadline <description> /by <dd/MM/yyyy HHmm>");
        }
        String description = parts[0];
        assert !description.isEmpty() : "description shouldn't be empty";
        LocalDateTime by = parseDateTime(parts[1]);
        return new AddDeadlineTask(description, by);
    }

    /**
     * Parse event command command.
     *
     * @param input the input
     * @return the command
     */
    private Command parseEventCommand(String input) {
        String[] parts = input.split(" /from | /to ");
        if (parts.length != 3) {
            throw new IllegalArgumentException("OOPS!!! The event format is incorrect. "
                    + "Use: event <description> /from <dd/MM/yyyy HHmm> /to <dd/MM/yyyy HHmm>");
        }
        String description = parts[0];
        assert !description.isEmpty() : "description shouldn't be empty";
        LocalDateTime from = parseDateTime(parts[1]);
        LocalDateTime to = parseDateTime(parts[2]);
        return new AddEventTask(description, from, to);
    }

    /**
     * Parse delete command command.
     *
     * @param input the input
     * @return the command
     */
    private Command parseDeleteCommand(String input) {
        String indexStr = input.substring(7);
        assert !indexStr.isEmpty() : "index shouldn't be empty";
        int taskIndex = Integer.parseInt(indexStr) - 1;
        return new DeleteCommand(taskIndex);
    }

    /**
     * Parse find command command.
     *
     * @param input the input
     * @return the command
     */
    private Command parseFindCommand(String input) {
        String filterString = input.substring(5);
        assert !filterString.isEmpty() : "index shouldn't be empty";
        return new FindCommand(filterString);
    }

    /**
     * Parse task task.
     *
     * @param input the input
     * @return the task
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
     * Create task task.
     *
     * @param parts the parts
     * @return the task
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
     * Sets task mark status.
     *
     * @param task   the task
     * @param status the status
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

