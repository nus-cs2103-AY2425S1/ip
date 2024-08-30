package nether.command;

import nether.NetherException;
import nether.Ui;
import nether.storage.Storage;
import nether.task.Task;
import nether.task.TaskList;

/**
 * Represents a command to delete a task from the task list.
 *
 * The {@code DeleteCommand} class handles the deletion of a specified task from the task list based on the provided task index.
 *
 */
public class DeleteCommand extends Command {
    private final int taskIndex;

    /**
     * Constructs a {@code DeleteCommand} with the specified task index.
     *
     * @param taskIndex The index of the task to be deleted (1-based index).
     */
    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the delete command by removing the specified task from the task list.
     *
     * This method checks if the provided task index is valid, performs the deletion, updates the task list,
     * notifies the user, and saves the updated task list to storage.
     *
     * @param tasks The {@code TaskList} to be modified by the command.
     * @param ui The {@code Ui} instance used to interact with the user.
     * @param storage The {@code Storage} instance used to persist changes.
     * @throws NetherException If the task index is invalid (i.e., out of range or negative).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws NetherException {
        if (taskIndex > tasks.getSize() || taskIndex < 0) {
            throw new NetherException("invalid task number!");
        }

        Task removedTask = tasks.getTask(taskIndex - 1);
        tasks.deleteTask(taskIndex - 1);
        ui.printTaskDeleted(removedTask, tasks.getSize());
        storage.saveTasks(tasks.getTasks());
    }
}
