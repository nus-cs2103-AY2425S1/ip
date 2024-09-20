package duck.command;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duck.storage.Storage;
import duck.task.Deadline;
import duck.task.EmptyToDoException;
import duck.task.Event;
import duck.task.InvalidDeadlineException;
import duck.task.InvalidEventException;
import duck.task.Task;
import duck.task.TaskList;
import duck.task.ToDo;
import duck.ui.Ui;

/**
 * Represents a command to manage tasks such as ToDo, Deadline, and Event.
 */
public class TaskCommand implements Command {
    private final String fullCommand;
    private final String commandType;

    /**
     * Constructs a TaskCommand instance with the specified command input and command type.
     *
     * @param fullCommand the full command string input.
     * @param commandType the type of command: "todo", "deadline" or "event".
     */
    public TaskCommand(String fullCommand, String commandType) {
        this.fullCommand = fullCommand;
        this.commandType = commandType;
    }

    /**
     * Executes the command depending on the commandType.
     *
     * @param list the task list to update.
     * @param ui the user interface to interact with the user.
     * @param storage the storage to save the updated task list.
     */
    @Override
    public void executeCommand(TaskList list, Ui ui, Storage storage) {
        assert this.fullCommand != null;
        assert this.commandType != null;

        try {
            Task task = createTask();
            list.add(task);
            ui.showAddTaskMessage(task, list);
            storage.saveTasks(list);
        } catch (InvalidDateFormatException e) {
            ui.showInvalidDateFormat();
        } catch (InvalidDeadlineException e) {
            ui.showInvalidDeadline();
        } catch (InvalidEventException e) {
            ui.showInvalidEvent();
        }
    }

    private Task createTask() throws InvalidDateFormatException, InvalidDeadlineException, InvalidEventException {
        return switch (this.commandType) {
        case "todo" -> parseToDo(this.fullCommand);
        case "deadline" -> parseDeadline(this.fullCommand);
        case "event" -> parseEvent(this.fullCommand);
        default -> throw new IllegalArgumentException("Invalid command type for tasks: " + commandType);
        };
    }

    /**
     * Parses and creates a ToDo task.
     *
     * @param fullCommand the full command string input.
     * @return a ToDo task.
     */
    public static Task parseToDo(String fullCommand) {
        try {
            if (fullCommand.equals("todo")) {
                throw new EmptyToDoException("Cannot have empty todo");
            }
            return new ToDo(fullCommand.split(" ", 2)[1]);
        } catch (EmptyToDoException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * Parses and creates a Deadline task.
     *
     * @param fullCommand the full command string input.
     * @return a Deadline task.
     */
    public static Task parseDeadline(String fullCommand) throws InvalidDateFormatException, InvalidDeadlineException {
        String[] commandParts = fullCommand.split("/by");
        if (commandParts.length != 2) {
            throw new InvalidDeadlineException("Invalid command format! Use: deadline <desc> /by <yyyy-mm-dd>");
        }
        LocalDate date;
        try {
            date = LocalDate.parse(commandParts[1].trim());
        } catch (DateTimeParseException e) {
            throw new InvalidDateFormatException("Invalid date format, should be <yyyy-mm-dd>");
        }
        return new Deadline(commandParts[0].split(" ", 2)[1], date);
    }

    /**
     * Parses and creates an Event task.
     *
     * @param fullCommand the full command string input.
     * @return an Event task.
     */
    public static Task parseEvent(String fullCommand) throws InvalidEventException {
        String[] commandParts = fullCommand.split("/from|/to");
        if (commandParts.length < 3) {
            throw new InvalidEventException("Invalid command format! Use: event <desc> /from <start> /to <end>");
        }
        return new Event(commandParts[0].split(" ", 2)[1], commandParts[1], commandParts[1]);
    }
}
