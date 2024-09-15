package popi.command;

import java.time.LocalDateTime;

import popi.exception.EmptyDescriptionException;
import popi.exception.InvalidTimeFormatException;
import popi.exception.PopiException;
import popi.exception.UnknownCommandException;
import popi.task.Deadline;
import popi.task.Event;
import popi.task.Task;
import popi.task.TaskList;
import popi.task.TaskManager;
import popi.task.Todo;
import popi.ui.Ui;
import popi.utils.DateTimeUtils;

/**
 * Represents a command to add a task to the task list.
 */
public class AddCommand extends Command {
    private final Task task;

    /**
     * Constructor for AddCommand.
     *
     * @param command The command to be parsed, which should include the task type and description.
     * @throws EmptyDescriptionException If the description of the task is empty.
     * @throws InvalidTimeFormatException If the time format is invalid.
     * @throws UnknownCommandException If the command is not recognised or the command is empty.
     */
    public AddCommand(String command) throws EmptyDescriptionException,
            InvalidTimeFormatException, UnknownCommandException {
        if (command == null || command.trim().isEmpty()) {
            throw new UnknownCommandException();
        }

        String[] parts = command.split(" ", 2);
        if (parts.length < 2) {
            throw new EmptyDescriptionException("The description of a task cannot be empty.");
        }
        String taskType = parts[0];
        String description = parts[1];
        assert !taskType.isBlank() : "Task type cannot be empty";
        assert !description.isBlank() : "Task description cannot be empty";

        switch (taskType) {
        case "todo":
            task = createTodoTask(description);
            break;
        case "deadline":
            task = createDeadlineTask(description);
            break;
        case "event":
            task = createEventTask(description);
            break;
        default:
            throw new UnknownCommandException();
        }
    }

    private Task createTodoTask(final String description) throws EmptyDescriptionException {
        if (description.isBlank()) {
            throw new EmptyDescriptionException("The description of the todo task cannot be empty.");
        }
        return new Todo(description);
    }

    private Task createDeadlineTask(final String description) throws EmptyDescriptionException,
            InvalidTimeFormatException {
        String[] deadline = description.split(" /by ", 2);
        if (deadline.length < 2) {
            throw new EmptyDescriptionException("The description and due date of a deadline cannot be empty.");
        }
        LocalDateTime due = DateTimeUtils.parseDateTime(deadline[1]);
        return new Deadline(deadline[0], due);
    }

    private Task createEventTask(final String description) throws EmptyDescriptionException,
            InvalidTimeFormatException {
        String[] event = description.split(" /from | /to ");
        if (event.length < 3) {
            throw new EmptyDescriptionException("The description and time of an event cannot be empty.");
        }

        LocalDateTime start = DateTimeUtils.parseDateTime(event[1]);
        LocalDateTime end = DateTimeUtils.parseDateTime(event[2]);
        return new Event(event[0], start, end);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, TaskManager taskManager) throws PopiException {
        assert task != null : "Task should be initialized in the constructor";
        tasks.addTask(task);
        taskManager.save(tasks);
        ui.showTaskAdded(task, tasks);
    }
}
