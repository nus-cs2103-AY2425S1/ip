package opus.commands;

import opus.exceptions.OpusException;
import opus.exceptions.OpusMissingArgumentException;
import opus.exceptions.OpusInvalidArgumentException;
import opus.exceptions.OpusTaskNotFoundException;
import opus.Storage;
import opus.TaskList;
import opus.tasks.Task;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand implements Command {
    private final int taskIndex;

    /**
     * Creates a new DeleteCommand object.
     *
     * @param details The details of the command.
     * @throws OpusException If the task number is invalid.
     */
    public DeleteCommand(String details) throws OpusException {
        if (details.trim().isEmpty()) {
            throw new OpusMissingArgumentException("Please provide the task number to delete.");
        }
        try {
            this.taskIndex = Integer.parseInt(details.trim()) - 1; // Convert to zero-based index
            if (taskIndex < 0) {
                throw new OpusInvalidArgumentException("Task number must be a positive integer.");
            }
        } catch (NumberFormatException e) {
            throw new OpusInvalidArgumentException("Please provide a valid task number.");
        }
    }

    @Override
    public String execute(TaskList taskList, Storage storage) throws OpusException {
        if (taskIndex < 0 || taskIndex >= taskList.getSize()) {
            throw new OpusTaskNotFoundException("Task number out of range.");
        }
        Task task = taskList.getTask(taskIndex);
        taskList.removeTask(taskIndex);
        storage.save(taskList.getTasks());
        return "Noted. I've removed this task:\n" + task.toString()
                + "\nNow you have " + taskList.getSize() + " tasks in the list.";
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
