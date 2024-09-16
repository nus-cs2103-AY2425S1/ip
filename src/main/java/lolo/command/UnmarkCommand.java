package lolo.command;

import lolo.LoloException;
import lolo.storage.Storage;
import lolo.task.Task;
import lolo.task.TaskList;

/**
 * Represents a command to mark a specific task as not done.
 * This command updates the status of a task and saves
 * the changes to storage.
 */
public class UnmarkCommand extends Command {
    private int taskNumber;

    /**
     * Constructs an UnmarkCommand with the specified task number.
     *
     * @param taskNumber The index of the task to be marked as not done.
     */
    public UnmarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Executes the command by marking the specified task as not done,
     * returning a confirmation message, and saving the changes to storage.
     *
     * @param tasks The list of tasks where the specified task will be marked as not done.
     * @param storage The storage to save the updated list of tasks.
     * @return A string confirmation that the task has been marked as not done.
     * @throws LoloException If an error occurs while updating or saving the task.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws LoloException {
        // Ensure taskIndex is within valid range
        if (taskNumber < 0 || taskNumber >= tasks.size()) {
            int indexShown = taskNumber + 1;
            throw new LoloException("Invalid task index: " + indexShown + " is out of Range");
        }
        Task task = tasks.markTaskAsNotDone(taskNumber);
        storage.save(tasks.getTasks());
        return "OK, I've marked this task as not done yet:\n  " + task;
    }
}

