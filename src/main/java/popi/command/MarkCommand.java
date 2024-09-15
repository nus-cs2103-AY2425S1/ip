package popi.command;

import popi.exception.EmptyDescriptionException;
import popi.exception.InvalidTaskNumberException;
import popi.exception.PopiException;
import popi.task.Task;
import popi.task.TaskList;
import popi.task.TaskManager;
import popi.ui.Ui;

/**
 * Represents a command to mark a task as done.
 */
public class MarkCommand extends Command {
    private final int taskNumber;

    /**
     * Constructor for MarkCommand.
     *
     * @param command The command string that was entered by the user.
     * @throws EmptyDescriptionException If the task number is empty.
     * @throws InvalidTaskNumberException If the task number is not a number.
     */
    public MarkCommand(String command) throws EmptyDescriptionException, InvalidTaskNumberException {
        String[] parts = command.split(" ");

        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new EmptyDescriptionException("The task number cannot be empty.");
        }

        try {
            taskNumber = Integer.parseInt(parts[1]);
        } catch (NumberFormatException e) {
            throw new InvalidTaskNumberException("Task number must be a number");
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, TaskManager taskManager) throws PopiException {
        Task task = tasks.markTask(taskNumber);
        taskManager.save(tasks);
        ui.showTaskMarked(task);
    }
}
