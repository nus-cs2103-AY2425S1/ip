package lolo.command;

import lolo.Ui;
import lolo.LoloException;
import lolo.storage.Storage;
import lolo.task.Task;
import lolo.task.TaskList;

/**
 * Represents a command to mark a specific task as not done.
 * This command updates the status of a task and interacts with
 * the user interface and storage to reflect the change.
 */
class UnmarkCommand extends Command {
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
     * updating the user interface, and saving the changes to storage.
     *
     * @param tasks The list of tasks where the specified task will be marked as not done.
     * @param ui The user interface to show the updated status of the task.
     * @param storage The storage to save the updated list of tasks.
     * @throws LoloException If an error occurs while updating or saving the task.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws LoloException {
        Task task = tasks.markTaskAsNotDone(taskNumber);
        ui.showMarkTaskAsNotDone(task);
        storage.save(tasks.getTasks());
    }
}
