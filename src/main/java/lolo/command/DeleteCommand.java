package lolo.command;

import lolo.Ui;
import lolo.LoloException;
import lolo.storage.Storage;
import lolo.task.Task;
import lolo.task.TaskList;

/**
 * Represents a command to delete a task from the task list.
 * This command interacts with the user interface to confirm the deletion
 * and updates the storage with the modified task list.
 */
class DeleteCommand extends Command {
    private int taskNumber;

    /**
     * Constructs a DeleteCommand with the specified task number.
     *
     * @param taskNumber The number of the task to be deleted from the task list.
     */
    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Executes the command by deleting the task from the task list,
     * displaying a confirmation message to the user, and saving
     * the updated task list to storage.
     *
     * @param tasks The list of tasks to be modified by the command.
     * @param ui The user interface to interact with the user.
     * @param storage The storage to save the updated task list.
     * @throws LoloException If there is an error during execution or saving.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws LoloException {
        Task task = tasks.deleteTask(taskNumber);
        ui.showDeletedTask(task, tasks.size());
        storage.save(tasks.getTasks());
    }
}
