package opus.commands;

import opus.exceptions.OpusException;
import opus.exceptions.OpusMissingArgumentException;
import opus.exceptions.OpusInvalidArgumentException;
import opus.exceptions.OpusTaskNotFoundException;
import opus.Storage;
import opus.TaskList;
import opus.tasks.Task;

/**
 * Represents a command to mark a task as done.
 */
public class MarkCommand implements Command {
    private final int taskIndex;

    /**
     * Creates a new MarkCommand object.
     *
     * @param details The details of the command.
     * @throws OpusException If the task number is invalid.
     */
    public MarkCommand(String details) throws OpusException {
        if (details.trim().isEmpty()) {
            throw new OpusMissingArgumentException("Please provide the task number to mark.");
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

    /**
     * Marks the task at the specified index as done.
     *
     * @param taskList The task list containing the task to mark as done.
     * @param storage The storage object to save the task list to.
     * @return A message indicating that the task has been marked as done.
     * @throws OpusException If the task number is out of range.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws OpusException {
        if (taskIndex < 0 || taskIndex >= taskList.getSize()) {
            throw new OpusTaskNotFoundException("Task number out of range.");
        }
        Task task = taskList.getTask(taskIndex);
        task.markAsDone();
        storage.save(taskList.getTasks());
        return "Nice! I've marked this task as done:\n" + task.toString();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
