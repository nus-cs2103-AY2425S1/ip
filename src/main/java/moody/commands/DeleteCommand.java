package moody.commands;

import moody.storage.Storage;
import moody.tasks.Task;
import moody.tasks.TaskList;
import moody.ui.Ui;

import java.io.IOException;

/**
 * Represents a command to delete a task from the task list.
 * This command removes a task at the specified index from the task list,
 * updates the storage, and informs the user through the UI.
 */
public class DeleteCommand extends Command {
    protected int taskIndex;

    /**
     * Constructs a {@code DeleteCommand} with the specified task index.
     *
     * @param taskIndex The index of the task to be deleted.
     */
    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the delete command by removing the task at the specified index
     * from the task list, saving the updated list to storage, and showing
     * a confirmation message to the user.
     *
     * @param tasks The task list to operate on.
     * @param ui The user interface for showing messages.
     * @param storage The storage for saving changes to the task list.
     * @throws IOException If an I/O error occurs while saving to storage.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        Task removedTask = tasks.remove(taskIndex);
        storage.save(tasks.toArrayList());
        ui.showTaskRemoved(removedTask, tasks.size());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
