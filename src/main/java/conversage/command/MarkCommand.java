package conversage.command;

import conversage.exception.ConverSageException;
import conversage.storage.Storage;
import conversage.task.Task;
import conversage.task.TaskList;
import conversage.ui.Ui;

/**
 * Represents a command to mark a task as done.
 */
public class MarkCommand extends Command {
    private int taskIndex;

    // Note that when user does "mark 1" => the 1 is a string
    /**
     * Constructs a MarkCommand with the specified task index.
     *
     * @param taskIndex the index of the task to be marked as done.
     */
    public MarkCommand(String taskIndex) {
        this.taskIndex = Integer.parseInt(taskIndex);
    }

    /**
     * Executes the mark command, marking a task as done, updating the UI, and saving the task list.
     *
     * @param tasks   The task list containing the task to be marked as done.
     * @param ui      The UI to update.
     * @param storage The storage to save the task list to.
     * @return A message indicating the task has been marked as done.
     * @throws ConverSageException If an error occurs during execution.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws ConverSageException {
        assert taskIndex > 0 && taskIndex < tasks.size(): "Task index should be within valid range";
        Task task = tasks.getTask(taskIndex - 1); // since tasks are 0-indexed
        task.markAsDone();
        ui.showLine();
        ui.showMessage("Nice! I've marked this task as done:\n" + task.toString());
        ui.showLine();
        // Update storage
        storage.save(tasks.getTasks());
        return "Nice! I've marked this task as done:\n" + task.toString();
    }
}
